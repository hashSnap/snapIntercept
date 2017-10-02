package local.interceptmod.modules;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Environment;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;
import static local.interceptmod.modules.Obfuscator.CbcEncryptionAlgorithmDecrypt;
import static local.interceptmod.modules.Obfuscator.CbcEncryptionAlgorithmKlass;
import static local.interceptmod.modules.Obfuscator.EncryptionAlgorithmInterface;
import static local.interceptmod.modules.Obfuscator.ExpectedVersion;
import static local.interceptmod.modules.Obfuscator.MediaCacheEntryConstructorFirstParam;
import static local.interceptmod.modules.Obfuscator.MediaCacheEntryKlass;
import static local.interceptmod.modules.Obfuscator.RootDetectorFirst;
import static local.interceptmod.modules.Obfuscator.RootDetectorForth;
import static local.interceptmod.modules.Obfuscator.RootDetectorKlass;
import static local.interceptmod.modules.Obfuscator.RootDetectorSecond;
import static local.interceptmod.modules.Obfuscator.RootDetectorThird;
import static local.interceptmod.modules.Obfuscator.SnapEventGetCacheKey;
import static local.interceptmod.modules.Obfuscator.SnapEventIsVideo;
import static local.interceptmod.modules.Obfuscator.SnapEventIsZipped;
import static local.interceptmod.modules.Obfuscator.SnapEventKlass;
import static local.interceptmod.modules.Obfuscator.SnapEventTimestamp;
import static local.interceptmod.modules.Obfuscator.SnapEventUsername;
import static local.interceptmod.modules.Obfuscator.VersionCode;


public class SnapInterceptLoader implements IXposedHookLoadPackage {


    private class RootDetectorOverrides extends XC_MethodReplacement {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
            return false;
        }
    }

    private class SnapInfo {
         String mUsername;
         long mTimestamp;
         boolean mIsVideo;
         boolean mIsZipped;
    }

    private final String AdditionalFieldSnapInfo = "SnapInfo";

    @Override
    public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
        Context mContext;
        final ConcurrentHashMap<String, Object> mCacheKeysMap = new ConcurrentHashMap();

        if(!lpparam.packageName.equals("com.snapchat.android")) {

            return;
        }
        log("Loaded app: " + lpparam.packageName);
        Object activityThread = XposedHelpers.callStaticMethod(findClass("android.app.ActivityThread", null), "currentActivityThread");
        mContext = (Context) XposedHelpers.callMethod(activityThread, "getSystemContext");
        PackageInfo snapchatPackage = mContext.getPackageManager().getPackageInfo(lpparam.packageName, 0);
        if(snapchatPackage.versionCode != VersionCode) {
            log("Wrong APK build. Ensure version "+ExpectedVersion+" is installed.");
            Toast.makeText(mContext,"SnapIntercept: Wrong APK build. Ensure version "+ExpectedVersion+" is installed.",Toast.LENGTH_LONG).show();
            return;
        }

        // Hook all the root detector methods
        findAndHookMethod(RootDetectorKlass,lpparam.classLoader,RootDetectorFirst,new RootDetectorOverrides());
        findAndHookMethod(RootDetectorKlass,lpparam.classLoader,RootDetectorSecond,new RootDetectorOverrides());
        findAndHookMethod(RootDetectorKlass,lpparam.classLoader,RootDetectorThird,new RootDetectorOverrides());
        findAndHookMethod(RootDetectorKlass,lpparam.classLoader,RootDetectorForth,new RootDetectorOverrides());

        // Hook into the SnapEvent class and create a SnapInfo object with the necessary information, then add to the mCacheKeysMap
        findAndHookMethod(SnapEventKlass, lpparam.classLoader, SnapEventGetCacheKey, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                String cacheKey = (String)param.getResult();
                log("in get cache key for "+cacheKey);

                Object snapEvent = param.thisObject;

                boolean isVideo = (boolean)XposedHelpers.callMethod(snapEvent,SnapEventIsVideo);
                boolean isZipped = (boolean)XposedHelpers.getObjectField(snapEvent,SnapEventIsZipped);
                String username = (String)XposedHelpers.getObjectField(snapEvent,SnapEventUsername);
                long timestamp = XposedHelpers.getLongField(snapEvent,SnapEventTimestamp);

                log("isVideo="+isVideo+" username ="+username+" timestamp="+timestamp);

                SnapInfo snapInfo = new SnapInfo();
                snapInfo.mUsername = username;
                snapInfo.mTimestamp = timestamp;
                snapInfo.mIsVideo = isVideo;
                snapInfo.mIsZipped = isZipped;

                mCacheKeysMap.put(cacheKey, snapInfo);
            }
        });


        // hook into the constructor for the MediaCacheEntry, and find the related SnapInfo object from mCacheKeysMap
        // then attach the SnapInfo object to the encryptionObject parameter
        XposedHelpers.findAndHookConstructor(MediaCacheEntryKlass,
                lpparam.classLoader,
                MediaCacheEntryConstructorFirstParam,
                String.class,
                EncryptionAlgorithmInterface,
                boolean.class, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                        String cacheKey = (String)param.args[1];
                        Object encryptionObject = param.args[2];
                        log("in media cache entry hook for object"+cacheKey);
                        if(mCacheKeysMap.containsKey(cacheKey)) {
                            log("contains key, adding additional field");
                            Object snapInfo = mCacheKeysMap.get(cacheKey);
                            XposedHelpers.setAdditionalInstanceField(encryptionObject,AdditionalFieldSnapInfo,snapInfo);

                        }
                    }
                });

        // Hook into the decrypt method of the CbcEncryptionAlgorithm class and check to see if there
        // is a SnapInfo object associated to it
        findAndHookMethod(CbcEncryptionAlgorithmKlass,
                lpparam.classLoader,
                CbcEncryptionAlgorithmDecrypt,
                InputStream.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        log("in encryption hook");
                        InputStream returnedStream = (InputStream)param.getResult();
                        if(returnedStream == null) {
                            log("Returned stream is null");
                            return;
                        }
                        SnapInfo snapInfo = (SnapInfo) XposedHelpers.getAdditionalInstanceField(param.thisObject,AdditionalFieldSnapInfo);
                        if(snapInfo == null) {
                            log("snap info is null");

                            return;
                        }

                        InputStream newStream;
                        if(snapInfo.mIsZipped) {
                            newStream = saveZipStream(snapInfo,returnedStream);
                        }
                        else {
                            newStream = saveStream(snapInfo,returnedStream);
                        }

                        param.setResult(newStream);
                    }
                });



    }

    private InputStream saveStream(SnapInfo snapInfo, InputStream input) throws Exception {
        String fileName = generateFileName(snapInfo);
        File f = new File(getFileBasePath(snapInfo),fileName);
        boolean fileCreated = f.createNewFile();
        if(fileCreated) {
            copyInputStreamToFile(input, f);
            BufferedInputStream fis;
            IOUtils.closeQuietly(input);
            fis = new BufferedInputStream(new FileInputStream(f));
            return fis;
        }
        return input;
    }

    private InputStream saveZipStream(SnapInfo snapInfo, InputStream input) throws Exception {
        String fileName = generateFileName(snapInfo);
        File mediaFile = new File(getFileBasePath(snapInfo),fileName);

        boolean fileCreated = mediaFile.createNewFile();
        if(fileCreated) {
            // We need to clone the InputStream to memory temporarily so that we can pass on the original
            // data back
            ByteArrayOutputStream tempBufferStream = new ByteArrayOutputStream();
            tempBufferStream.write(input);

            // Create a new input stream from the buffer.
            input = tempBufferStream.toInputStream();

            // Loop through the file entries in the zip. Handle the special files starting with media~ and overlay~
            ZipInputStream zipStream = new ZipInputStream(input);
            while (true) {
                ZipEntry nextEntry = zipStream.getNextEntry();
                if (nextEntry == null) {
                    break;
                }
                String name = nextEntry.getName();
                if (name.startsWith("media~")) {
                    copyInputStreamToFile(zipStream, mediaFile);
                } else if (name.startsWith("overlay~")) {
                    File overlayFile = new File(getFileBasePath(snapInfo),fileName+"_overlay.jpg");
                    copyInputStreamToFile(zipStream, overlayFile);
                }
            }

            IOUtils.closeQuietly(input);
            // return an InputStream to the original data
            return tempBufferStream.toInputStream();
        }
        return input;
    }

    private File getFileBasePath(SnapInfo snapInfo) {
        String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();

        String path = baseDir+File.separator+"interceptmod"+File.separator+snapInfo.mUsername;

        File filePath = new File(path);
        filePath.mkdirs();
        return filePath;
    }

    private String generateFileName(SnapInfo snapInfo) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS", Locale.getDefault());
        String extension = snapInfo.mIsVideo ? ".mp4" : ".jpg";
        String dateName = dateFormat.format(new Date(snapInfo.mTimestamp));
        return snapInfo.mUsername+"_"+dateName+extension;
    }

    private static void log(String str) {
        XposedBridge.log("SnapIntercept: "+str);
    }

    private static void copyInputStreamToFile(InputStream in, File file) {
        OutputStream out = null;

        try {
            out = new FileOutputStream(file);
            IOUtils.copy(in,out);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            IOUtils.closeQuietly(out);
        }
    }
}

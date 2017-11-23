package local.interceptmod.modules;

interface Obfuscator {
    // Snapchat version
    int VersionCode = 1403;
    String ExpectedVersion = "10.22.6.0";
    // Snap event
    String SnapEventKlass = "mxx"; // "-FirstFrame"
    String SnapEventGetCacheKey = "L"; // return L() + "-FirstFrame";
    String SnapEventIsVideo = "i"; // the if above: return obz.SNAP_VIDEO_RECEIVED;
    String SnapEventUsername = "al"; //  this.am = str2;
    String SnapEventTimestamp = "v"; // this.v = j2;
    String SnapEventIsZipped= "am"; // "isZipped", this.am
    // Media cache
    String MediaCacheEntryKlass = "nci"; // ("mCache", this.a).a("mKey", this.b)
    String MediaCacheEntryConstructorFirstParam = "pgg";
    // Encryption
    String CbcEncryptionAlgorithmKlass = "com.snapchat.android.framework.crypto.CbcEncryptionAlgorithm"; 
    String CbcEncryptionAlgorithmDecrypt = "b"; 
    String EncryptionAlgorithmInterface = "com.snapchat.android.framework.crypto.EncryptionAlgorithm"; 
    // Root detection
    String RootDetectorKlass = "olh"; // "/sbin/su", "/system/bin/su",
    String RootDetectorFirst = "b"; 
    String RootDetectorSecond = "c"; 
    String RootDetectorThird = "d"; 
    String RootDetectorForth = "e";
}
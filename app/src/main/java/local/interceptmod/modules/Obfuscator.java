package local.interceptmod.modules;

interface Obfuscator {
    // Snapchat version
    int VersionCode = 1691;
    String ExpectedVersion = "10.30.5.0";
    // Snap event - everything here is in the file of SnapEventKlass
    String SnapEventKlass = "uuv"; // find the file with "-FirstFrame" in it
    String SnapEventGetCacheKey = "ag"; // return ag() + "-FirstFrame";
    String SnapEventIsVideo = "i"; // in the (i()) in the if statement above: return wab.SNAP_VIDEO_RECEIVED ,search for SNAP_VIDEO_RECEIVED;
    String SnapEventUsername = "aB"; //  this.aB = str2;
    String SnapEventTimestamp = "w"; // this.w = j2;
    String SnapEventIsZipped= "aC"; // "isZipped", this.aC
    // Media cache - everything here is in the file of MediaCacheEntryKlass
    String MediaCacheEntryKlass = "uzi"; // find the file with ("mCache", this.a).a("mKey", this.b) in it
    String MediaCacheEntryConstructorFirstParam = "xiy"; // public uzi(xiy xiy, String str) {this(xiy, str, null, false);}
    // Encryption
    String CbcEncryptionAlgorithmKlass = "com.snapchat.android.framework.crypto.CbcEncryptionAlgorithm";
    String CbcEncryptionAlgorithmDecrypt = "b";
    String EncryptionAlgorithmInterface = "com.snapchat.android.framework.crypto.EncryptionAlgorithm";
    // Root detection
    String RootDetectorKlass = "wma"; // "/sbin/su", "/system/bin/su"
    String RootDetectorFirst = "b";
    String RootDetectorSecond = "c";
    String RootDetectorThird = "d";
    String RootDetectorForth = "e";
}
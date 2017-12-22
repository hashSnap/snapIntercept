package local.interceptmod.modules;

interface Obfuscator {
    // Snapchat version
    int VersionCode = 1524;
    String ExpectedVersion = "10.23.7.0";
    // Snap event
    String SnapEventKlass = "och"; // "-FirstFrame"
    String SnapEventGetCacheKey = "L"; // return L() + "-FirstFrame";
    String SnapEventIsVideo = "i"; // the if above: return xxx.SNAP_VIDEO_RECEIVED;
    String SnapEventUsername = "av"; //  this.av = str2;
    String SnapEventTimestamp = "v"; // this.v = j2;
    String SnapEventIsZipped= "aw"; // "isZipped", this.aw
    // Media cache
    String MediaCacheEntryKlass = "oha"; // ("mCache", this.a).a("mKey", this.b)
    String MediaCacheEntryConstructorFirstParam = "qng";
    // Encryption
    String CbcEncryptionAlgorithmKlass = "com.snapchat.android.framework.crypto.CbcEncryptionAlgorithm"; 
    String CbcEncryptionAlgorithmDecrypt = "b"; 
    String EncryptionAlgorithmInterface = "com.snapchat.android.framework.crypto.EncryptionAlgorithm"; 
    // Root detection
    String RootDetectorKlass = "prj"; // "/sbin/su", "/system/bin/su",
    String RootDetectorFirst = "b"; 
    String RootDetectorSecond = "c"; 
    String RootDetectorThird = "d"; 
    String RootDetectorForth = "e";
}
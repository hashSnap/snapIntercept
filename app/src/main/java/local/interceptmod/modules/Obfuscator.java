package local.interceptmod.modules;

interface Obfuscator {
    // Snapchat version
    int VersionCode = 1385;
    String ExpectedVersion = "10.20.5.0";
    // Snap event
    String SnapEventKlass = "mlv"; // "-FirstFrame"
    String SnapEventGetCacheKey = "L";
    String SnapEventIsVideo = "da_";
    String SnapEventUsername = "am";
    String SnapEventTimestamp = "v";
    String SnapEventIsZipped= "an";
    // Media cache
    String MediaCacheEntryKlass = "mql"; // ("mCache", this.a).a("mKey", this.b)
    String MediaCacheEntryConstructorFirstParam = "otd";
    // Encryption
    String CbcEncryptionAlgorithmKlass = "com.snapchat.android.framework.crypto.CbcEncryptionAlgorithm"; 
    String CbcEncryptionAlgorithmDecrypt = "b"; 
    String EncryptionAlgorithmInterface = "com.snapchat.android.framework.crypto.EncryptionAlgorithm"; 
    // Root detection
    String RootDetectorKlass = "nyc"; // "/sbin/su", "/system/bin/su",
    String RootDetectorFirst = "b"; 
    String RootDetectorSecond = "c"; 
    String RootDetectorThird = "d"; 
    String RootDetectorForth = "e";
}
package local.interceptmod.modules;

interface Obfuscator {
    // Snapchat version
    int VersionCode = 1380;
    String ExpectedVersion = "10.19.5.0";
    // Snap event
    String SnapEventKlass = "mff"; // "-FirstFrame"
    String SnapEventGetCacheKey = "L";
    String SnapEventIsVideo = "do_";
    String SnapEventUsername = "an";
    String SnapEventTimestamp = "t";
    String SnapEventIsZipped= "ao";
    // Media cache
    String MediaCacheEntryKlass = "mjt"; // ("mCache", this.a).a("mKey", this.b)
    String MediaCacheEntryConstructorFirstParam = "ole";
    // Encryption
    String CbcEncryptionAlgorithmKlass = "com.snapchat.android.framework.crypto.CbcEncryptionAlgorithm"; 
    String CbcEncryptionAlgorithmDecrypt = "b"; 
    String EncryptionAlgorithmInterface = "com.snapchat.android.framework.crypto.EncryptionAlgorithm"; 
    // Root detection
    String RootDetectorKlass = "nps"; // "/sbin/su", "/system/bin/su",
    String RootDetectorFirst = "b"; 
    String RootDetectorSecond = "c"; 
    String RootDetectorThird = "d"; 
    String RootDetectorForth = "e";
}

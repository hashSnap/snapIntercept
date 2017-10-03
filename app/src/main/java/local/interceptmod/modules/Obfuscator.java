package local.interceptmod.modules;

interface Obfuscator {
    // Snapchat version
    int VersionCode = 1367;
    String ExpectedVersion = "10.18.2.0";
    // Snap event
    String SnapEventKlass = "lne";
    String SnapEventGetCacheKey = "K";
    String SnapEventIsVideo = "cX_";
    String SnapEventUsername = "al";
    String SnapEventTimestamp = "t";
    String SnapEventIsZipped= "am";
    // Media cache
    String MediaCacheEntryKlass = "lrm";
    String MediaCacheEntryConstructorFirstParam = "npz";
    // Encryption
    String CbcEncryptionAlgorithmKlass = "com.snapchat.android.framework.crypto.CbcEncryptionAlgorithm";
    String CbcEncryptionAlgorithmDecrypt = "b";
    String EncryptionAlgorithmInterface = "com.snapchat.android.framework.crypto.EncryptionAlgorithm";
    // Root detection
    String RootDetectorKlass = "muq";
    String RootDetectorFirst = "b";
    String RootDetectorSecond = "c";
    String RootDetectorThird = "d";
    String RootDetectorForth = "e";
}

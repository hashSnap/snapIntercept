package local.interceptmod.modules;

class Obfuscator {
    // Snapchat version
    final static int VersionCode = 1367;
    final static String ExpectedVersion = "10.18.2.0";
    // Snap event
    final static String SnapEventKlass = "lne";
    final static String SnapEventGetCacheKey = "K";
    final static String SnapEventIsVideo = "cX_";
    final static String SnapEventUsername = "al";
    final static String SnapEventTimestamp = "t";
    final static String SnapEventIsZipped= "am";
    // Media cache
    final static String MediaCacheEntryKlass = "lrm";
    final static String MediaCacheEntryConstructorFirstParam = "npz";
    // Encryption
    final static String CbcEncryptionAlgorithmKlass = "com.snapchat.android.framework.crypto.CbcEncryptionAlgorithm";
    final static String CbcEncryptionAlgorithmDecrypt = "b";
    final static String EncryptionAlgorithmInterface = "com.snapchat.android.framework.crypto.EncryptionAlgorithm";
    // Root detection
    final static String RootDetectorKlass = "muq";
    final static String RootDetectorFirst = "b";
    final static String RootDetectorSecond = "c";
    final static String RootDetectorThird = "d";
    final static String RootDetectorForth = "e";
}

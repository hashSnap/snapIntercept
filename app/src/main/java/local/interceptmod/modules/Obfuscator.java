package local.interceptmod.modules;

interface Obfuscator {
    // Snapchat version
    int VersionCode = 1694;
    String ExpectedVersion = "10.31.0.0";
    // Snap event - everything here is in the file of SnapEventKlass
    String SnapEventKlass = "vqb"; // find the file with "-FirstFrame" in it
    String SnapEventGetCacheKey = "ai"; // return ai() + "-FirstFrame";
    String SnapEventIsVideo = "i"; // in the (i()) in the if statement above: return wuf.SNAP_VIDEO_RECEIVED ,search for SNAP_VIDEO_RECEIVED;
    String SnapEventUsername = "aE"; //  this.aE = str2;
    String SnapEventTimestamp = "w"; // this.w = j2;
    String SnapEventIsZipped= "aF"; // "isZipped", this.aF
    // Media cache - everything here is in the file of MediaCacheEntryKlass
    String MediaCacheEntryKlass = "vuq"; // find the file with ("mCache", this.a).a("mKey", this.b) in it
    String MediaCacheEntryConstructorFirstParam = "yer"; //  public vuq(yer yer, String str) { this(yer, str, null, false);}
    // Encryption
    String CbcEncryptionAlgorithmKlass = "com.snapchat.android.framework.crypto.CbcEncryptionAlgorithm";
    String CbcEncryptionAlgorithmDecrypt = "b";
    String EncryptionAlgorithmInterface = "com.snapchat.android.framework.crypto.EncryptionAlgorithm";
    // Root detection
    String RootDetectorKlass = "xho"; // "/sbin/su", "/system/bin/su"
    String RootDetectorFirst = "b";
    String RootDetectorSecond = "c";
    String RootDetectorThird = "d";
    String RootDetectorForth = "e";
}

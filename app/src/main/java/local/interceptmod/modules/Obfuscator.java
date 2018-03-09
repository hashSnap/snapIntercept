package local.interceptmod.modules;

interface Obfuscator {
    // Snapchat version
    int VersionCode = 1659;
    String ExpectedVersion = "10.27.0.0";
    // Snap event - everything here is in the file of SnapEventKlass
    String SnapEventKlass = "rpe"; // find the file with "-FirstFrame" in it
    String SnapEventGetCacheKey = "L"; // return L() + "-FirstFrame";
    String SnapEventIsVideo = "i"; // in the (i()) in the if statement above: return rnv.SNAP_VIDEO_RECEIVED;
    String SnapEventUsername = "ay"; //  this.ay = str2;
    String SnapEventTimestamp = "v"; // this.v = j2;
    String SnapEventIsZipped= "az"; // "isZipped", this.az
    // Media cache - everything here is in the file of MediaCacheEntryKlass
    String MediaCacheEntryKlass = "rtv"; // find the file with ("mCache", this.a).a("mKey", this.b) in it
    String MediaCacheEntryConstructorFirstParam = "ucd"; // public rtv(ucd ucd, String str) {this(ucd, str, null, false);}
    // Encryption
    String CbcEncryptionAlgorithmKlass = "com.snapchat.android.framework.crypto.CbcEncryptionAlgorithm";
    String CbcEncryptionAlgorithmDecrypt = "b";
    String EncryptionAlgorithmInterface = "com.snapchat.android.framework.crypto.EncryptionAlgorithm";
    // Root detection
    String RootDetectorKlass = "tfr"; // "/sbin/su", "/system/bin/su",
    String RootDetectorFirst = "b";
    String RootDetectorSecond = "c";
    String RootDetectorThird = "d";
    String RootDetectorForth = "e";
}
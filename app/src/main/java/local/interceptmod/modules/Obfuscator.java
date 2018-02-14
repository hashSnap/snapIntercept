package local.interceptmod.modules;

interface Obfuscator {
    // Snapchat version
    int VersionCode = 1611;
    String ExpectedVersion = "10.25.0.0";
    // Snap event - everything here is in the file of SnapEventKlass
    String SnapEventKlass = "qgt"; // find the file with "-FirstFrame" in it
    String SnapEventGetCacheKey = "L"; // return L() + "-FirstFrame";
    String SnapEventIsVideo = "i"; // in the (i()) in the if statement above: return rnv.SNAP_VIDEO_RECEIVED;
    String SnapEventUsername = "aw"; //  this.aw = str2;
    String SnapEventTimestamp = "v"; // this.v = j2;
    String SnapEventIsZipped= "ax"; // "isZipped", this.ax
    // Media cache - everything here is in the file of MediaCacheEntryKlass
    String MediaCacheEntryKlass = "qlj"; // find the file with ("mCache", this.a).a("mKey", this.b) in it
    String MediaCacheEntryConstructorFirstParam = "svt"; // public qli(svs svs, String str) {this(svs, str, null, false);}
    // Encryption
    String CbcEncryptionAlgorithmKlass = "com.snapchat.android.framework.crypto.CbcEncryptionAlgorithm";
    String CbcEncryptionAlgorithmDecrypt = "b";
    String EncryptionAlgorithmInterface = "com.snapchat.android.framework.crypto.EncryptionAlgorithm";
    // Root detection
    String RootDetectorKlass = "rza"; // "/sbin/su", "/system/bin/su",
    String RootDetectorFirst = "b";
    String RootDetectorSecond = "c";
    String RootDetectorThird = "d";
    String RootDetectorForth = "e";
}
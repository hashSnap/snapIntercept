package local.interceptmod.modules;

interface Obfuscator {
    // Snapchat version
    int VersionCode = 1671;
    String ExpectedVersion = "10.28.5.0";
    // Snap event - everything here is in the file of SnapEventKlass
    String SnapEventKlass = "tjx"; // find the file with "-FirstFrame" in it
    String SnapEventGetCacheKey = "L"; // return L() + "-FirstFrame";
    String SnapEventIsVideo = "i"; // in the (i()) in the if statement above: return uos.SNAP_VIDEO_RECEIVED;
    String SnapEventUsername = "az"; //  this.az = str2;
    String SnapEventTimestamp = "w"; // this.w = j2;
    String SnapEventIsZipped= "aA"; // "isZipped", this.aA
    // Media cache - everything here is in the file of MediaCacheEntryKlass
    String MediaCacheEntryKlass = "tom"; // find the file with ("mCache", this.a).a("mKey", this.b) in it
    String MediaCacheEntryConstructorFirstParam = "vxe"; // public tom(vxe vxe, String str) {this(vxe, str, null, false);}
    // Encryption
    String CbcEncryptionAlgorithmKlass = "com.snapchat.android.framework.crypto.CbcEncryptionAlgorithm";
    String CbcEncryptionAlgorithmDecrypt = "b";
    String EncryptionAlgorithmInterface = "com.snapchat.android.framework.crypto.EncryptionAlgorithm";
    // Root detection
    String RootDetectorKlass = "vah"; // "/sbin/su", "/system/bin/su",
    String RootDetectorFirst = "b";
    String RootDetectorSecond = "c";
    String RootDetectorThird = "d";
    String RootDetectorForth = "e";
}
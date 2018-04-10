package local.interceptmod.modules;

interface Obfuscator {
    // Snapchat version
    int VersionCode = 1681;
    String ExpectedVersion = "10.29.5.0";
    // Snap event - everything here is in the file of SnapEventKlass
    String SnapEventKlass = "tqf"; // find the file with "-FirstFrame" in it
    String SnapEventGetCacheKey = "ag"; // return ag() + "-FirstFrame";
    String SnapEventIsVideo = "i"; // in the (i()) in the if statement above: return uvm.SNAP_VIDEO_RECEIVED ,search for SNAP_VIDEO_RECEIVED;
    String SnapEventUsername = "aB"; //  this.aB = str2;
    String SnapEventTimestamp = "w"; // this.w = j2;
    String SnapEventIsZipped= "aC"; // "isZipped", this.aC
    // Media cache - everything here is in the file of MediaCacheEntryKlass
    String MediaCacheEntryKlass = "tuu"; // find the file with ("mCache", this.a).a("mKey", this.b) in it
    String MediaCacheEntryConstructorFirstParam = "weu"; // public tuu(weu weu, String str) {this(weu, str, null, false);}
    // Encryption
    String CbcEncryptionAlgorithmKlass = "com.snapchat.android.framework.crypto.CbcEncryptionAlgorithm";
    String CbcEncryptionAlgorithmDecrypt = "b";
    String EncryptionAlgorithmInterface = "com.snapchat.android.framework.crypto.EncryptionAlgorithm";
    // Root detection
    String RootDetectorKlass = "vhm"; // "/sbin/su", "/system/bin/su"
    String RootDetectorFirst = "b";
    String RootDetectorSecond = "c";
    String RootDetectorThird = "d";
    String RootDetectorForth = "e";
}
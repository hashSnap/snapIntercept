package local.interceptmod.modules;

interface Obfuscator {
    // Snapchat version
    int VersionCode = 1741;
    String ExpectedVersion = "10.35.5.0";
    // Snap event - everything here is in the file of SnapEventKlass
    String SnapEventKlass = "abbr"; // find the file with "-FirstFrame" in it
    String SnapEventGetCacheKey = "k"; // return ai() + "-FirstFrame";
    String SnapEventIsVideo = "aF_"; // in the (aF_()) in the if statement above: return achn.SNAP_VIDEO_RECEIVED ,search for SNAP_VIDEO_RECEIVED;
    String SnapEventUsername = "aF"; //  this.aE = str2;
    String SnapEventTimestamp = "y"; // this.w = j2;
    String SnapEventIsZipped= "aG"; // "isZipped", this.aG
    // Media cache - everything here is in the file of MediaCacheEntryKlass
    String MediaCacheEntryKlass = "abgd"; // find the file with ("mCache", this.a).a("mKey", this.b) in it
    String MediaCacheEntryConstructorFirstParam = "adtu"; //  public abgd(adtu adtu, String str) { this(adtu, str, null, false);}
    // Encryption
    String CbcEncryptionAlgorithmKlass = "com.snapchat.android.framework.crypto.CbcEncryptionAlgorithm";
    String CbcEncryptionAlgorithmDecrypt = "b";
    String EncryptionAlgorithmInterface = "com.snapchat.android.framework.crypto.EncryptionAlgorithm";
    // Root detection
    String RootDetectorKlass = "actu"; // "/sbin/su", "/system/bin/su"
    String RootDetectorFirst = "b";
    String RootDetectorSecond = "c";
    String RootDetectorThird = "d";
    String RootDetectorForth = "e";
}

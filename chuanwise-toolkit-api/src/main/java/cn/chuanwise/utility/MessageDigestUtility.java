package cn.chuanwise.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestUtility extends StaticUtility {
    public static final MessageDigest MD5;

    static {
        MessageDigest tempMD5;
        try {
            tempMD5 = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            tempMD5 = null;
        }
        MD5 = tempMD5;
    }
}

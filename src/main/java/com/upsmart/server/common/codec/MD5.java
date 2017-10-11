package com.upsmart.server.common.codec;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yuhang on 17-6-9.
 */
public final class MD5 {

    // 全局数组
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // 返回形式只为数字
    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }

    // 转换字节数组为16进制字串
    public static String byteToString(byte[] bByte) {
        StringBuilder sBuffer = new StringBuilder();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    public static String encrypt(String strObj) throws NoSuchAlgorithmException {
        String resultString;
        MessageDigest md = MessageDigest.getInstance("MD5");
        // md.digest() 该函数返回值为存放哈希值结果的byte数组
        resultString = byteToString(md.digest(strObj.getBytes()));
        return resultString;
    }

    public static String encrypt(byte[] obj) throws NoSuchAlgorithmException {
        String resultString;
        MessageDigest md = MessageDigest.getInstance("MD5");
        // md.digest() 该函数返回值为存放哈希值结果的byte数组
        resultString = byteToString(md.digest(obj));
        return resultString;
    }

    public static String encrypt(ByteBuffer obj) throws NoSuchAlgorithmException {
        String resultString;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(obj);
        resultString = byteToString(md.digest());
        return resultString;
    }
}

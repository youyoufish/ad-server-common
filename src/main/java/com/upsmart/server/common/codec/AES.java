package com.upsmart.server.common.codec;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;

/**
 * Created by yuhang on 17-6-2.
 */
public class AES {

    public static byte[] encrypt(byte[] encode, byte[] key) throws Exception {
        if(null == encode || encode.length <=0
                || null == key || key.length <=0){
            return null;
        }
        SecretKeySpec securekeySpec = new SecretKeySpec(key, "AES");
        Cipher cipherEncrypt = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipherEncrypt.init(Cipher.ENCRYPT_MODE, securekeySpec);
        return cipherEncrypt.doFinal(encode);
    }

    public static byte[] decrypt(byte[] decode, byte[] key) throws Exception {
        if(null == decode || decode.length <=0
                || null == key || key.length <=0){
            return null;
        }

        SecretKeySpec securekeySpec = new SecretKeySpec(key, "AES");
        Cipher cipherDecrypt = Cipher.getInstance("AES/ECB/PKCS5Padding"); // Cipher.getInstance("AES/CBC/NoPadding");
        cipherDecrypt.init(Cipher.DECRYPT_MODE, securekeySpec);
        return cipherDecrypt.doFinal(decode);
    }

    /**
     * 16进制字符串转字符串
     * @param hexStr
     * @return
     */
    private static final String HEX_STRING = "0123456789ABCDEF";
    public static String hexToString(String hexStr) {
        return new String(hexToBytes(hexStr));
    }
    public static byte[] hexToBytes(String hexStr) {
        hexStr = hexStr.toUpperCase();
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = HEX_STRING.indexOf(hexs[2 * i]) * 16;
            n += HEX_STRING.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return bytes;
    }
}

package com.upsmart.server.common.codec;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by upsmart on 17-6-2.
 */
public class AESTest {

    @Test
    public void test() throws Exception {

        String key = "50c094c0927145b79577b226c3b4b551";

        String decode = "Y4d3zpSOgQiUN84AItTUKe0EBxSP1oo0xEFXOV0NxJ4";
        decode = decode + "==";
        Base64.setOwnEncoding("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_");
        byte[] bytesDecode = Base64.decodeBase64(decode.getBytes());

        byte[] bk = AES.hexToBytes(key);

//        String encode = "Hello World!";
//        byte[] bytesDecode = AES.encrypt(encode.getBytes(), bk);

        byte[] decrypt = AES.decrypt(bytesDecode, bk);
        String result = new String(decrypt);
        System.out.println(result);

        Assert.assertEquals("965_1496212197724", result);
    }

    @Test
    public void test2(){
        String result = AES.hexToString("48656c6c6f20476f7068657221");
        System.out.println(result);

        Assert.assertEquals("Hello Gopher!", result);
    }
}

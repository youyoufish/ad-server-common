package com.upsmart.server.common.codec;

import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

/**
 * Created by yuhang on 17-6-9.
 */
public class MD5Test {

    @Test
    public void test(){
        try {
            String result = MD5.encrypt("123456");
            System.out.println(result);
            Assert.assertEquals(result, "e10adc3949ba59abbe56e057f20f883e");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}

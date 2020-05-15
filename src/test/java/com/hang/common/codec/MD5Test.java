package com.hang.common.codec;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
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

    @Test
    public void testFileMD5(){
        try {
            String s = MD5.fileMD5("/home/upsmart/works/audience/170920/2017-09_application_1505869503010_0000_00_full.gz", 0);
            Assert.assertEquals(s, "1fe0a4ff2183f9b0539b72fdc170c6f0");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

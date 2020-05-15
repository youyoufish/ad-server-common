package com.hang.common.codec;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

/**
 * Created by yuhang on 17-5-2.
 */
public class GzipTest {

    @Test
    public void testStringGzip(){

        String str = "{[ABC:456]}为统一台湾而奋斗!";
        String encoding = "utf-8";
        byte[] text = Gzip.compressToByte(str, encoding);
        String result = Gzip.uncompressToString(text, encoding);
        Assert.assertEquals(str, result);
    }

    @Test
    public void testStreamGzip() throws IOException {

        String str = "{[ABC:456]}为统一台湾而奋斗!";
        String encoding = "utf-8";
        byte[] text = Gzip.compressToByte(str, encoding);

        InputStream is = new ByteArrayInputStream(text);
        String result = Gzip.uncompressToString(is, encoding);
        Assert.assertEquals(str, result);
    }

    @Test
    public void testFileCompress() throws Exception {
        String file = "/home/upsmart/works/documents/capinfo.csv";
        Gzip.fileCompress(file, false);
    }
    @Test
    public void testFileDecompress() throws Exception {
        String file = "/home/upsmart/works/documents/capinfo.csv.gz";
        String newfile = "/home/upsmart/works/documents/capinfo.csv.111";
        Gzip.fileDecompress(file, newfile);
    }
}

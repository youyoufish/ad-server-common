package com.upsmart.server.common.codec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by yuhang on 17-5-2.
 */
public class Gzip {

    /**
     * 字符串压缩为字节数组
     * @param str
     * @param encoding utf-8
     * @return
     */
    public static byte[] compressToByte(String str, String encoding){
        if (str == null || str.length() == 0) {
            return null;
        }
        GZIPOutputStream gzip = null;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(encoding));
            gzip.finish();
            return out.toByteArray();
        } catch (IOException e) {
        }
        finally {
            try {
                if (null != gzip) {
                    gzip.close();
                }
            }
            catch (IOException e) {
            }
        }
        return null;
    }

    /**
     * 字节数组解压缩后返回字符串
     * @param b
     * @param encoding utf-8
     * @return
     */
    public static String uncompressToString(byte[] b, String encoding) {
        if (b == null || b.length == 0) {
            return null;
        }
        ByteArrayInputStream in = null;
        try{
            in = new ByteArrayInputStream(b);
            return uncompressToString(in, encoding);
        }
        finally {
            if(null != in){
                try {
                    in.close();
                }
                catch (IOException e) {
                }
            }
        }
    }

    public static String uncompressToString(InputStream inStream, String encoding) {
        if (inStream == null) {
            return null;
        }
        GZIPInputStream gzip = null;
        ByteArrayOutputStream out = null;
        try {
            gzip = new GZIPInputStream(inStream);
            out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = gzip.read(buffer)) >= 0) {
                out.write(buffer, 0, len);
            }
            return out.toString(encoding);
        } catch (IOException e) {
        }
        finally {
            try {
                if(null != gzip){
                    gzip.close();
                }
                if(null != out){
                    out.close();
                }
            }
            catch (IOException e) {
            }
        }
        return null;
    }
}

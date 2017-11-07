package com.upsmart.server.common.codec;

import java.io.*;
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
            if(null != out){
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
            if(null != gzip) {
                try {
                    gzip.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    /**
     * 文件解压缩
     * @param oldFile   压缩文件路径
     * @param newFile   解压后的文件路径
     * @throws Exception
     */
    public static void fileDecompress(String oldFile, String newFile) throws Exception {

        File file = new File(oldFile);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try{
            fis = new FileInputStream(file);
            fos = new FileOutputStream(newFile);
            fileDecompress(fis, fos);
        }
        finally {
            if(null != fis) {
                fis.close();
            }
            if(null != fos) {
                fos.close();
            }
        }
    }
    private static void fileDecompress(InputStream is, OutputStream os) throws IOException {
        GZIPInputStream gis = null;
        try{
            gis = new GZIPInputStream(is);
            int count;
            byte data[] = new byte[10240];
            while ((count = gis.read(data, 0, 10240)) != -1) {
                os.write(data, 0, count);
                os.flush();
            }
        }
        finally {
            if(null != gis){
                gis.close();
            }
        }
    }

    /**
     * 文件压缩
     * @param path
     * @param delete
     * @throws Exception
     */
    public static void fileCompress(String path, boolean delete) throws Exception {
        File file = new File(path);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try{
            fis = new FileInputStream(file);
            fos = new FileOutputStream(file.getPath() + ".gz");
            fileCompress(fis, fos);

            if (delete) {
                file.delete();
            }
        }
        finally {
            if(null != fis) {
                fis.close();
            }
            if(null != fos) {
                fos.close();
            }
        }
    }
    private static void fileCompress(InputStream is, OutputStream os) throws Exception {
        GZIPOutputStream gos = null;
        try{
            gos = new GZIPOutputStream(os);
            int count;
            byte data[] = new byte[10240];
            while ((count = is.read(data, 0, 10240)) != -1) {
                gos.write(data, 0, count);
                gos.flush();
            }
            gos.finish();
            gos.flush();
        }
        finally {
            if(null != gos){
                gos.close();
            }
        }
    }
}

package com.upsmart.server.common.codec;

import com.upsmart.server.common.utils.StringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * 文件MD5
     * @param filePath 文件路径
     * @param maxBuffSize 文件缓冲，默认为0
     * @return md5字符串
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String fileMD5(String filePath, int maxBuffSize) throws NoSuchAlgorithmException, IOException {
        if (StringUtil.isNullOrEmpty(filePath)) {
            return null;
        }
        if(maxBuffSize <= 0){
            maxBuffSize = Integer.MAX_VALUE / 100;
        }

        File file = new File(filePath);
        if (!file.isDirectory()) {
            FileInputStream in = null;
            FileChannel channel = null;
            List<MappedByteBuffer> listBuff = new ArrayList<>();
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                in = new FileInputStream(file);
                channel = in.getChannel();

                // 文件块数量，可能还有结余
                long blockNum = file.length() / maxBuffSize;
                for(int i=0; i<blockNum; i++){
                    MappedByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, i * maxBuffSize, maxBuffSize);
                    md.update(byteBuffer);
                    listBuff.add(byteBuffer);
                }
                long remainByte = file.length() - (blockNum * maxBuffSize);
                if(remainByte > 0){
                    MappedByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, (blockNum) * maxBuffSize, remainByte);
                    md.update(byteBuffer);
                    listBuff.add(byteBuffer);
                }

                return MD5.byteToString(md.digest());
            } finally {
                if(null != listBuff && !listBuff.isEmpty()){
                    for(MappedByteBuffer buff : listBuff){
                        releaseBuff(buff); // 释放内存
                    }
                }
                if(null != channel){
                    channel.close();
                }
                if (null != in) {
                    in.close();
                }
            }
        }
        return null;
    }
    /**
     * 释放内存映射文件
     * @param byteBuffer
     */
    private static void releaseBuff(final MappedByteBuffer byteBuffer){
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                try {
                    Method getCleanerMethod = byteBuffer.getClass().getMethod("cleaner", new Class[0]);
                    getCleanerMethod.setAccessible(true);
                    sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod.invoke(byteBuffer, new Object[0]);
                    cleaner.clean();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
}

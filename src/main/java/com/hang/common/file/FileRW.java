package com.hang.common.file;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * 大文件读写
 * @author hang on 2020/9/24
 */
public class FileRW {

    private String filePath;
    private int blockSize;
    private FileDataCallBack fileDataCallBack;

    public FileRW(String filePath, int blockSize, FileDataCallBack callBack){
        this.filePath = filePath;
        this.blockSize = blockSize;
        this.fileDataCallBack = callBack;
    }

    public void readAll() throws IOException {
        FileInputStream fileInputStream = null;
        FileChannel fileChannel = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            fileChannel = fileInputStream.getChannel();
            // 文件总长
            long fileLength = fileChannel.size();
            // 根据块长度切割
            int count = (int)Math.ceil((double)fileLength/ (double)blockSize);
            // 读取文件起始位置
            long startIndex = 0;
            int regionSize = blockSize;
            for(int i=0; i<count; ++i){

                if(fileLength - startIndex < blockSize){
                    regionSize = (int)(fileLength - startIndex);
                }
                byte[] array = new byte[regionSize];
                MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, startIndex, regionSize);
                mappedByteBuffer.get(array);
                // 回调处理
                fileDataCallBack.proc(array);
                cleanBuff(mappedByteBuffer);
                startIndex += regionSize;
            }
        }
        finally {
            fileChannel.close();
            fileInputStream.close();
        }
    }


    /**
     * 由于MappedByteBuffer资源释放有问题，需要手动调用释放
     * @param buffer
     */
    private void cleanBuff(ByteBuffer buffer){
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                try {
                    Method getCleanerMethod = buffer.getClass().getMethod("cleaner", new Class[0]);//获取 cleaner 方法
                    getCleanerMethod.setAccessible(true);
                    sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod.invoke(buffer, new Object[0]);//ok...
                    cleaner.clean();
                } catch (Exception e) {
                }
                return null;
            }
        });
    }


    class TestFileProc implements FileDataCallBack{

        FileOutputStream fileOutputStream;
        public TestFileProc(String filePath) {
            try {
                fileOutputStream = new FileOutputStream(filePath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void proc(byte[] array) {
            try {
                fileOutputStream.write(array);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void clean() throws IOException {
            if(null != fileOutputStream){
                fileOutputStream.close();
            }
        }
    }

    public static void main(String[] args){

    }
}

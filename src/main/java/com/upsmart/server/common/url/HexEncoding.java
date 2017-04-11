package com.upsmart.server.common.url;

import java.nio.ByteOrder;

public abstract class HexEncoding {

    protected static char[] decodeTable;
    protected static byte[] encodeTable;

    static {
        decodeTable = "0123456789abcdef".toCharArray();
        encodeTable = new byte[128];

        for(int i = 0; i < encodeTable.length; ++i)
            encodeTable[i] = (byte)0xff;
        for(int i = '0', j = 0; i <= '9'; ++i, ++j)
            encodeTable[i] = (byte)j;
        for(int i = 'a', j = 10; i <= 'f'; ++i, ++j)
            encodeTable[i] = (byte)j;
        for(int i = 'A', j = 10; i <= 'F'; ++i, ++j)
            encodeTable[i] = (byte)j;

        HexEncoding big = new BigEndianHexEncoding();
        HexEncoding little = new LittleEndianHexEncoding();

        BIG_ENDIAN = big;
        LITTLE_ENDIAN = little;
        DEFAULT = big;
    }

    public static final HexEncoding BIG_ENDIAN;
    public static final HexEncoding LITTLE_ENDIAN;
    public static final HexEncoding DEFAULT;

    public abstract void fromHexCharArray(char[] chars, int cOffset, byte[] bytes, int bOffset, int count);
    public abstract void toHexCharArray(byte[] bytes, int bOffset, char[] chars, int cOffset, int count);

    public byte[] fromHexString(String hexString, int offset, int count) {
        byte[] bytes = new byte[count >> 1];
        fromHexCharArray(hexString.toCharArray(), offset, bytes, 0, count);
        return bytes;
    }

    public String toHexString(byte[] bytes, int offset, int count) {
        char[] chars = new char[count << 1];
        toHexCharArray(bytes, offset, chars, 0, count);
        return new String(chars);
    }

    /// <summary>
    /// convert String into byte array.
    /// assume hex String is written from left to right, and significant bytes comes before
    /// less ones, which means the hex String is written in Big-Endian
    ///
    /// Big-Endian Encoding result: "abcdef" -> byte[3] 0xab 0xcd 0xef
    /// Little-Endian Encoding result: "abcdef" -> byte[3] 0xef 0xcd 0xab
    /// ATTENION: make sure the hexString only contains anyone of the following characeters: '0123456789abcdefABCDEF'
    /// </summary>
    public byte[] fromHexString(String hexString) { return fromHexString(hexString, 0, hexString.length()); }
    public String toHexString(byte[] bytes) { return toHexString(bytes, 0, bytes.length); }

    public abstract ByteOrder getByteOrder();
}

final class BigEndianHexEncoding extends HexEncoding {

    protected BigEndianHexEncoding() {}
    @Override
    public ByteOrder getByteOrder() { return ByteOrder.BIG_ENDIAN; }
    @Override
    public void fromHexCharArray(char[] chars, int cOffset, byte[] bytes, int bOffset, int count) {
        int high4Bits, low4Bits;
        for(int end = cOffset + count, i = cOffset, j = bOffset; i < end; ++i, ++j) {
            high4Bits = encodeTable[chars[i] & 0x7f];
            low4Bits = encodeTable[chars[++i] & 0x7f];
            bytes[j] = (byte)((high4Bits << 4) | low4Bits);
        }
    }
    @Override
    public void toHexCharArray(byte[] bytes, int bOffset, char[] chars, int cOffset, int count) {
        int val = 0;
        for(int end = bOffset + count, i = bOffset, j = cOffset; i < end; ++i, ++j) {
            val = bytes[i] & 0xff;
            chars[j] = decodeTable[val >> 4];
            chars[++j] = decodeTable[val & 0xf];
        }
    }
}

final class LittleEndianHexEncoding extends HexEncoding {

    protected LittleEndianHexEncoding () {}
    @Override
    public ByteOrder getByteOrder() { return ByteOrder.LITTLE_ENDIAN; }
    @Override
    public void fromHexCharArray(char[] chars, int cOffset, byte[] bytes, int bOffset, int count) {
        int high4Bits, low4Bits;
        for(int end = cOffset + count, i = cOffset, j = bOffset + (count >> 1) - 1; i < end; ++i, --j) {
            high4Bits = encodeTable[chars[i] & 0x7f];
            low4Bits = encodeTable[chars[++i] & 0x7f];
            bytes[j] = (byte)((high4Bits << 4) | low4Bits);
        }
    }
    @Override
    public void toHexCharArray(byte[] bytes, int bOffset, char[] chars, int cOffset, int count) {
        int val = 0;
        for(int end = bOffset + count, i = bOffset, j = cOffset + (count << 1) - 1; i < end; ++i, --j) {
            val = bytes[i] & 0xff;
            chars[j] = decodeTable[val & 0xf];
            chars[--j] = decodeTable[val >>  4];
        }
    }
}

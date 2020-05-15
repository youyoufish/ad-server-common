package com.hang.common.utils;

import java.io.UnsupportedEncodingException;

/**
 * byte array convert
 * 
 * @author hang.yu
 * @version 2013-08-06
 */
public final class ByteArrayUtil
{
	public static long byteArrayToLong(byte[] v)
	{
		// Not use BitConverter.ToInt64(v, 0) since it should be portable to
		// Java platform

		long ret = 0;
		for (int i = 0; i < 8; ++i)
		{
			long x = v[i] & 0xFF;
			ret |= x << (i * 8);
		}

		/*
		 * ret |= (uint)v[0] << (0 * 8); ret |= (uint)v[1] << (1 * 8); ret |=
		 * (uint)v[2] << (2 * 8); ret |= (uint)v[3] << (3 * 8); ret |=
		 * (uint)v[4] << (4 * 8); ret |= (uint)v[5] << (5 * 8); ret |=
		 * (uint)v[6] << (6 * 8); ret |= (uint)v[7] << (7 * 8);
		 */

		return ret;
	}

	public static byte[] longToByteArray(long v)
	{
		// Not use BitConverter.GetBytes(v) since it should be portable to Java
		// platform
		byte[] ret = new byte[8];
		for (int i = 0; i < 8; ++i)
		{
			long mask = (long) 0xFF << (i * 8);
			long x = ((v & mask) >> (i * 8)) & 0xFF;
			ret[i] = (byte) x;
		}

		/*
		 * ret[0] = (byte) ((u & 0xFF) >> 0); ret[1] = (byte) ((u & 0xFF00) >>
		 * (1 * 8)); ret[2] = (byte) ((u & 0xFF0000) >> (2 * 8)); ret[3] =
		 * (byte) ((u & 0xFF000000) >> (3 * 8)); ret[4] = (byte) ((u &
		 * 0xFF00000000) >> (4 * 8)); ret[5] = (byte) ((u & 0xFF0000000000) >>
		 * (5 * 8)); ret[6] = (byte) ((u & 0xFF000000000000) >> (6 * 8)); ret[7]
		 * = (byte) ((u & 0xFF00000000000000) >> (7 *8));
		 */

		return ret;
	}
	
	public static byte[] stringToByteArray(String v) throws UnsupportedEncodingException
    {
    	return v.getBytes("UTF-8");
    }

    public static String byteArrayToString(byte[] v) throws UnsupportedEncodingException
    {
    	// Use ToUTF8String since ToString built-in method of Object
    	return new String(v, "UTF-8");
    }
    
    public static byte[] doubleToByteArray(double v) 
    {
    	long intBits = Double.doubleToLongBits(v);
        return longToByteArray(intBits);
    }
    
    public static byte[] floatToByteArray(float data)
    {
        int intBits = Float.floatToIntBits(data);
        return intToByteArray(intBits);
    }
    
    public static byte[] intToByteArray(int data)
    {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (data & 0xff);
        bytes[1] = (byte) ((data & 0xff00) >> 8);
        bytes[2] = (byte) ((data & 0xff0000) >> 16);
        bytes[3] = (byte) ((data & 0xff000000) >> 24);
        return bytes;
    }
    
    public static double byteArrayToDouble(byte[] bytes)
    {
    	long l = byteArrayToLong(bytes);
        return Double.longBitsToDouble(l);
    }
    
    public static float byteArrayToFloat(byte[] bytes)
    {
        return Float.intBitsToFloat(byteArrayToInt(bytes));
    }
    
    public static int byteArrayToInt(byte[] bytes)
    {
        return (0xff & bytes[0]) | (0xff00 & (bytes[1] << 8)) | (0xff0000 & (bytes[2] << 16)) | (0xff000000 & (bytes[3] << 24));
    }
}

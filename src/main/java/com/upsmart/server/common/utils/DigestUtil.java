package com.upsmart.server.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.upsmart.server.common.codec.Base64;

public class DigestUtil
{

	public static final String KEY_SALT = "|AdChina.com.20140117";

	public static final String KEY_ALG = "MD5";

	public static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String encode(String input)
	{
		return encryptionWithMD5(input + KEY_SALT);
	}

	private static String encryptionWithMD5(String content)
	{
		try
		{
			Base64 b64 = new Base64();
			byte[] inBytes = b64.encodeBase64(ByteArrayUtil.stringToByteArray(content));
			String ret = toMD5(inBytes);
			return ret;
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private static String toMD5(byte[] content)
	{
		MessageDigest mdTemp = null;
		try
		{
			mdTemp = MessageDigest.getInstance(KEY_ALG);
			mdTemp.update(content);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++)
			{
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);

		} catch (NoSuchAlgorithmException e)
		{
			return null;
		}
	}
}

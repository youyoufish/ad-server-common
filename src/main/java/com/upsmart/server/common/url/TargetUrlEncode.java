package com.upsmart.server.common.url;

import java.io.UnsupportedEncodingException;

import com.upsmart.server.common.utils.StringUtil;

public final class TargetUrlEncode
{
	private static String CODE_TYPE = "UTF-8";
	private static String KEY_STRING = "adchina.com";
	private static byte[] KEY_BYTES;
	
	static
	{
		try
		{
			KEY_BYTES = KEY_STRING.getBytes(CODE_TYPE);
		}
		catch (UnsupportedEncodingException e)
		{
			KEY_BYTES = new byte[]{19,82,10,21};
		}
	}

	private static byte[] exclusiveOR(byte[] bytes, byte[] keys)
	{
		int iLoop = bytes.length / keys.length;
		if(0 == iLoop)
		{
			for(int j=0; j<bytes.length; ++j)
			{
				bytes[j] ^= keys[j];
			}
		}
		else
		{
			int iIndex = 0;
			for(int i=0; i<iLoop; ++i)
			{
				for(int j=0; j<keys.length; ++j)
				{
					bytes[iIndex++] ^= keys[j];
				}
			}
			int iMod = bytes.length % keys.length;
			for(int j=0; j<iMod; ++j)
			{
				bytes[iIndex++] ^= keys[j];
			}
		}
		
		return bytes;
	}
	
	public static String encode(String data)
	{
		if(StringUtil.isNullOrEmpty(data))
		{
			return null;
		}
		String ret = null;
		try
		{
			byte[] tempBytes = data.getBytes(CODE_TYPE);
			byte[] tempBytesEncoded = exclusiveOR(tempBytes, KEY_BYTES);
			ret = org.apache.commons.codec.binary.Base64.encodeBase64String(tempBytesEncoded);
			ret = ret.replaceAll("=", "-");
		}
		catch (UnsupportedEncodingException e)
		{
		}
		return ret;
	}
	
	public static String decode(String data)
	{
		if(StringUtil.isNullOrEmpty(data))
		{
			return null;
		}
		String ret = null;
		try
		{
			String tempBase64 = data.replaceAll("-", "=");
			byte[] tempBytesEncoded = org.apache.commons.codec.binary.Base64.decodeBase64(tempBase64);
			byte[] tempBytes = exclusiveOR(tempBytesEncoded, KEY_BYTES);
			ret = new String(tempBytes,CODE_TYPE);
		}
		catch (UnsupportedEncodingException e)
		{
		}
		return ret;
	}
}

package com.hang.common.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 字符串处理实用类
 *
 * @author Richard.wang
 * @version 2013-04-17
 */
public final class StringUtil
{
	private static Logger log = LoggerFactory.getLogger(StringUtil.class);

	// 平台统一的换行符
	private final static String newLine = System.getProperty("line.separator");

	public static boolean isNullOrEmpty(String x)
	{
		return x == null || x.length() == 0;
	}

	public static boolean isNullOrWhitespace(String x)
	{
		return x == null || x.trim().length() == 0;
	}

	public static String getNewLine()
	{
		return newLine;
	}

	/**
	 * 将给定字符串转为int型
	 *
	 * @param s
	 * @param defaultValue
	 *            转换失败时的默认值
	 * @return
	 */
	public static int parseInt(String s, int defaultValue)
	{
		return parseInt(s, defaultValue, "");
	}

	/**
	 * 将给定字符串转为int型
	 *
	 * @param s
	 * @param defaultValue
	 *            转换失败时的默认值
	 * @param customLog
	 *            转换失败时自定义log
	 * @return
	 */
	public static int parseInt(String s, int defaultValue, String customLog)
	{
		int result = defaultValue;
		try
		{
			result = Integer.parseInt(s);
		}
		catch (Exception e)
		{
			if (null != customLog && !"".equals(customLog))
			{
				log.debug(customLog);
			}
			result = defaultValue;
		}
		return result;
	}

	/**
	 * 将给定字符串转为double型
	 *
	 * @param s
	 * @param defaultValue
	 *            转换失败时的默认值
	 */
	public static double parseDouble(String s, double defaultValue)
	{
		return parseDouble(s, defaultValue, "");
	}

	/**
	 * 将给定字符串转为double型
	 *
	 * @param s
	 * @param defaultValue
	 *            转换失败时的默认值
	 * @param customLog
	 *            转换失败时自定义log
	 * @return
	 */
	public static double parseDouble(String s, double defaultValue, String customLog)
	{
		double result = defaultValue;
		try
		{
			result = Double.parseDouble(s);
		}
		catch (Exception e)
		{
			if (null != customLog && !"".equals(customLog))
			{
				log.debug(customLog);
			}
			result = defaultValue;
		}

		return result;
	}

	/**
	 * 将给定字符串转为long型
	 *
	 * @param s
	 * @param defaultValue
	 * @return
	 */
	public static long parseLong(String s, long defaultValue)
	{
		return parseLong(s, defaultValue, "");
	}

	/**
	 * 将给定字符串转为long型
	 *
	 * @param s
	 * @param defaultValue
	 *            转换失败时的默认值
	 * @param customLog
	 *            转换失败时自定义log
	 * @return
	 */
	public static long parseLong(String s, long defaultValue, String customLog)
	{
		long result = defaultValue;
		try
		{
			result = Long.parseLong(s);
		}
		catch (Exception e)
		{
			if (null != customLog && !"".equals(customLog))
			{
				log.debug(customLog);
			}
			result = defaultValue;
		}
		return result;
	}

	/**
	 * 将给定字符串转为boolean型
	 *
	 * @param s
	 * @param defaultValue
	 * @return
	 */
	public static boolean parseBoolean(String s, boolean defaultValue)
	{
		return parseBoolean(s, defaultValue,"");
	}
	/**
	 * 将给定字符串转为boolean型
	 *
	 * @param s
	 * @param defaultValue
	 *            转换失败时的默认值
	 * @param customLog
	 *            转换失败时自定义log
	 * @return
	 */
	public static boolean parseBoolean(String s, boolean defaultValue, String customLog)
	{
		boolean result = defaultValue;
		try
		{
			result = Boolean.parseBoolean(s);
		}
		catch (Exception e)
		{
			if (null != customLog && !"".equals(customLog))
			{
				log.debug(customLog);
			}
			result = defaultValue;
		}
		return result;
	}
	/**
	 * Simple path combination
	 *
	 * @param parent
	 *            : parent folder
	 * @param path
	 *            another file/directory path under the parent
	 * @return path combination
	 */
	public static String combinePath(String parent, String path)
	{
		String ret = path;
		if (!isNullOrEmpty(parent))
		{
			int lastIndex = parent.length() - 1;
			if (parent.charAt(lastIndex) == File.separatorChar)
			{
				// The last character is file separator, e.g. '/'
				ret = String.format("%s%s", parent, path);
			}
			else
			{
				ret = String.format("%s%s%s", parent, File.separator, path);
			}
		}
		return ret;
	}

	public static byte[] stringToByteArray(String v)
			throws UnsupportedEncodingException
	{
		return v.getBytes("UTF-8");
	}

	public static String byteArrayToString(byte[] v)
			throws UnsupportedEncodingException
	{
		// Use ToUTF8String since ToString built-in method of Object
		return new String(v, "UTF-8");
	}



	public static String encode(String s, String charSetName)
	{
		String text = "";
		try
		{
			text = URLEncoder.encode(s, charSetName);
		}
		catch (UnsupportedEncodingException e)
		{
			log.debug("StringUtil encode method got exception", e);
		}

		return text;
	}

	public static String decode(String s, String charSetName) throws IllegalArgumentException, UnsupportedEncodingException
	{
		String text =URLDecoder.decode(s, charSetName);
		return text;
	}

	/**
	 * 八进制转十进制
	 *
	 * @param text
	 *            : 指定的八进制字符串
	 * @return String
	 */
	public static String octToDec(String text)
	{
		String z = null;
		int x = parseInt(text, 0);
		int i = 0;
		long a, b = 0;
		while (x > 0)
		{
			a = x % 10;
			x = x / 10;
			b = (long) (b + a * Math.pow(8, i));
			i++;
		}
		z = b + "";
		return z;
	}

	/**
	 * 判断字符是否为数字
	 *
	 * @param c
	 *            : 指定的char
	 * @return boolean
	 */
	public static boolean isNumeric(char c)
	{
		if (c < 48 || c > 57)
		{
			return false;
		}

		return true;
	}

	/**
	 * 将list用逗号(,)分隔成字符串
	 *
	 * @param list
	 *            : 指定的list
	 * @return String
	 */
	public static <T> String listToString(List<T> list)
	{
		return listToString(list, ",");
	}

	public static <T> String hashSetToString(HashSet<T> hashSet)
	{
		return hashSetToString(hashSet,",");

	}
	
	public static <T> String hashSetToString(HashSet<T> hashSet,String separator)
	{
		if (hashSet == null)
		{
			return "";
		}
		else
		{
			return listToString(new ArrayList<T>(hashSet), separator);
		}

	}

	/**
	 * 将list用指定的分隔符组成字符串
	 *
	 * @param list
	 *            :指定的list
	 * @param seperator
	 *            : 分隔符
	 * @return String
	 */
	public static <T> String listToString(List<T> list, String seperator)
	{
		StringBuilder content = new StringBuilder();
		if (list != null)
		{
			for (T item : list)
			{
				content.append(seperator + item.toString());
			}
		}

		if (content.length() > 0)
		{
			return content.substring(seperator.length());
		}
		else
		{
			return "";
		}
	}

	public static String encodeVerticalBar(String text)
	{
		if (!isNullOrEmpty(text))
		{
			return text.replace("|", "%7C");
		}

		return "";
	}

	/**
	 * simple wrapper for String.split, for handling both null and empty string.
	 *
	 * @param s
	 *            string to be split
	 * @param pattern
	 *            the delimiting regular expression
	 * @param limit
	 *            the result threshold, as described above
	 * @return the array of strings computed by splitting this string around
	 *         matches of the given regular expression. never returns null.
	 */
	public static String[] split(String s, String pattern, int limit)
	{
		if (s == null || s.equals(""))
			return new String[0];
		return s.split(pattern, limit);
	}

	/**
	 * simple wrapper for String.split, for handling both null and empty string.
	 *
	 * @param s
	 *            string to be split
	 * @param pattern
	 *            the delimiting regular expression
	 * @return the array of strings computed by splitting this string around
	 *         matches of the given regular expression. never returns null.
	 */
	public static String[] split(String s, String pattern)
	{
		if (s == null || s.equals(""))
			return new String[0];
		return s.split(pattern);
	}

    /**
     * equivalent to C# String.Join
     *
     * @return never return null
     */
    public static String join(char delimiter, Collection<?> c) {
        if(c == null)
            return "";
        return StringUtils.join(c, delimiter);
    }
    /**
     * equivalent to C# String.Join
     *
     * @return never return null
     */
    public static String join(String delimiter, Collection<?> c) {
        if(c == null)
            return "";
        return StringUtils.join(c, delimiter);
    }

    /**
     * equivalent to C# String.Join
     *
     * @return never return null
     */
    public static <T> String join(char delimiter, T[] args) {
        if(args == null)
            return "";
        return StringUtils.join((Object[])args, delimiter);
    }
    /**
     * equivalent to C# String.Join
     *
     * @return never return null
     */
    public static <T> String join(String delimiter, T[] args) {
        if(args == null)
            return "";
        return StringUtils.join((Object[])args, delimiter);
    }
    /**
     * equivalent to C# String.Join
     *
     * @return never return null
     */
    public static String join(char delimiter, Iterator<?> it) {
        if(it == null)
            return "";
        return StringUtils.join(it, delimiter);
    }
    /**
     * equivalent to C# String.Join
     *
     * @return never return null
     */
    public static String join(String delimiter, Iterator<?> it) {
        if(it == null)
            return "";
        return StringUtils.join(it, delimiter);
    }
}

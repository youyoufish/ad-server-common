/**
 * 
 */
package com.hang.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

/**
 * @author Administrator
 * 
 */
public class URLUtil
{

	public static HashMap<String, String> ParseQueryString(String query)
	{
		return ParseQueryString(query, "utf-8");
	}

	public static HashMap<String, String> ParseQueryString(String query,
			String encoding)
	{
		if (query == null)
		{
			throw new NullPointerException("query");
		}
		if (encoding == null)
		{
			throw new NullPointerException("encoding");
		}
		if ((query.length() > 0) && (query.charAt(0) == '?'))
		{
			query = query.substring(1);
		}
		return FillFromString(query, false, encoding);
	}

	private static HashMap<String, String> FillFromString(String s,
			Boolean urlencoded, String encoding)
	{
		HashMap<String, String> httpValueCollectionMap = new HashMap<String, String>();
		try
		{
			int num = (s != null) ? s.length() : 0;
			for (int i = 0; i < num; i++)
			{
				int startIndex = i;
				int num4 = -1;
				while (i < num)
				{
					char ch = s.charAt(i);
					if (ch == '=')
					{
						if (num4 < 0)
						{
							num4 = i;
						}
					}
					else if (ch == '&')
					{
						break;
					}
					i++;
				}
				String str = null;
				String str2 = null;
				if (num4 >= 0)
				{
					str = s.substring(startIndex, num4);
					str2 = s.substring(num4 + 1, i);
				}
				else
				{
					str2 = s.substring(startIndex, i);
				}
				if (urlencoded)
				{
					httpValueCollectionMap.put(
							URLDecoder.decode(str, encoding),
							URLDecoder.decode(str2, encoding));
				}
				else
				{
					httpValueCollectionMap.put(str, str2);
				}
				if ((i == (num - 1)) && (s.charAt(i) == '&'))
				{
					httpValueCollectionMap.put(null, "");
				}
			}
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO: handle exception
		}

		return httpValueCollectionMap;
	}

}

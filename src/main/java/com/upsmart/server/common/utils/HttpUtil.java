package com.upsmart.server.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public final class HttpUtil
{

	private static final HashSet<String> domainPostfixArray= new HashSet<String>();
	
	static
	{
		domainPostfixArray.add("com");
		domainPostfixArray.add("net");
		domainPostfixArray.add("org");
		domainPostfixArray.add("gov");
		domainPostfixArray.add("edu");
		domainPostfixArray.add("cn");
		domainPostfixArray.add("biz");
		domainPostfixArray.add("cc");
		domainPostfixArray.add("tv");
		domainPostfixArray.add("info");
	}
	
	public static String getDomain(HttpServletRequest request)
	{
		String requestDomain = request.getServerName();
		return getDomain(requestDomain);
	}

	public static String getDomain(String serverName)
	{
		if (!StringUtil.isNullOrEmpty(serverName))
		{
			StringBuilder ret = new StringBuilder();
			String[] array = serverName.split("\\.");
			for(int i=array.length-1; i>=0 ; i--)
			{
				if(domainPostfixArray.contains(array[i]))
				{
					ret.insert(0, array[i]);
					ret.insert(0, ".");
					continue;
				}
				else
				{
					ret.insert(0, array[i]);
					break;
				}
			}
			
			return ret.toString();
		}
		return null;
	}

	/**
	 * Send HTTP get request
	 * 
	 * @param requestURL
	 *            the resource to be requested.
	 * @param headers
	 *            HTTP request headers
	 * @return status code
	 * @exception IOException
	 *                if anything wrong happened.
	 * @exception ClassCastException
	 *                if can't create HttpURLConnection
	 */
	public static int doGet(URL requestURL, Map<String, String> headers) throws IOException
	{
		URLConnection urlConn = (HttpURLConnection) requestURL.openConnection();
		HttpURLConnection httpConn = (HttpURLConnection) urlConn;
		httpConn.setRequestMethod("GET");
		if (headers != null)
			for (Map.Entry<String, String> header : headers.entrySet())
				httpConn.setRequestProperty(header.getKey(), header.getValue());
		httpConn.connect();
		try
		{
			return httpConn.getResponseCode();
		}
		catch (IOException e)
		{
			throw e;
		}
		finally
		{
			httpConn.disconnect();
		}
	}

	public static int doPost(URL requestURL, Map<String, String> headers) throws IOException
	{
		return doPost(requestURL, headers, (InputStream) null);
	}

	public static int doPost(URL requestURL, Map<String, String> headers, InputStream body) throws IOException
	{
		URLConnection urlConn = (HttpURLConnection) requestURL.openConnection();
		HttpURLConnection httpConn = (HttpURLConnection) urlConn;
		httpConn.setRequestMethod("POST");
		httpConn.setDoOutput(true);
		if (headers != null)
			for (Map.Entry<String, String> header : headers.entrySet())
				httpConn.setRequestProperty(header.getKey(), header.getValue());
		if (body != null)
			StreamUtils.copy(body, httpConn.getOutputStream(), 8192);
		httpConn.connect();
		try
		{
			return httpConn.getResponseCode();
		}
		catch (IOException e)
		{
			throw e;
		}
		finally
		{
			httpConn.disconnect();
		}
	}

	public static int doPost(URL requestURL, Map<String, String> headers, byte[] body) throws IOException
	{
		URLConnection urlConn = (HttpURLConnection) requestURL.openConnection();
		HttpURLConnection httpConn = (HttpURLConnection) urlConn;
		httpConn.setRequestMethod("POST");
		httpConn.setDoOutput(true);
		if (headers != null)
			for (Map.Entry<String, String> header : headers.entrySet())
				httpConn.setRequestProperty(header.getKey(), header.getValue());
		if (body != null)
			httpConn.getOutputStream().write(body);
		httpConn.connect();
		try
		{
			return httpConn.getResponseCode();
		}
		catch (IOException e)
		{
			throw e;
		}
		finally
		{
			httpConn.disconnect();
		}
	}
}

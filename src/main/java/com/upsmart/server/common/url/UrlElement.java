package com.upsmart.server.common.url;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.upsmart.server.common.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlElement
{
	private static Logger log = LoggerFactory.getLogger(UrlElement.class);
	
	public String RefererHost;
	public String RefererPage;
	public String RefererQuery;
	
	public String TopRefererHost;
	public String TopRefererPage;
	public String TopRefererQuery;
	
	private static Pattern regexDomain=Pattern.compile("(www.)?(.*)",Pattern.CASE_INSENSITIVE);
	private static Pattern regexPage=Pattern.compile(".*(?=\\?)",Pattern.CASE_INSENSITIVE);
	public UrlElement(String referURL)
	{
		this.parseURL(referURL,"");
	}
	
	public UrlElement(String referURL,String topReferURL)
	{
		this.parseURL(referURL,topReferURL);
	}
	
	private void parseURL(String referURL,String topReferURL)
	{
		try
		{
			Matcher matcher=null;
			URL url=null;
		   if(!StringUtil.isNullOrEmpty(referURL))
		   {
				if(!referURL.startsWith("http://"))
				{
					referURL="http://"+referURL;
				}
				url=new URL(referURL);
				
				this.RefererHost=url.getHost();
				matcher=regexDomain.matcher(url.getHost());
				if(matcher.find())
				{
					if(matcher.groupCount()==2)
					{
						this.RefererHost=matcher.group(2);
					}
				}
				this.RefererPage=url.getFile();
				if(!StringUtil.isNullOrEmpty(url.getFile()))
				{
					matcher=regexPage.matcher(url.getFile());
					if(matcher.find())
					{
						this.RefererPage=matcher.group();
					}
				}
				this.RefererQuery=url.getQuery();
		   }
		   
		   if(!StringUtil.isNullOrEmpty(topReferURL))
		   {
				if(!topReferURL.startsWith("http://"))
				{
					topReferURL="http://"+topReferURL;
				}
				url=new URL(topReferURL);
				this.TopRefererHost=url.getHost();
				matcher=regexDomain.matcher(url.getHost());
				if(matcher.find())
				{
					if(matcher.groupCount()==2)
					{
						this.TopRefererHost=matcher.group(2);
					}
				}
				
				this.TopRefererPage=url.getFile();
				if(!StringUtil.isNullOrEmpty(url.getFile()))
				{
					matcher=regexPage.matcher(url.getFile());
					if(matcher.find())
					{
						this.TopRefererPage=matcher.group();
					}
				}
				
				this.TopRefererQuery=url.getQuery();
		   }
		}
		catch (MalformedURLException e)
		{
			log.warn("UrlElement parseURL method got exception:");
			log.warn(e.toString());
		}
		
	}
}

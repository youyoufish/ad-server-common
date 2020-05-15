package com.hang.common.web.browser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hang.common.utils.StringUtil;

public class BrowserUtil
{
	private static Pattern regexIE = Pattern.compile("(?<!((Maxthon|Opera|Tencent|QQBrowser|TheWorld|360SE|MetaSr).+))\\bMSIE[\\s/]?(\\d*)(?!(.+(Maxthon|Opera|Tencent|QQBrowser|TheWorld|360SE|MetaSr)))",Pattern.CASE_INSENSITIVE);
	private static Pattern regexFireFox = Pattern.compile("(?<!((Opera|Opera).+))\\bFirefox[\\s/]?(\\d*)(?!(.+(Opera)))", Pattern.CASE_INSENSITIVE);
	private static Pattern regexChrome = Pattern.compile("\\bChrome[\\s/]?(\\d*)(?!(.+(\\bSE.*MetaSr)))", Pattern.CASE_INSENSITIVE);
	private static Pattern regexOpera = Pattern.compile("\\bOpera[\\s/]?(\\d*)", Pattern.CASE_INSENSITIVE);
	private static Pattern regexSafari = Pattern.compile("\\bVersion[\\s/]?.*\\bSafari", Pattern.CASE_INSENSITIVE);
	private static Pattern regexMaxthon = Pattern.compile("\\bMaxthon[\\s/]?(\\d*)", Pattern.CASE_INSENSITIVE);
	private static Pattern regexTencentTraveler = Pattern.compile("\\bTencent\\s?Traveler[\\s/]?(\\d*)|(\\bTrident[\\s/]?.*\\bQQBrowser)", Pattern.CASE_INSENSITIVE);
	private static Pattern regexSogouBroswer = Pattern.compile("\\bSE.*MetaSr", Pattern.CASE_INSENSITIVE);
	private static Pattern regexTheWorldBrowser = Pattern.compile("\\bTheWorld[\\s/]?(\\d*)", Pattern.CASE_INSENSITIVE);
	private static Pattern regexBrowser360 = Pattern.compile("\\b360SE", Pattern.CASE_INSENSITIVE);
	private static Pattern regexVersion = Pattern.compile("\\bVersion[\\s/]?(\\d*)", Pattern.CASE_INSENSITIVE);
	private static Pattern regexD = Pattern.compile("\\d+", Pattern.CASE_INSENSITIVE);

	private static HashMap<Browser, Pattern> browserRegexMap = null;
	private static ArrayList<Browser> browserIndex = null;
	static
	{
		browserRegexMap = new HashMap<Browser, Pattern>();
		browserRegexMap.put(Browser.BROWSER_360, regexBrowser360);
		browserRegexMap.put(Browser.IE, regexIE);
		browserRegexMap.put(Browser.FIREFOX, regexFireFox);
		browserRegexMap.put(Browser.CHROME, regexChrome);
		browserRegexMap.put(Browser.SOGOU_BROWSER, regexSogouBroswer);
		browserRegexMap.put(Browser.TENCENT_TRAVERLER, regexTencentTraveler);
		browserRegexMap.put(Browser.THEWORLD_BROWSER, regexTheWorldBrowser);
		browserRegexMap.put(Browser.MAXTHON, regexMaxthon);
		browserRegexMap.put(Browser.OPERA, regexOpera);
		browserRegexMap.put(Browser.SAFARI, regexSafari);
		
		browserIndex = new ArrayList<Browser>();
		browserIndex.add(Browser.BROWSER_360);
		browserIndex.add(Browser.IE);
		browserIndex.add(Browser.FIREFOX);
		browserIndex.add(Browser.CHROME);
		browserIndex.add(Browser.SOGOU_BROWSER);
		browserIndex.add(Browser.TENCENT_TRAVERLER);
		browserIndex.add(Browser.THEWORLD_BROWSER);
		browserIndex.add(Browser.MAXTHON);
		browserIndex.add(Browser.OPERA);
		browserIndex.add(Browser.SAFARI);
	}

	public static int getBrowserInt(String userAgent)
	{
		if (StringUtil.isNullOrEmpty(userAgent))
		{
			return 0;
		}
		else
		{
			String ret = getBrowser(userAgent, "0");
			return StringUtil.parseInt(ret, 0);
		}
	}

	public static String getBrowser(String userAgent, String defaultSplitor)
	{
		userAgent = userAgent.toLowerCase();
		for (Browser browser : browserIndex)
		{
			Pattern pattern = browserRegexMap.get(browser);
			if(null == pattern)
			{
				continue;
			}
			
			String ret = "";
			if (browser.equals(Browser.BROWSER_360) || browser.equals(Browser.SOGOU_BROWSER) || browser.equals(Browser.THEWORLD_BROWSER))
			{
				ret = getBrowserButNoVersion(userAgent, defaultSplitor, pattern, browser);
			}
			else
			{
				ret = getBrowserWithVersion(userAgent, defaultSplitor, pattern, browser);
			}
			if (!StringUtil.isNullOrEmpty(ret))
			{
				return ret;
			}
		}

		return String.format("%s%s%s", String.valueOf(Browser.OTHER.getValue()), defaultSplitor, "0");
	}

	private static String getBrowserButNoVersion(String userAgent, String defaultSplitor, Pattern regex, Browser browser)
	{
		Matcher name = regex.matcher(userAgent);
		if (name.find())
		{
			return String.format("%s%s", String.valueOf(browser.getValue()), defaultSplitor);
		}
		return null;
	}

	private static String getBrowserWithVersion(String userAgent, String defaultSplitor, Pattern regex, Browser browser)
	{
		Matcher name = regex.matcher(userAgent);
		if (name.find())
		{
			String version = "";
			Matcher versionname = regexVersion.matcher(userAgent);
			if (versionname.find())
			{
				version = getVersion(versionname.group());
			}
			else
			{
				version = getVersion(name.group());
			}
			return String.format("%s%s%s", String.valueOf(browser.getValue()), defaultSplitor, version);
		}
		return null;
	}

	public static String getVersion(String content)
	{
		Matcher matcher = regexD.matcher(content);
		while (matcher.find())
		{
			return matcher.group(0);
		}
		return "";
	}
}
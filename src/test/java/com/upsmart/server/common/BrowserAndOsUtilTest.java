package com.upsmart.server.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.upsmart.server.common.browser.BrowserUtil;
import com.upsmart.server.common.os.OperatingSystemUtil;

import junit.framework.TestCase;

public class BrowserAndOsUtilTest extends TestCase
{
	public HashMap<String, ArrayList<String>> browser = new HashMap<String, ArrayList<String>>();

	public void testGetBrowserOs()
	{
		for (Entry<String, ArrayList<String>> item : browser.entrySet())
		{
			String ua = item.getKey();
			ArrayList<String> result = item.getValue();
			int browser = Integer.parseInt(result.get(0));
			String os = result.get(1);
			int parseBrowser = BrowserUtil.getBrowserInt(ua);
			String parseOS = OperatingSystemUtil.getOS(ua);
			
			System.out.println(String.format("%d\t%s\t%s",parseBrowser,parseOS,ua));
			
			assertTrue(parseBrowser == browser);
			assertTrue(parseOS.equalsIgnoreCase(os));
		}
	}
	
	// TODO:
	// Mozilla/5.0+(iPhone;+CPU+iPhone+OS+6_1_1+like+Mac+OS+X)+AppleWebKit/536.26+(KHTML,+like+Gecko)+Mobile/10B145+MicroMessenger/5.3

	public void setUp()
	{
		ArrayList<String> result = new ArrayList<String>();

		// the world
		result.add("90");
		result.add("Windows NT 6.1");
		browser.put("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.2; TheWorld)",result);

		result = new ArrayList<String>();
		result.add("90");
		result.add("Windows NT 5.1");
		browser.put("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; InfoPath.2; .NET CLR 2.0.50727; TheWorld)", result);

		// sogou
		result = new ArrayList<String>();
		result.add("80");
		result.add("Windows NT 6.1");
		browser.put("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36 SE 2.X MetaSr 1.0", result);

		// tencent/qq
		result = new ArrayList<String>();
		result.add("705");
		result.add("Windows NT 6.1");
		browser.put("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; QQBrowser/7.5.17872.400)", result);
		result = new ArrayList<String>();
		result.add("704");
		result.add("Windows NT 5.1");
		browser.put("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; TencentTraveler 4.0; GTB6.3; QQDownload 625; (R1 1.6))", result);
		result = new ArrayList<String>();
		result.add("704");
		result.add("Windows NT 6.0");
		browser.put("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; TencentTraveler 4.0; QQDownload 667; SLCC1; .NET CLR 2.0.50727; .NET CLR 3.0.04506)", result);
		result = new ArrayList<String>();
		result.add("704");
		result.add("Windows NT 6.0");
		browser.put("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0; TencentTraveler 4.0; Trident/4.0; SLCC1; Media Center PC 5.0; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30618)",result);

		// maxthon
		result = new ArrayList<String>();
		result.add("603");
		result.add("Windows NT 6.0");
		browser.put("Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US) AppleWebKit/533.1 (KHTML, like Gecko) Maxthon/3.0.8.2 Safari/533.1", result);
		result = new ArrayList<String>();
		result.add("603");
		result.add("Windows NT 6.1");
		browser.put("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; Maxthon/3.0)", result);
		result = new ArrayList<String>();
		result.add("602");
		result.add("Windows NT 6.1");
		browser.put("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/4.0; WOW64; Trident/5.0; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 2.0.50727; Media Center PC 6.0; Maxthon 2.0)",result);
		result = new ArrayList<String>();
		result.add("602");
		result.add("Windows NT 5.1");
		browser.put("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1) ; .NET CLR 1.1.4322; .NET CLR 2.0.50727; MAXTHON 2.0)",result);

		// safari
		result = new ArrayList<String>();
		result.add("506");
		result.add("ios");
		browser.put("Mozilla/5.0 (iPad; CPU OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5355d Safari/8536.25", result);
		result = new ArrayList<String>();
		result.add("505");
		result.add("Mac OS X 10_6");
		browser.put("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/537.13+ (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2", result);
		result = new ArrayList<String>();
		result.add("505");
		result.add("Windows NT 6.1");
		browser.put("Mozilla/5.0 (Windows; U; Windows NT 6.1; fr-FR) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27", result);
		result = new ArrayList<String>();
		result.add("504");
		result.add("Mac OS X 10_6");
		browser.put("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_2; nb-no) AppleWebKit/533.16 (KHTML, like Gecko) Version/4.1 Safari/533.16", result);
		result = new ArrayList<String>();
		result.add("505");
		result.add("ios");
		browser.put("Mozilla/5.0+(iPhone;+U;+CPU+iPhone+OS+5_0+like+Mac+OS+X;+en-us)+AppleWebKit/534.46+(KHTML,+like+Gecko)+Version/5.1+Mobile/9A334+Safari/7534.48.3",result);

		// opera
		result = new ArrayList<String>();
		result.add("4010");
		result.add("Windows NT 5.2");
		browser.put("Opera/9.80 (Windows NT 5.2; U; zh-cn) Presto/2.6.30 Version/10.63", result);
		result = new ArrayList<String>();
		result.add("4011");
		result.add("Windows NT 6.1");
		browser.put("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; de) Opera 11.51", result);
		result = new ArrayList<String>();
		result.add("4012");
		result.add("Windows NT 5.1");
		browser.put("Opera/9.80 (Windows NT 5.1; U; zh-sg) Presto/2.9.181 Version/12.00", result);
		result = new ArrayList<String>();
		result.add("4012");
		result.add("Windows NT 6.0");
		browser.put("Mozilla/5.0 (Windows NT 6.0; rv:2.0) Gecko/20100101 Firefox/4.0 Opera 12.14", result);
		result = new ArrayList<String>();
		result.add("4011");
		result.add("Android 2.2");
		browser.put("Opera/9.80 (Android 2.2.1; Linux; Opera Mobi/ADR-1107051709; U; pl) Presto/2.8.149 Version/11.10", result);
		result = new ArrayList<String>();
		result.add("4010");
		result.add("Android 2.2");
		browser.put("Opera/9.80 (Android 2.2; Opera Mobi/-2118645896; U; pl) Presto/2.7.60 Version/10.5", result);
		result = new ArrayList<String>();
		result.add("4011");
		result.add("ios");
		browser.put("Opera/9.80 (iPad; Opera Mini/7.1.32694/27.1407; U; en) Presto/2.8.119 Version/11.10", result);

		// chrome
		result = new ArrayList<String>();
		result.add("3032");
		result.add("Windows NT 6.2");
		browser.put("Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36", result);
		result = new ArrayList<String>();
		result.add("3031");
		result.add("Windows NT 5.1");
		browser.put("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36", result);
		result = new ArrayList<String>();
		result.add("3030");
		result.add("Windows NT 6.2");
		browser.put("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.17 Safari/537.36", result);
		result = new ArrayList<String>();
		result.add("3029");
		result.add("");
		browser.put("Mozilla/5.0 (X11; CrOS i686 4319.74.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.57 Safari/537.36", result);
		result = new ArrayList<String>();
		result.add("3042");
		result.add("Mac OS X 10_10");
		browser.put("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.90 Safari/537.36", result);
		
		// firefox
		result = new ArrayList<String>();
		result.add("2025");
		result.add("Mac OS X 10.6");
		browser.put("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:25.0) Gecko/20100101 Firefox/25.0", result);
		result = new ArrayList<String>();
		result.add("2023");
		result.add("Windows NT 6.1");
		browser.put("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:23.0) Gecko/20131011 Firefox/23.0", result);
		result = new ArrayList<String>();
		result.add("2021");
		result.add("Windows NT 6.1");
		browser.put("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20130330 Firefox/21.0", result);
		result = new ArrayList<String>();
		result.add("2020");
		result.add("Windows NT 6.2");
		browser.put("Mozilla/5.0 (Windows NT 6.2; Win64; x64;) Gecko/20100101 Firefox/20.0", result);
		result = new ArrayList<String>();
		result.add("2024");
		result.add("Linux");
		browser.put("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:24.0) Gecko/20100101 Firefox/24.0", result);

		// internet explore
		result = new ArrayList<String>();
		result.add("1010");
		result.add("Windows NT 6.1");
		browser.put("Mozilla/5.0 (compatible; MSIE 10.6; Windows NT 6.1; Trident/5.0; InfoPath.2; SLCC1; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET CLR 2.0.50727) 3gpp-gba UNTRUSTED/1.0", result);
		result = new ArrayList<String>();
		result.add("1010");
		result.add("Mac OS X 10_7");
		browser.put("Mozilla/5.0 (compatible; MSIE 10.0; Macintosh; Intel Mac OS X 10_7_3; Trident/6.0)", result);
		result = new ArrayList<String>();
		result.add("1010");
		result.add("Windows NT 6.1");
		browser.put("Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/4.0; InfoPath.2; SV1; .NET CLR 2.0.50727; WOW64)", result);
		result = new ArrayList<String>();
		result.add("109");
		result.add("Windows NT 6.1");
		browser.put("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; Media Center PC 6.0; InfoPath.3; MS-RTC LM 8; Zune 4.7)", result);
		result = new ArrayList<String>();
		result.add("109");
		result.add("Windows NT 6.1");
		browser.put("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 2.0.50727; Media Center PC 6.0)", result);
		result = new ArrayList<String>();
		result.add("109");
		result.add("Windows NT 6.1");
		browser.put("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/4.0; GTB7.4; InfoPath.1; SV1; .NET CLR 2.8.52393; WOW64; en-US)", result);
		result = new ArrayList<String>();
		result.add("108");
		result.add("Windows NT 6.1");
		browser.put("Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0; GTB7.4; InfoPath.2; SV1; .NET CLR 3.3.69573; WOW64; en-US)", result);
		result = new ArrayList<String>();
		result.add("108");
		result.add("Windows NT 5.1");
		browser.put("Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; SLCC1; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET CLR 1.1.4322)", result);
		result = new ArrayList<String>();
		result.add("108");
		result.add("Windows NT 6.1");
		browser.put("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; InfoPath.3; .NET4.0C; .NET4.0E; .NET CLR 3.5.30729; .NET CLR 3.0.30729; MS-RTC LM 8)",result);
		result = new ArrayList<String>();
		result.add("107");
		result.add("Windows NT 5.1");
		browser.put("Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 5.1; Media Center PC 3.0; .NET CLR 1.0.3705; .NET CLR 1.1.4322; .NET CLR 2.0.50727; InfoPath.1)", result);
		result = new ArrayList<String>();
		result.add("107");
		result.add("");
		browser.put("Mozilla/5.0 (MSIE 7.0; Macintosh; U; SunOS; X11; gu; SV1; InfoPath.2; .NET CLR 3.0.04506.30; .NET CLR 3.0.04506.648)", result);
		result = new ArrayList<String>();
		result.add("107");
		result.add("Windows NT 5.1");
		browser.put("Mozilla/4.0 (Windows; MSIE 7.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727)", result);
		result = new ArrayList<String>();
		result.add("106");
		result.add("Windows XP");
		browser.put("Mozilla/4.0 (compatible; MSIE 6.1; Windows XP; .NET CLR 1.1.4322; .NET CLR 2.0.50727)", result);
		result = new ArrayList<String>();
		result.add("106");
		result.add("Windows NT 5.0");
		browser.put("Mozilla/4.0 (compatible; MSIE 6.0b; Windows NT 5.0; YComp 5.0.0.0) (Compatible; ; ; Trident/4.0)", result);
		result = new ArrayList<String>();
		result.add("106");
		result.add("");
		browser.put("Mozilla/4.0 (X11; MSIE 6.0; i686; .NET CLR 1.1.4322; .NET CLR 2.0.50727; FDM)", result);
		result = new ArrayList<String>();
		result.add("106");
		result.add("Windows NT 5.1");
		browser.put("Mozilla/4.0 (Compatible; Windows NT 5.1; MSIE 6.0) (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)", result);
		result = new ArrayList<String>();
		result.add("109");
		result.add("Windows Phone");
		browser.put("Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0)", result);
	
		
	}
}

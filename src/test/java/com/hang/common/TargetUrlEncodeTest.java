package com.hang.common;

import com.hang.common.web.url.TargetUrlEncode;

import junit.framework.TestCase;

public class TargetUrlEncodeTest extends TestCase
{
	public void setUp()
	{
	}

	public void tearDown()
	{
	}
	
	public void testTargetUrlEncode() throws Exception
	{
		String requestString1 = "http://e.mbm.cn.miaozhen.com";
		String requestString2 = "http://www.adchina.com#1";
		String requestString3 = "http://acs86.com";
		String requestString4 = "http://";
		String requestString5 = "http://8crvta.c.admaster.com.cn";
		String requestString6 = "http://mlt01.com";
		String requestString7 = "http://asdfajfl_aajl-dfjlajdflajdfakdjfladjfadjf.com.cn/adfadafad";
		
		
		
		int i = requestString7.indexOf("/", 7);
		String s1 =requestString7.substring(0, i);
		String s2 =requestString7.substring(i);
		
		String base1 = TargetUrlEncode.encode(requestString1);
		String result1 = TargetUrlEncode.decode(base1);
		System.out.println("base64_1:"+ base1);
		System.out.println("result_1:"+ result1);
		assertSame(requestString1.equals(result1),true);
		
		String base2 = TargetUrlEncode.encode(requestString2);
		String result2 = TargetUrlEncode.decode(base2);
		System.out.println("base64_2:"+ base2);
		System.out.println("result_2:"+ result2);
		assertSame(requestString2.equals(result2),true);
		
		String result3 = TargetUrlEncode.decode(TargetUrlEncode.encode(requestString3));
		System.out.println("result3:"+ result3);
		assertSame(requestString3.equals(result3),true);
		
		String result4 = TargetUrlEncode.decode(TargetUrlEncode.encode(requestString4));
		System.out.println("result4:"+ result4);
		assertSame(requestString4.equals(result4),true);
		
		String result5 = TargetUrlEncode.decode(TargetUrlEncode.encode(requestString5));
		System.out.println("result5:"+ result5);
		assertSame(requestString5.equals(result5),true);
		
		String result6 = TargetUrlEncode.decode(TargetUrlEncode.encode(requestString6));
		System.out.println("result6:"+ result6);
		assertSame(requestString6.equals(result6),true);
		
		String result7 = TargetUrlEncode.decode(TargetUrlEncode.encode(requestString7));
		System.out.println("result7:"+ result7);
		assertSame(requestString7.equals(result7),true);
		
		String result8 = TargetUrlEncode.decode("CRAXGExdAAtRCUhTAgZGCgBPQwoOAhsMBgZHDQ5D");
		System.out.println("result8:"+ result8);
		assertSame("http%3a%2f%2fe.cn.miaozhen.com".equals(result8),true);
		
		String result9 = TargetUrlEncode.decode("CRAXGExdAAtRCUhTAgZGBAwMAAABQwwNAgcTBgRATQwCDA--");
		System.out.println("result9:"+ result9);
		assertSame("http%3a%2f%2fe.mbm.cn.miaozhen.com".equals(result9),true);

	}
}

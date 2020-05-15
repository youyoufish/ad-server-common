package com.hang.common;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import com.hang.common.utils.IPUtil;
import org.easymock.EasyMock;

import junit.framework.TestCase;

public class IPUtilsTest  extends TestCase
{ 
	public void testConvertIPToLong() throws UnknownHostException
	{
		long v1 = IPUtil.convertIPToLong("203.156.207.78");
		long v2 = Long.parseLong("3416051534");
		assertTrue(v1 == v2);
		
		long lIp = IPUtil.convertIPToLong("220.191.226.142");
		String ip = IPUtil.convertLongToIP(lIp);
		assertEquals("220.191.226.142", ip);
		
		int iIp = IPUtil.convertIPToLongForCSharp("220.191.226.142");
		ip = IPUtil.convertLongToIPForCSharp(iIp);
		assertTrue(-1897742372 == iIp);
		assertEquals("220.191.226.142", ip);
	}

	public void testGetIpAddress() throws UnknownHostException
	{
		HttpServletRequest request1 = EasyMock.createMock(HttpServletRequest.class);
		EasyMock.expect(request1.getHeader("x-forwarded-for")).andReturn("unknown, 220.191.226.142");
		EasyMock.expect(request1.getHeader("x-real_ip")).andReturn("220.191.226.142");
		EasyMock.expect(request1.getRemoteAddr()).andReturn("192.168.8.234");
		EasyMock.replay(request1);
		String ip = IPUtil.getIpAddress(request1);
		assertEquals("220.191.226.142", ip);
	}


	public void testGetLocalIp() throws IOException {

		String ip= IPUtil.getLocalIp();
		assertEquals("192.168.87.251",ip);

	}
}

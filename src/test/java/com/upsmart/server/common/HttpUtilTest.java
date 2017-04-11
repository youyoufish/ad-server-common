package com.upsmart.server.common;

import java.net.UnknownHostException;

import com.upsmart.server.common.utils.HttpUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HttpUtilTest
{
	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void testGetIPDetailSuccess() throws UnknownHostException
	{
		Assert.assertEquals(HttpUtil.getDomain("track.cbsi.com.cn"), 	"cbsi.com.cn");
		Assert.assertEquals(HttpUtil.getDomain("cbsi.com.cn"), 			"cbsi.com.cn");
		Assert.assertEquals(HttpUtil.getDomain("track.cbsi.com"), 		"cbsi.com");
		Assert.assertEquals(HttpUtil.getDomain("cbsi.com"), 			"cbsi.com");
		Assert.assertEquals(HttpUtil.getDomain("a.track.cbsi.com"), 	"cbsi.com");
		Assert.assertEquals(HttpUtil.getDomain("a.track.cbsi.com.cn"), 	"cbsi.com.cn");
	}
}

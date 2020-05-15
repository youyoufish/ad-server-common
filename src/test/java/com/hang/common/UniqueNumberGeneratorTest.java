package com.hang.common;

import java.util.HashSet;

import com.hang.common.uniquenumber.UniqueNumberGenerator;

import junit.framework.TestCase;

public class UniqueNumberGeneratorTest extends TestCase 
{
	public UniqueNumberGeneratorTest(String name) 
	{
		super(name);
	}
	public void setUp()
	{
	}
	public void tearDown()
	{
	}
	public void testGetInstance() 
	{
		UniqueNumberGenerator A = UniqueNumberGenerator.getInstance(153);
		UniqueNumberGenerator B = UniqueNumberGenerator.getInstance(153);
		assertSame("UniqueNumberGen instance", A, B);
	}
	public void testNext() 
	{
		HashSet<Long> randomValue = new HashSet<Long>(); 
		for(int i=0; i<1000000; i++) // 循环100w次，验证是否有重复的id生成
		{
			Long l = UniqueNumberGenerator.getInstance(153).next();
			if(!randomValue.contains(l))
			{
				//System.out.print(String.format("%d\r\n", l));
				randomValue.add(l);
			}
			else
			{
				fail(String.format("%d, there is same ID in UniqueNumber!", l));
			}
		}
	}
}

package com.hang.common.web.os;

public enum OS
{
	Other(0), 
	Windows(1), 
	Linux(2), 
	MacOS(3), 
	IOS(4), 
	Android(5), 
	WindowsCE(6), 
	WindowsPhone(7);

	private int value = 0;

	private OS(int value)
	{
		this.value = value;
	}

	public int getValue()
	{
		return this.value;
	}

}

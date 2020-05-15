package com.hang.common.uniquenumber;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * get unique number for id
 * 
 * @author hang.yu
 * @version 2013-05-02
 */
public class UniqueNumberGenerator
{
	/**
	 * 2007.1.1
	 */
	private static final long CONST_TIME;

	private int sequenceNum;

	private int machineId;

	static
	{
		GregorianCalendar cal = new GregorianCalendar(2007, 1, 1);
		CONST_TIME = cal.getTimeInMillis();
	}

	/**
	 * static unique instance of the class
	 */
	private static UniqueNumberGenerator thisInstance = null;

	/**
	 * get unique static instance of class
	 * 
	 * @param machineId
	 */
	public static UniqueNumberGenerator getInstance(int machineId)
	{
		if (null == thisInstance)
		{
			thisInstance = new UniqueNumberGenerator(machineId);
		}
		return thisInstance;
	}

	/**
	 * constructor
	 * 
	 * @param machineId
	 */
	private UniqueNumberGenerator(int machineId)
	{
		this.sequenceNum = 0;
		this.machineId = 0;
		this.machineId = machineId;
	}

	/**
	 * get unique number
	 * 
	 * To use '0x7fff' because there're no unsigned types for java.
	 * The highest bit set 1 means negative number.
	 * 
	 * @return unique number
	 */
	public long next()
	{
		Date dateNow = new Date();
		long timeSpan = dateNow.getTime() - CONST_TIME;

		int seq = 0;
		synchronized (this)
		{
			seq = sequenceNum;
			sequenceNum++;
		}
		
		// convert to long is very important
		return (long)(this.machineId * 0x7fff + seq) * 0x7fffffff + timeSpan; 
	}
}

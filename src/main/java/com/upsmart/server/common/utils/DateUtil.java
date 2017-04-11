package com.upsmart.server.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * class for date time format
 * 
 * SimpleDateFormat is not thread safety, so every time format function should create an new SimpleDateFormat instance.
 * 
 * @author Richard.wang
 * @version 2013-11-09
 */

public final class DateUtil
{
    private static Logger log = LoggerFactory.getLogger(DateUtil.class);
    
    // default format
    public static final String DEFAULT_DATE_FORMAT_STRING = "yyyy/MM/dd HH:mm:ss";

    /**
     * default format
     * 
     * @see DEFAULT_DATE_FORMAT_STRING
     * 
     * @param date instance of Date
     * @return String result
     */
    public static String format(Date date)
    {
        return format(date, DEFAULT_DATE_FORMAT_STRING);
    }
    
    /**
     * special format
     * 
     * @param date instance of Date
     * @param formatString special format
     * @return String result
     */
    public static String format(Date date, String formatString)
    {
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
            return dateFormat.format(date);
        }
        catch(Exception ex)
        {
        	log.warn(String.format("DateUtil format method got exception:%s %s", formatString,ex.getMessage()));
        }
        return null;
    }
    
    /**
     *  default format
     *  
     * @see DEFAULT_DATE_FORMAT_STRING
     * 
     * @param date in String
     * @return instance of Date
     */
    public static Date parseDate(String date) 
    {
        return parseDate(date, DEFAULT_DATE_FORMAT_STRING);
    }
    
    /**
     * special format
     * 
     * @param date
     * @param format
     * @return instance of Date
     */
    public static Date parseDate(String date, String format) 
    {
        Date ret = null;
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            ret = dateFormat.parse(date);
        }
        catch(Exception ex)
        {
        	log.warn(String.format("DateUtil parseDate method got exception:%s %s", format, ex.getMessage()));
        }
       
        return ret;
    }
    
    public static Date addMinute(int minute)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }
    public static Date addMinute(Date time, int minute)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }
    
    public static Date addSecond(int second)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }
    public static Date addSecond(Date time, int second)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }
    
    public static Date addMilliSecond(int milliSecond)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MILLISECOND, milliSecond);
        return calendar.getTime();
    }
    public static Date addMilliSecond(Date time, int milliSecond)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.MILLISECOND, milliSecond);
        return calendar.getTime();
    }
    
    public static Date getDateFromMilliSecond(long time)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.getTime();
    }
    
    /**
     * 比较两个Date对象是否在分钟级别上相等
     * 
     * @param date Date实例
     * @param anotherDate Date实例
     * @return 相等返回true，否则返回false
     */
    public static boolean equalsInMinute(Date date, Date anotherDate)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        
        Calendar anotherCalendar = Calendar.getInstance();
        anotherCalendar.setTime(anotherDate);
        int anotherYear = anotherCalendar.get(Calendar.YEAR);
        int anotherMonth = anotherCalendar.get(Calendar.MONTH);
        int anotherDay = anotherCalendar.get(Calendar.DAY_OF_MONTH);
        int anotherHour = anotherCalendar.get(Calendar.HOUR);
        int anotherMinute = anotherCalendar.get(Calendar.MINUTE);
        
        return year == anotherYear && month == anotherMonth && day == anotherDay 
                && hour == anotherHour && minute == anotherMinute;
    }
    
    /**
     * Date convert to Long
     * 
     * @param date
     * @return long
     */
    public static long dateToLong(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		long x = 0;

		// Second can be represented within 6 bits: 2^6 = 64
		x = cal.get(Calendar.SECOND) & 0x3F;
		long second = x << 0;

		// Minute can be represented within 6 bits: 2^6 = 64
		x = cal.get(Calendar.MINUTE) & 0x3F;
		long minute = x << 6;

		// Hour can be represented within 5 bits: 2^5 = 32
		x = cal.get(Calendar.HOUR_OF_DAY) & 0x1F;
		long hour = x << 12;

		// Day can be represented within 5 bits: 2^5 = 32
		x = cal.get(Calendar.DAY_OF_MONTH) & 0x1F;
		long day = x << 17;

		// Month can be represented within 4 bits : 2^4 = 16
		x = (cal.get(Calendar.MONTH)+1) & 0x0F;
		long month = x << 22;

		// Year represented within 12 bits (0 -- 4095) : 2^12 = 4096
		x = cal.get(Calendar.YEAR) & 0x0FFF;
		long year = x << 26;

		return (year | month | day | hour | minute | second);
	}

    /**
     * long convert to Date
     * 
     * @param v long of Date
     * @return instance of Date
     */
	public static Date longToDate(long v)
	{
		// TODO: implementation
		// Mask: 00111111
		int second = (int)((v & 0x3F) >> 0);

		// Mask: 1111 1100 0000
		int minute = (int)((v & 0xFC0) >> 6);

		// Mask: 0001 1111 0000 0000 0000
		int hour = (int)((v & 0x1F000) >> 12);

		// Mask: 0011 1110 0000 0000 0000 0000
		int day = (int)((v & 0x3E0000) >> 17);

		// Mask: 0011 1100 0000 0000 0000 0000 0000
		int month = (int)((v & 0x3C00000) >> 22);
		month-=1;

		// Mask: 0011 1111 1111 1100 0000 0000 0000 0000 0000 0000
		int year = (int)((v & 0x3FFC000000L) >> 26);

		Calendar cal = new GregorianCalendar(year, month, day, hour, minute, second);
		return cal.getTime();
	}

    /**
     * This tricky timestamp is counted in seconds to comfort AdServer.
     */
    public static long toUnixTimestamp(Date date) { return date.getTime() / 1000; }

    /**
     * This tricky timestamp is counted in seconds to comfort AdServer.
     */
    public static Date fromUnixTimestamp(long timestamp) { return new Date(timestamp * 1000); }
    
    /**
    * get the first day of the week of date
    * 
    * @param date
    * @return
    */
    public static Date getFirstDayOfWeek(Date date) 
    { 
    	Calendar cal = Calendar.getInstance();
    	cal.setFirstDayOfWeek(Calendar.MONDAY);
    	cal.setTime(date);
    	cal = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH));
    	cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek()); // Monday
    	return cal.getTime ();
    }
    
    /**
     * the week count between two date.
     * 
     * @param from
     * @param to
     * @return
     */
    public static long getWeekTimespan(Date from, Date to)
    {
    	long between = (to.getTime() - from.getTime())/1000; // convert to second
    	long weekspan = (between/(24*3600)) / 7;
		return weekspan;
    }
}

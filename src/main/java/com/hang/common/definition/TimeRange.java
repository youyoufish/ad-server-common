package com.hang.common.definition;

import java.util.Date;
//import java.util.Objects;

public class TimeRange
{
	public Date StartTime, EndTime;

	public TimeRange(Date begin, Date end)
	{		
		if(null == begin || null == end)
		{
			throw new NullPointerException("Begin date or end date is null");
		}
		this.StartTime = begin; //Objects.requireNonNull(begin);
		this.EndTime = end; //Objects.requireNonNull(end); 
	}

	@Override
	public boolean equals(Object obj)
	{
		return (obj instanceof TimeRange) ? equals((TimeRange) obj) : super
				.equals(obj);
	}

	public boolean equals(TimeRange other)
	{
		return StartTime.equals(other.StartTime) && EndTime.equals(other.EndTime);
	}
	
	@Override
	public int hashCode()
	{
		return StartTime.hashCode() ^ EndTime.hashCode();
	}
	 
 
}

package com.hang.common.longitudelatitude;

/**
 * 经纬度结构
 * 
 * @author hang.yu
 *
 */
public class LongitudeLatitude
{
	/**
	 * 经度
	 */
	public double longitude;
	
	/**
	 * 纬度
	 */
	public double latitude;
	
	public LongitudeLatitude()
	{
		longitude = Double.NaN;
		latitude = Double.NaN;
	}
	
	/**
	 * 
	 * @param longitude 经度
	 * @param latitude 纬度
	 */
	public LongitudeLatitude(double longitude, double latitude)
	{
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	@Override
	public String toString()
	{
		if (isEmpty())
		{
			return "";
		}
		return longitude + "," + latitude;
	}

	public boolean isEmpty()
	{
		return Double.isNaN(longitude) || Double.isNaN(latitude);
	}
	
	public double distance(LongitudeLatitude position)
	{
		return LongitudeLatitudeUtil.distance(this, position);
	}
}

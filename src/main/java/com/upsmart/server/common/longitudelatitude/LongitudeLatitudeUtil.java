package com.upsmart.server.common.longitudelatitude;

public class LongitudeLatitudeUtil
{
	private final static double EarthRadius = 6378.137;// 地球半径
	
	/**
	 * 
	 * @param position1 位置1
	 * @param position2 位置2
	 * @return 米
	 */
	public static double distance(LongitudeLatitude position1,LongitudeLatitude position2)
	{
		double radLat1 = rad(position1.latitude);
		double radLat2 = rad(position2.latitude);
		double latDValue = radLat1 - radLat2;
		double lonDValue = rad(position1.longitude) - rad(position2.longitude);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(latDValue / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2)
		        * Math.pow(Math.sin(lonDValue / 2), 2)));
		s = s * EarthRadius;
		s = Math.round(s * 10000) / 10000.0;
		return s * 1000;
	}
	
	/**
	 * 
	 * @param latitude1 	位置1纬度
	 * @param longitude1	位置1经度
	 * @param latitude2		位置2纬度
	 * @param longitude2	位置2经度
	 * @return	米
	 */
	public static double distance(double latitude1, double longitude1, double latitude2, double longitude2)
	{
		double radLat1 = rad(latitude1);
		double radLat2 = rad(latitude2);
		double latDValue = radLat1 - radLat2;
		double lonDValue = rad(longitude1) - rad(longitude2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(latDValue / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2)
		        * Math.pow(Math.sin(lonDValue / 2), 2)));
		s = s * EarthRadius;
		s = Math.round(s * 10000) / 10000.0;
		return s * 1000;
	}

	/**
	 * 弧度
	 * 
	 * @param e
	 * @return
	 */
	private static double rad(double e)
	{
		return e * Math.PI / 180.0;
	}
}

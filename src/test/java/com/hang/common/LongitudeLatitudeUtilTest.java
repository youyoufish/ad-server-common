package com.hang.common;

import com.hang.common.longitudelatitude.LongitudeLatitude;
import org.junit.Test;

public class LongitudeLatitudeUtilTest
{
	@Test
	public void testDistance()
	{
		LongitudeLatitude ll_1 = new LongitudeLatitude();
		ll_1.latitude = 1;
		ll_1.longitude = 1;
		LongitudeLatitude ll_2 = new LongitudeLatitude();
		ll_2.latitude = 2;
		ll_2.longitude = 1;
		double distance = ll_1.distance(ll_2); // 经度相同，纬度相差1度，理论相差111000米
		System.out.println(distance);
		
		ll_1 = new LongitudeLatitude();
		ll_1.latitude = 1;
		ll_1.longitude = 1;
		ll_2 = new LongitudeLatitude();
		ll_2.latitude = -1;
		ll_2.longitude = 1;
		distance = ll_1.distance(ll_2); // 经度相同，纬度相差2分，理论相差222000米
		System.out.println(distance);
		
		ll_1 = new LongitudeLatitude();
		ll_1.latitude = 1;
		ll_1.longitude = 1;
		ll_2 = new LongitudeLatitude();
		ll_2.latitude = 1+ (double)1/60;
		ll_2.longitude = 1;
		distance = ll_1.distance(ll_2); // 经度相同，纬度相差1分，理论相差1850米
		System.out.println(distance);
		
		ll_1 = new LongitudeLatitude();
		ll_1.latitude = 1;
		ll_1.longitude = 1;
		ll_2 = new LongitudeLatitude();
		ll_2.latitude = 1+(double)1/(60*60);
		ll_2.longitude = 1;
		distance = ll_1.distance(ll_2); // 经度相同，纬度相差1秒，理论相差30.9米
		System.out.println(distance);
	}
}

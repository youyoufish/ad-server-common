package com.hang.common.utils;

import org.junit.Test;

import java.util.Date;

/**
 * Created by yuhang on 17-6-5.
 */
public class DateUtilTest {

    @Test
    public void test(){
        int dayOfWeek = DateUtil.getDayOfWeek(new Date());
        System.out.println(dayOfWeek);

        dayOfWeek = DateUtil.getDayOfWeek(DateUtil.parseDate("20170603", "yyyyMMdd"));
        System.out.println(dayOfWeek);

        dayOfWeek = DateUtil.getDayOfWeek(DateUtil.parseDate("20170604", "yyyyMMdd"));
        System.out.println(dayOfWeek);

        Date day = DateUtil.addDay(DateUtil.parseDate("20170531", "yyyyMMdd"), 1);
        System.out.println(day);
        day = DateUtil.addDay(DateUtil.parseDate("20170430", "yyyyMMdd"), 1);
        System.out.println(day);

        day = DateUtil.addMonth(DateUtil.parseDate("20170525", "yyyyMMdd"), 1);
        System.out.println(day);
        day = DateUtil.addMonth(DateUtil.parseDate("20170403", "yyyyMMdd"), 1);
        System.out.println(day);

        day = DateUtil.getMondayOfWeek(DateUtil.parseDate("20170606", "yyyyMMdd"));
        System.out.println(DateUtil.format(day));

        day = DateUtil.getFirstDayOfMonth(DateUtil.parseDate("20170501", "yyyyMMdd"));
        System.out.println(DateUtil.format(day));

        day = DateUtil.zeroClock(DateUtil.parseDate("20170501 121314", "yyyyMMdd HHmmss"));
        System.out.println(DateUtil.format(day));

        Date now = new Date();
        long n = DateUtil.dateToLong(now);
        Date nn = DateUtil.longToDate(n);
        System.out.println(String.format("%s, %d, %s", now, n, nn));

    }
}

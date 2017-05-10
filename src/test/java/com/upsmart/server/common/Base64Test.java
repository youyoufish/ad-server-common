package com.upsmart.server.common;

import com.upsmart.server.common.codec.Base64;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by upsmart on 17-5-8.
 */
public class Base64Test {

    @Test
    public void test(){
        String s ="ZXY9dHlwZTp3bmBvbj1hdWlkOjA7bXBpZDoxMztiaWQ6NjM7Y2lkOjMwMTthZGZtdDpiYW5uZXJgYXQ9c3JjOjg7cHVibmFtZTolRTUlQjAlOEYlRTglQUYlQjR2aXAlRTclQUIlQTAlRTglOEElODIlRTUlODUlOEQlRTglQjQlQjklRTklOTglODUlRTglQUYlQkItJUU1JUJGJUFCJUU4JUFGJUJCJUU2JThFJThDJUU5JTk4JTg1JUU0JUI5JUE2JUU2JTk3JTk3JTJCMjAxNSVFOCVBOCU4MCVFNiU4MyU4NSVFNyU4RSU4NCVFNSVCOSVCQiVFNiVBRCVBNiVFNCVCRSVBMCVFOCVCRiVCRCVFNCVCOSVBNiVFNyVBNSU5RSVFNSU5OSVBODttZG1kdDphcHA7bWRtY3N0OjthcHBpZDoyYTQ3MjU5MzA5MjI0YTFlYTllNDJhODNkYWM3Y2Y0NzthcHBhaWQ6O2J1bmRsZTo5MTM4MjQ0MDk7cHViaWQ6O21kOjtwZGlkOjthaWQ6MDtzaXplOjMyMHg1MDtgZW52PW9zOjI7aW9zdmVyOjEwLjA7aXA6MTE2LjIzOC45OC4xMDg7bGNvdW50cnk6MTg5O2xjaXR5OjU3MDA7bHN0YXRlOjUyNjtud2t0eXBlOjQ7bndrdm5kOjA7c3I6O2R2Y3R5cGU6O2R2Y2JyYW5kOkFwcGxlO2R2Y21vZGVsOmlQaG9uZSslMjh1bmRldGVjdGVkJTI5O2xuZzoxMjEuNDcwMDAwO2xhdDozMS4yMjgwMDA7YHVzZXI9aW1laTo7bWFjOjttYWMxOjthbmRyb2lkaWQ6O2lkZmE6OWY4OWM4NGE1NTlmNTczNjM2YTQ3ZmY4ZGFlZDBkMzM7b3BlbnVkaWQ6O2R1aWQ6O2ltc2k6YGV0PTEwMDBgYWNpZD11bmlvbnBheWBhdWN0aW9uaWQ9YWM0Y2ZjZjYtMDE1OS0xMDAwLWM2MmQtMDBlNGIxYTg4MTky";
        //String s = "ZXY9dHlwZTp3bmBvbj1hdWlkOjA7bXBpZDo0MDM7YmlkOjM4NDtjaWQ6Mzc4O2FkZm10OmJhbm5lcjtmcmVxZGV2aWNlaWQ6ZjI1MGY3OTRkOGRmMDEyMDA4ZThiNDhlODE3NTcwYjc7ZnJlcW1ldGE6O2BhdD1zcmM6MTtwdWJuYW1lOk15K1RhbGtpbmcrQW5nZWxhO21kbWR0OmFwcDthcHBpZDpiZWM2MTZjZmMyNzM0ZjJmYTg4ZDgxZDA4ZjllYjdiNTthcHBhaWQ6O2J1bmRsZTo5MDkzNTExNTg7cHViaWQ6O21kOjtwZGlkOjthaWQ6MDtzaXplOjMyMHg1MDtgZW52PW9zOjI7aW9zdmVyOjguNDtpcDoyMjIuNzIuMjQ4LjE5ODtsY291bnRyeToxODk7bGNpdHk6NTcwMDtsc3RhdGU6NTI2O253a3R5cGU6NDtud2t2bmQ6MDtzcjo7ZHZjdHlwZToxO2R2Y2JyYW5kOkFwcGxlO2R2Y21vZGVsOmlQYWQgKHVuZGV0ZWN0ZWQpO2xuZzoxMjEuNDQ5OTk2OTQ4MjQyMTk7bGF0OjMxLjIzOTk5OTc3MTExODE2NDtgdXNlcj1pbWVpOjttYWM6O21hYzE6O2FuZHJvaWRpZDo7aWRmYToyNDlEOUVFRi05REMzLTRCNjEtOTMyOC1ERkE5Mjg4QjhCNEMxO29wZW51ZGlkOjtkdWlkOjtpbXNpOmBldD0xMDAwYGFjaWQ9dW5pb25wYXlgYXVjdGlvbmlkPThiYmU5ODUwLTAxNTEtMTAwMC1jOWE2LTNlZWVhYWQzZjQ5NA==";
        byte[] b = Base64.decodeBase64(s.getBytes());
        String n = new String(b);
        System.out.println(n);

        Assert.assertEquals(n, "ev=type:wn`on=auid:0;mpid:13;bid:63;cid:301;adfmt:banner`at=src:8;pubname:%E5%B0%8F%E8%AF%B4vip%E7%AB%A0%E8%8A%82%E5%85%8D%E8%B4%B9%E9%98%85%E8%AF%BB-%E5%BF%AB%E8%AF%BB%E6%8E%8C%E9%98%85%E4%B9%A6%E6%97%97%2B2015%E8%A8%80%E6%83%85%E7%8E%84%E5%B9%BB%E6%AD%A6%E4%BE%A0%E8%BF%BD%E4%B9%A6%E7%A5%9E%E5%99%A8;mdmdt:app;mdmcst:;appid:2a47259309224a1ea9e42a83dac7cf47;appaid:;bundle:913824409;pubid:;md:;pdid:;aid:0;size:320x50;`env=os:2;iosver:10.0;ip:116.238.98.108;lcountry:189;lcity:5700;lstate:526;nwktype:4;nwkvnd:0;sr:;dvctype:;dvcbrand:Apple;dvcmodel:iPhone+%28undetected%29;lng:121.470000;lat:31.228000;`user=imei:;mac:;mac1:;androidid:;idfa:9f89c84a559f573636a47ff8daed0d33;openudid:;duid:;imsi:`et=1000`acid=unionpay`auctionid=ac4cfcf6-0159-1000-c62d-00e4b1a88192");

        StringBuilder sb = new StringBuilder("1234567:89");
        System.out.println(sb.length());

        sb.delete(sb.length()-1, sb.length());
        System.out.println(sb.toString());

        String result = sb.substring(sb.indexOf(":")+1);
        System.out.println(result);
    }
}

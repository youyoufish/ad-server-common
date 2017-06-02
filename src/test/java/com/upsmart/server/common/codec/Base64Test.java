package com.upsmart.server.common.codec;

import com.upsmart.server.common.codec.Base64;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by upsmart on 17-5-8.
 */
public class Base64Test {

    @Test
    public void test(){
        Base64 base64 = new Base64();
        base64.setOwnEncoding("EFGHIJKLABCDMNOPQRSTUVWXYZabcdefghmnopqijklrstuvwxyz0123456789_-");

        byte[] b64 = base64.encodeBase64("ABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes());
        String s64 = new String(b64);
        System.out.println(s64);
        byte[] d64 = base64.decodeBase64(s64.getBytes());
        String n = new String(d64);
        System.out.println(n);


        //String s ="ZXY9dHlwZTp3bmBvbj1hdWlkOjA7bXBpZDoxMztiaWQ6NjM7Y2lkOjMwMTthZGZtdDpiYW5uZXJgYXQ9c3JjOjg7cHVibmFtZTolRTUlQjAlOEYlRTglQUYlQjR2aXAlRTclQUIlQTAlRTglOEElODIlRTUlODUlOEQlRTglQjQlQjklRTklOTglODUlRTglQUYlQkItJUU1JUJGJUFCJUU4JUFGJUJCJUU2JThFJThDJUU5JTk4JTg1JUU0JUI5JUE2JUU2JTk3JTk3JTJCMjAxNSVFOCVBOCU4MCVFNiU4MyU4NSVFNyU4RSU4NCVFNSVCOSVCQiVFNiVBRCVBNiVFNCVCRSVBMCVFOCVCRiVCRCVFNCVCOSVBNiVFNyVBNSU5RSVFNSU5OSVBODttZG1kdDphcHA7bWRtY3N0OjthcHBpZDoyYTQ3MjU5MzA5MjI0YTFlYTllNDJhODNkYWM3Y2Y0NzthcHBhaWQ6O2J1bmRsZTo5MTM4MjQ0MDk7cHViaWQ6O21kOjtwZGlkOjthaWQ6MDtzaXplOjMyMHg1MDtgZW52PW9zOjI7aW9zdmVyOjEwLjA7aXA6MTE2LjIzOC45OC4xMDg7bGNvdW50cnk6MTg5O2xjaXR5OjU3MDA7bHN0YXRlOjUyNjtud2t0eXBlOjQ7bndrdm5kOjA7c3I6O2R2Y3R5cGU6O2R2Y2JyYW5kOkFwcGxlO2R2Y21vZGVsOmlQaG9uZSslMjh1bmRldGVjdGVkJTI5O2xuZzoxMjEuNDcwMDAwO2xhdDozMS4yMjgwMDA7YHVzZXI9aW1laTo7bWFjOjttYWMxOjthbmRyb2lkaWQ6O2lkZmE6OWY4OWM4NGE1NTlmNTczNjM2YTQ3ZmY4ZGFlZDBkMzM7b3BlbnVkaWQ6O2R1aWQ6O2ltc2k6YGV0PTEwMDBgYWNpZD11bmlvbnBheWBhdWN0aW9uaWQ9YWM0Y2ZjZjYtMDE1OS0xMDAwLWM2MmQtMDBlNGIxYTg4MTky";
        //String s = "ZXY9dHlwZTp3bmBvbj1hdWlkOjA7bXBpZDo0MDM7YmlkOjM4NDtjaWQ6Mzc4O2FkZm10OmJhbm5lcjtmcmVxZGV2aWNlaWQ6ZjI1MGY3OTRkOGRmMDEyMDA4ZThiNDhlODE3NTcwYjc7ZnJlcW1ldGE6O2BhdD1zcmM6MTtwdWJuYW1lOk15K1RhbGtpbmcrQW5nZWxhO21kbWR0OmFwcDthcHBpZDpiZWM2MTZjZmMyNzM0ZjJmYTg4ZDgxZDA4ZjllYjdiNTthcHBhaWQ6O2J1bmRsZTo5MDkzNTExNTg7cHViaWQ6O21kOjtwZGlkOjthaWQ6MDtzaXplOjMyMHg1MDtgZW52PW9zOjI7aW9zdmVyOjguNDtpcDoyMjIuNzIuMjQ4LjE5ODtsY291bnRyeToxODk7bGNpdHk6NTcwMDtsc3RhdGU6NTI2O253a3R5cGU6NDtud2t2bmQ6MDtzcjo7ZHZjdHlwZToxO2R2Y2JyYW5kOkFwcGxlO2R2Y21vZGVsOmlQYWQgKHVuZGV0ZWN0ZWQpO2xuZzoxMjEuNDQ5OTk2OTQ4MjQyMTk7bGF0OjMxLjIzOTk5OTc3MTExODE2NDtgdXNlcj1pbWVpOjttYWM6O21hYzE6O2FuZHJvaWRpZDo7aWRmYToyNDlEOUVFRi05REMzLTRCNjEtOTMyOC1ERkE5Mjg4QjhCNEMxO29wZW51ZGlkOjtkdWlkOjtpbXNpOmBldD0xMDAwYGFjaWQ9dW5pb25wYXlgYXVjdGlvbmlkPThiYmU5ODUwLTAxNTEtMTAwMC1jOWE2LTNlZWVhYWQzZjQ5NA==";

        String s = "ZXY9dLpwZTkhY2Fvbn1hdWpoOnE7bXFkZHjzOHQ7YqpoOnMyOTtnaWQ6MTY4O2JoZq10OqBhbq5pcntqcqVxZKV2aWNpaWQ6ZnA1MKY3OTRoOKRqMHIyMHE4ZThmNHhpOHI3NTcwYnc7ZiBpcW1pdKI6Y19oYWpseV9nbKtfNTtgYXQ9c3BnOnI7cLVmbqJtZTkNeStUYWxraW5iC0JuZ2VsYTttZK1odHkhcLE7YXFwaWQ6YqVnNnI2Y2ZnMnczNKYyZqI4OKQ4MWQwOKY5ZWA3YnU7YXFwYWpoOntmdW5obKU6OTE5MzUxMTU4O3F1YqpoOnttZHj7cKRkZHj7YWpoOnE7c2p6ZTjzMnF4NHgwO2FpbiY9b3M6Mntkb3N2ZXA6OG40O2pwOnI4My4yMnQuOTUuMTA2O2xnb3VudLB5OnI4OTtsY2p0eTj3MTE2O2xzdKJ0ZTj1MzM7bidrdLpwZTj0O253a3ZuZHj7c3A6O2R2Y3R5cKU6O2R2Y2ByYW5oOoJwcKxpO2R2Y21vZKVsOqpQYWQrBTA4dW5oZXRpY3RpZGUyOTtsbqc6MTEyDncwNnEwMHtsYXQ6MnUuMHM3MHEwO2F1c2VyPWptZWo6O21hYzj7bWJnMTj7YW5ocq9kZKpoOntkZKZhOqYyNTFqNzo0ZHhoZnExMnEwOKU4YnQ4ZTgxNzU3MKA3O29wZW51ZKpoOntodWpoOntkbXNkOqFpdH0xMHEwYKJnaWQ9dW5kb25wYXpfZKV2YKJ1Y3Rkb25kZH04YqBpOTg1MG0wMTUxDTIwMHEtYzphNm0zZWVpYWJoM2Y0OTRgZ289aLR0cGUzQSUyRmUyRqBhaWR1DqNvbQ==";
        byte[] b = base64.decodeBase64(s.getBytes());
        n = new String(b);
        System.out.println(n);

        Assert.assertEquals(n, "ev=type:ac`on=auid:0;mpid:384;bid:329;cid:168;adfmt:banner;freqdeviceid:f250f794d8df012008e8b48e817570b7;freqmeta:c_daily_clk_5;`at=src:1;pubname:My+Talking+Angela;mdmdt:app;appid:bec616cfc2734f2fa88d81d08f9eb7b5;appaid:;bundle:909351158;pubid:;md:;pdid:;aid:0;size:320x480;`env=os:2;iosver:8.4;ip:183.224.95.126;lcountry:189;lcity:7106;lstate:533;nwktype:4;nwkvnd:;sr:;dvctype:;dvcbrand:Apple;dvcmodel:iPad+%28undetected%29;lng:102.706000;lat:25.037000;`user=imei:;mac:;mac1:;androidid:;idfa:f250f794d8df012008e8b48e817570b7;openudid:;duid:;imsi:`et=1000`acid=unionpay_dev`auctionid=8bbe9850-0151-1000-c9a6-3eeeaad3f494`go=http%3A%2F%2Fbaidu.com");

        StringBuilder sb = new StringBuilder("1234567:89");
        System.out.println(sb.length());

        sb.delete(sb.length()-1, sb.length());
        System.out.println(sb.toString());

        String result = sb.substring(sb.indexOf(":")+1);
        System.out.println(result);

        //org.apache.commons.codec.binary.Base64 b64 = new org.apache.commons.codec.binary.Base64();
    }

    @Test
    public void test2(){
        Base64 base64 = new Base64();
        base64.setOwnEncoding("EFGHIJKLABCDMNOPQRSTUVWXYZabcdefghmnopqijklrstuvwxyz0123456789_-");
        base64.setOwnPad((byte)'!');

        String s = "ZXY9dLpwZTkhaWFvbn1hdWpoOnE7bXFkZHjyNHtmaWQ6MTY3O2NkZHj3MTo7YWRqbXQ6YqJubqVyO2ZyZXJoZXZkY2VkZHj1YTg0YzM1OWBmNHpoOWZpOHo5Znc0NzpmOTBmYzcyMztqcqVxbWV0YTknX3RvdKJsX2ptcJ82O2FhdH1zcqM6MTE7cLVmbqJtZTjpRTUpOHUpQUMpRTQpQngpQoApRTYpOTcpQnYpRTUpQnEpOUIpRTUpOIMpOTYpRTUpQTYpOHYyO21obWR0OqJwcHthcLFkZHkhMnMxYqI5NnEwYWI3NTU4MnE4ZHRoNKRpMHVhZHg5NnthcLFhaWQ6O2B1bqRsZTknb20uYW5ocq9kZKVtdS5uZXNjZKwyO3F1YqpoOnttZHj7cKRkZHj7YWpoOnE7c2p6ZTj2NHF4MTEwO2FpbiY9b3M6MTtkb3N2ZXA6NG40DnQ7aXE6MTI3DnIzNm4yNm4xNnE7bKNvdW50cio6MTg5O2xnaXR5Ontsc3RhdKU6NTMxO253a3R5cKU6MHtud2t2bqQ6MTtzcnj7ZLZndLpwZTj7ZLZnYiBhbqQ6T1FQTztodqNtb2RpbHkFMTI7bK5iOntsYXQ6O2F1c2VyPWptZWo6NWI4NKMzNTpmYnQ5ZHpqZTg5OWY3NHc5YnoyYqM3MnM7bWJnOnA0YnA0Ync5NzQ5Y2A2YToxMWVnYTNqZnRhOHNhY2YyO21hYzI6O2JuZLBvaWRkZHj5MHFoZWZhNHo0ZnAxN2JnNWQ0NzRpYTEwNKM1M2QyNTtkZKZhOntvcKVudWRkZHj7ZLVkZHj7aW1zaTkgZXQ9MTEwMKFhY2poPXVuaW9ucKJ5YKJ1Y3Rkb25kZH0yMHI3MHUzMS0xNHE0MzhfYqpocqVxXzIzOS0yOTQtM3hHcm04OTQ!";

        byte[] d64 = base64.decodeBase64(s.getBytes());
        String n = new String(d64);
        System.out.println(n);

        String compare = "ev=type:ai`on=auid:0;mpid:24;bid:167;cid:719;adfmt:banner;freqdeviceid:5a84c359bb49d9fe899f7479b92bc723;freqmeta:c_total_imp_6;`at=src:10;pubname:%E5%85%AC%E4%B8%BB%E6%97%B6%E5%B0%9A%E5%8C%96%E5%A6%862;mdmdt:app;appid:a231ba9600aa7558208d4d4de05ad896;appaid:;bundle:com.androidemu.neshdl2;pubid:;md:;pdid:;aid:0;size:640x100;`env=os:1;iosver:4.4.4;ip:117.136.26.160;lcountry:189;lcity:;lstate:531;nwktype:0;nwkvnd:1;sr:;dvctype:;dvcbrand:OPPO;dvcmodel:A11;lng:;lat:;`user=imei:5a84c359bb49d9fe899f7479b92bc723;mac:24b24b79749cb6a911eca3ff4a83acf2;mac1:;androidid:900defa494f217ac5d474ea004c53d25;idfa:;openudid:;duid:;imsi:`et=1000`acid=unionpay`auctionid=20170531-140438_bidreq_139-294-3xCr-894";
        System.out.println(compare);

        Assert.assertEquals(n, compare);
    }

    @Test
    public void test3(){
        Base64 base64 = new Base64();

        String s = "123";
        byte[] x = base64.encodeBase64(s.getBytes());
        String encode = new String(x);
        System.out.println(encode);
        encode = encode +"==";
        byte[] y = base64.decodeBase64(encode.getBytes());
        String decode = new String(y);
        System.out.println(decode);

        Assert.assertEquals(s, decode);
    }
}

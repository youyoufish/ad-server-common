package com.upsmart.server.common.codec;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yuhang on 17-10-9.
 */
public class SohuCryptoTest {


    @Test
    public void test() throws Exception {
        String key = "70465A507A31376F564E70344745303336625A30696B56794F7536316B565848";
        String data = "1CC76C4E999E87CFC06EB425D1672C591F28C4C9659C510A24B9BC147A37FB1A";

        SohuCrypto sohuCrypto = new SohuCrypto(key);

        String result = sohuCrypto.decrypt(data);

        System.out.println(result);

        Assert.assertEquals("5.5", result);
    }
}

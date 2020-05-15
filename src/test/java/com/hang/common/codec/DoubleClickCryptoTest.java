package com.hang.common.codec;

import org.junit.Assert;
import org.junit.Test;

import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.SignatureException;

/**
 * Created by yuhang on 17-6-2.
 */
public class DoubleClickCryptoTest {

    @Test
    public void test() throws InvalidKeyException, SignatureException {

        DoubleClickCrypto.Keys keys = new DoubleClickCrypto.Keys(
                new SecretKeySpec("24IHHDVFm4d0Ptnq83pCUsOMcgKZa3W8".getBytes(), "HmacSHA1"),
                new SecretKeySpec("8P600sh1nqYwwdI9CRji8UmSCrsmbIPS".getBytes(), "HmacSHA1"));

        DoubleClickCrypto.Price crypto = new DoubleClickCrypto.Price(keys);

        String encodedPrice = "itwyXVwBAABWEFJHLGp8VbIk3abu3pabIwqAmg";
        long price = crypto.decodePriceMicros(encodedPrice);

        System.out.println(price);
        Assert.assertEquals(price, 27600);
    }
}

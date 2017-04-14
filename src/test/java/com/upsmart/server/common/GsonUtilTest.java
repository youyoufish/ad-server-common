package com.upsmart.server.common;

import com.upsmart.server.common.utils.GsonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by upsmart on 17-4-14.
 */
public class GsonUtilTest {

    @Test
    public void testGson(){
        GsonUtil gson = new GsonUtil();

        String s = "{" +
                        "\"cur\": \"CNY\"," +
                        "\"id\": \"7495a2b1-0153-1000-d0c0-3e952d9e3097\"," +
                        "\"bid\": [{\"price\" : 0.999 },{\"price\" : 991}]" +
                    "}";
        InputStream is = new ByteArrayInputStream(s.getBytes());
        TData td = gson.deserialize(is, TData.class);

        Assert.assertTrue(td.cur.equals("CNY"));
        Assert.assertTrue(td.id.equals("7495a2b1-0153-1000-d0c0-3e952d9e3097"));


        TData td2 = new TData();
        td2.cur = "RMB";
        td2.id = "123456";
        SubData sd = new SubData();
        sd.price = 0.999f;
        td2.bid.add(sd);
        String s2 = gson.serialize(td2);

        Assert.assertTrue(true);
    }

    class TData{
        public String cur;
        public String id;
        public List<SubData> bid = new ArrayList<>();
    }
    class SubData{
        public float price;
    }
}

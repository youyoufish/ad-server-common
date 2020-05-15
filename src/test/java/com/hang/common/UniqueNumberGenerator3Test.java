package com.hang.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.hang.common.uniquenumber.UniqueNumberGenerator3;
import org.junit.Assert;
import org.junit.Test;

public class UniqueNumberGenerator3Test {

    @Test
    public void testGenerateAndDescript() {
        final int MACHINE_ID = 255;
        long t1 = System.currentTimeMillis();
        String uid = UniqueNumberGenerator3.getInstance(MACHINE_ID).next();
        long t2 = System.currentTimeMillis();
        UniqueNumberGenerator3.DecryptedUid duid = UniqueNumberGenerator3.decrypt(uid);
        long ts = duid.getTimestamp().getTime();
        Assert.assertTrue(ts >= t1 && ts <= t2);
        Assert.assertEquals(MACHINE_ID, duid.getMachineId());
    }

    @Test
    public void singleThreadedUniqueVerify() {
        final int N = 0xfffff;
        Set<String> uids = new HashSet<String>(N);
        for (int i = 0; i < N; ++i)
            uids.add(UniqueNumberGenerator3.getInstance(i).next());
        Assert.assertEquals(N, uids.size());
    }

    @Test
    public void multiThreadedUniqueVerify() throws InterruptedException {
        final int N = 0xffff;
        final Object NULL = new Object();
        final ConcurrentMap<String, Object> uids = new ConcurrentHashMap<String, Object>();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Callable<Void> task = new Callable<Void>() {
            @Override
            public Void call() {
                for (int i = 0, n = N / 2; i < n; ++i)
                    uids.put(UniqueNumberGenerator3.getInstance(i)
                            .next(), NULL);
                return null;
            }
        };
        threadPool.invokeAll(Arrays.asList(task, task));
        TimeUnit.SECONDS.sleep(2L);
        threadPool.shutdown();
        threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        Assert.assertEquals(N / 2 * 2, uids.size());
    }
}
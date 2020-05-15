package com.hang.common.localcache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author hang on 2020/5/16
 */
public class LocalCacheThread {

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,5,5000L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(1000));

    private static ThreadPoolExecutor buildThreadPool(){
        return threadPoolExecutor;
    }

    public static <U,V> List<V> concurrentCall(
            List<U> requests,
            Translator<U, Callable<V>> taskTransltor,
            int timeout,
            TimeUnit timeUnit,
            boolean allowPartialSuccess){

        List<Callable<V>> tasks = translate(requests, taskTransltor);
        ThreadPoolExecutor executor = buildThreadPool();

        return concurrentTaskCall(tasks, executor,timeout, timeUnit, allowPartialSuccess);
    }

    private static <V> List<V> concurrentTaskCall(
            List<Callable<V>> tasks,
            ThreadPoolExecutor executor,
            int timeout,
            TimeUnit timeUnit,
            boolean allowPartialSuccess) {

        List<Future<V>> futures;
        List<V> result = new ArrayList<>();
        if(null == tasks || tasks.isEmpty()){
            return null;
        }

        try{
            futures = executor.invokeAll(tasks, timeout, timeUnit);
            for(Future<V> future : futures){
                try{
                    if(future.isCancelled()){
                        // 并发调用发生异常
                        if(!allowPartialSuccess){
                            return null;
                        }
                        else {
                            continue;
                        }
                    }

                    V singleResult = future.get();
                    if(!allowPartialSuccess && null == singleResult){
                        // 任务执行失败
                        return null;
                    }
                    if(null != singleResult){
                        result.add(singleResult);
                    }
                }
                catch (Exception ex){
                    if(!allowPartialSuccess){
                        // 如果不允许部分成功，那么异常就返回null
                        return null;
                    }
                }
            }
        }
        catch (Exception ex){
            return null;
        }
        return result;
    }


    private static <T,K> List<K> translate(List<T> list, Translator<T,K> translator){
        if(null == list || list.isEmpty()){
            return new ArrayList<>();
        }
        List<K> result = new ArrayList<>();
        for(T t : list){
            result.add(translator.translator(t));
        }
        return result;
    }

    public static interface Translator<T,K>{
        K translator(T t);
    }
}

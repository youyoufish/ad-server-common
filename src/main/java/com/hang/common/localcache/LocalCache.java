package com.hang.common.localcache;

import com.google.common.cache.*;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 本地缓存: kv结构。
 *
 * 1) 根据一定的规则，用key从数据源获得value，保存在本地缓存中
 * 2) 使用key获得本地缓存中的value
 *
 * @author hang on 2020/5/14
 */
public abstract class LocalCache<T> {

    protected LoadingCache<String, T> cache;

    protected synchronized void init(LocalCacheConf conf){
        if(null == conf){
            throw new RuntimeException("config is null");
        }
        if(null == cache){
            cache = callableCached(conf);
        }
    }

    private LoadingCache<String, T> callableCached(LocalCacheConf conf){
        LoadingCache<String,T> cache = CacheBuilder
                .newBuilder()
                .maximumSize(conf.maximunSize)
                .expireAfterWrite(conf.expireAfterWrite, TimeUnit.SECONDS)
                .refreshAfterWrite(conf.refreshAfterWrite, TimeUnit.SECONDS)
                .removalListener(new RemovalListener<String, T>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, T> removalNotification) {
                        // log
                    }
                }).build(new CacheLoader<String, T>() {
                    @Override
                    public T load(String s) throws Exception {
                        T t = getToLocal(s);
                        if(null == t){
                            // log
                        }
                        return t;
                    }

                    @Override
                    public Map<String, T> loadAll(Iterable<? extends String> keys) throws Exception {
                        List<String> keyList = Lists.newArrayList(keys);
                        Map<String,T> mapValue = getToLocal(keyList);
                        if(null == mapValue || mapValue.isEmpty()){
                            // log
                        }
                        return mapValue;
                    }

                    @Override
                    public ListenableFuture<T> reload(String key, T oldValue) throws Exception {

                        List<T> ret = LocalCacheThread.concurrentCall(
                                Lists.newArrayList(key),
                                new LocalCacheThread.Translator<String, Callable<T>>() {
                                    @Override
                                    public Callable<T> translator(String s) {
                                        return new Callable<T>() {
                                            @Override
                                            public T call() throws Exception {
                                                T t = null;
                                                try{
                                                    t = getToLocal(s);
                                                    if(null == t){
                                                        // log
                                                    }
                                                }
                                                catch (Exception ex){
                                                    // log
                                                }
                                                return t;
                                            }
                                        };
                                    }
                                },
                                conf.concurrentCallTimeout,
                                TimeUnit.MILLISECONDS,
                                conf.allowPartialSuccess);

                        if(null == ret || ret.isEmpty()){
                            return Futures.immediateFuture(oldValue);
                        }
                        return Futures.immediateFuture(ret.get(0));
                    }
                });
        return cache;
    }

    /**
     * 通过key从数据源获得value到缓存
     * @param id
     * @return
     */
    protected abstract T getToLocal(String id);

    /**
     * 通过key集合从数据源获得value集合到缓存
     * @param ids
     * @return
     */
    protected abstract Map<String, T> getToLocal(List<String> ids);

    /**
     * 从缓存中通过key获得value
     * @param id
     * @return
     */
    public T get(String id){
        T t = null;
        try{
            if(null != cache){
                t = cache.get(id);
            }
        }
        catch (Exception ex){
            t = null;
        }
        return t;
    }

    /**
     * 获得缓存中的value数量(非精确)
     * @return
     */
    public long size(){
        if(null != cache){
            return cache.size();
        }
        return 0;
    }

    /**
     * 重新加载key的value到缓存中
     * @param id
     */
    public void reload(String id){
        try{
            if(null != cache){
                cache.refresh(id);
            }
        }
        catch (Exception ex){
        }
    }
}

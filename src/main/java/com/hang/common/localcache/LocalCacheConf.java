package com.hang.common.localcache;

/**
 * 本地缓存基础配置
 *
 * @author hang on 2020/5/14
 */
public class LocalCacheConf {

    /**
     * 缓存kv最大数量
     */
    public int maximunSize = 30000;

    /**
     * 缓存淘汰时间(秒)
     */
    public int expireAfterWrite = 86400;

    /**
     * 缓存刷新时间(秒)
     */
    public int refreshAfterWrite = 60;

    /**
     * （并发调用时）最大超时时间(毫秒)
     */
    public int concurrentCallTimeout = 200;

    /**
     * (并发调用时)是否允许部分成功
     */
    public boolean allowPartialSuccess = true;
}

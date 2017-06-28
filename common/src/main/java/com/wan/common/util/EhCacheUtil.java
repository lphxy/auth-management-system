package com.wan.common.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.Optional;

/**
 * EhCache工具类
 * <p>
 * Created by w1992wishes on 2017/6/28.
 */
public class EhCacheUtil {

    /**
     * 获取缓存
     *
     * @param cacheName
     * @return
     */
    private static Cache getCache(String cacheName) {
//        CacheManager cacheManager = CacheManager.create();
//        if (null == cacheManager) {
//            return null;
//        }
//        Cache cache = cacheManager.getCache(cacheName);
//        if (null == cache) {
//            return null;
//        }
//        return cache;
        return Optional.ofNullable(CacheManager.create())
                .map(cacheManager -> cacheManager.getCache(cacheName))
                .orElse(null);
    }

    /**
     * 新增缓存记录
     *
     * @param cacheName
     * @param key
     * @param value
     */
    public static void put(String cacheName, String key, Object value) {
//        Cache cache = getCache(cacheName);
//        if (null != cache) {
//            Element element = new Element(key, value);
//            cache.put(element);
//        }
        Optional.ofNullable(getCache(cacheName))
                .ifPresent(cache -> cache.put(new Element(key, value)));
    }

    /**
     * 获取缓存记录
     *
     * @param cacheName
     * @param key
     * @return
     */
    public static Object get(String cacheName, String key) {
//        Cache cache = getCache(cacheName);
//        if (cache == null)
//            return null;
//        Element element = cache.get(key);
//        if (element == null)
//            return null;
//        return element.getObjectValue();
        return Optional.ofNullable(getCache(cacheName))
                .map(cache -> cache.get(key))
                .map(element -> element.getObjectValue())
                .orElse(null);
    }

    /**
     * 删除缓存记录
     *
     * @param cacheName
     * @param key
     * @return
     */
    public static boolean remove(String cacheName, String key){
//        Cache cache = getCache(cacheName);
//        if (null == cache)
//            return false;
//        return cache.remove(key);
        return Optional.ofNullable(getCache(cacheName))
                .map(cache -> cache.remove(key))
                .orElse(false);
    }

    public static void removeAll(String cacheName){
        Optional.ofNullable(getCache(cacheName)).ifPresent(cache -> cache.removeAll());
    }
}

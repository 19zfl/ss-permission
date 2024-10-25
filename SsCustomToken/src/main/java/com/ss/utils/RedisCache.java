//package com.ss.utils;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.BoundSetOperations;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.stereotype.Component;
//
//import java.util.*;
//import java.util.concurrent.TimeUnit;
//
///**
// * @ClassName:RedisCache
// * @Description:
// * @Author:zfl19
// * @CreateDate:2024/10/23 0:19
// */
//
//@SuppressWarnings(value = { "unchecked", "rawtypes" })
//@Component
//public class RedisCache {
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    public <T> void setCacheObject(final String key, final T value) {
//        redisTemplate.opsForValue().set(key, value);
//    }
//
//    /**
//     *
//     * @param key
//     * @param value
//     * @param timeout
//     * @param timeUnit 时间颗粒度
//     * @param <T>
//     */
//    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
//        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
//    }
//
//    public boolean expire(final String key, final long timeout, TimeUnit timeUnit) {
//        return redisTemplate.expire(key, timeout, timeUnit);
//    }
//
//    public boolean expire(final String key, final long timeout) {
//        return expire(key, timeout, TimeUnit.SECONDS);
//    }
//
//    public <T> T getCacheObject(final String key) {
//        ValueOperations<String, T> operations = redisTemplate.opsForValue();
//        return operations.get(key);
//    }
//
//    /**
//     * 删除单个
//     * @param key
//     * @return
//     */
//    public boolean deleteObject(final String key) {
//        return redisTemplate.delete(key);
//    }
//
//    /**
//     * 删除集合
//     * @param collection
//     * @return
//     */
//    public long deleteObject(final Collection collection) {
//        return redisTemplate.delete(collection);
//    }
//
//    /**
//     * 缓存List数据
//     * @param key
//     * @param dataList
//     * @return
//     * @param <T>
//     */
//    public <T> long setCacheList(final String key, final List<T> dataList) {
//        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
//        return count == null ? 0 : count;
//    }
//
//    /**
//     * 获取缓存的List数据
//     * @param key
//     * @return
//     * @param <T>
//     */
//    public <T> List<T> getCacheList(final String key) {
//        return redisTemplate.opsForList().range(key, 0, -1);
//    }
//
//    /**
//     * 缓存Set数据
//     * @param key
//     * @param dataSet
//     * @return
//     * @param <T>
//     */
//    public <T> BoundSetOperations<String, T> setCacheSet(final String key, Set<T> dataSet) {
//        BoundSetOperations<String, T> setOperations = redisTemplate.boundSetOps(key);
//        Iterator<T> it = dataSet.iterator();
//        while(it.hasNext()) {
//            setOperations.add(it.next());
//        }
//        return setOperations;
//    }
//
//    /**
//     * 获取缓存的Set数据
//     * @param key
//     * @return
//     * @param <T>
//     */
//    public <T> Set<T> getCacheSet(final String key) {
//        return redisTemplate.opsForSet().members(key);
//    }
//
//    /**
//     * 缓存Map数据
//     * @param key
//     * @param dataMap
//     * @param <T>
//     */
//    public <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
//        if (dataMap != null) {
//            redisTemplate.opsForHash().putAll(key, dataMap);
//        }
//    }
//
//    /**
//     * 获取缓存的Map数据
//     * @param key
//     * @return
//     * @param <T>
//     */
//    public <T> Map<String, T> getCacheMap(final String key) {
//        return redisTemplate.opsForHash().entries(key);
//    }
//
//    /**
//     * 缓存Hash数据
//     * @param key
//     * @param hkey
//     * @param value
//     * @param <T>
//     */
//    public <T> void setCacheMapValue(final String key, final String hkey, final T value) {
//        redisTemplate.opsForHash().put(key, hkey, value);
//    }
//
//    /**
//     * 获取Hash缓存的数据
//     * @param key
//     * @param hkey
//     * @return
//     * @param <T>
//     */
//    public <T> T getCacheMapValue(final String key, final String hkey) {
//        HashOperations<String, String, T> operations = redisTemplate.opsForHash();
//        return operations.get(key, hkey);
//    }
//
//    /**
//     * 删除Hash中的数据
//     * @param key
//     * @param hkey
//     */
//    public void deleteCacheMapValue(final String key, final String hkey) {
//        HashOperations operations = redisTemplate.opsForHash();
//        operations.delete(key, hkey);
//    }
//
//    /**
//     * 获取多个Hash中的数据
//     * @param key
//     * @param hkeys
//     * @return
//     * @param <T>
//     */
//    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hkeys) {
//        return redisTemplate.opsForHash().multiGet(key, hkeys);
//    }
//
//    /**
//     * 获取缓存的基本对象列表
//     * @param pattern
//     * @return
//     */
//    public Collection<String> keys(final String pattern) {
//        return redisTemplate.keys(pattern);
//    }
//
//}

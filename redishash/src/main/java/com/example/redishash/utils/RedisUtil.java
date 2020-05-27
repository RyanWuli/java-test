package com.example.redishash.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: Ryan
 * @Date: 2020/5/11 16:25
 * @Version: 1.0
 * @Description: redis 操作工具类
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, String> template;

    /**
     * 设置默认过期时间
     */
    public static final long DEFAULT_EXPIRE = 60 * 60 * 24;

    /**
     * 不设置过期时间（永不过期）
     */
    public static final long NOT_EXPIRE = -1;

    /**
     * 是否存在 key
     * @param key
     * @return
     */
    public boolean existsKey(String key) {
        return template.hasKey(key);
    }

    /**
     * 重命名，如果 newKey 已经存在，则覆盖 newKey 的原值
     * @param oldKey
     * @param newKey
     */
    public void renameKey(String oldKey, String newKey) {
        template.rename(oldKey, newKey);
    }

    /**
     * newKey 名称不存在是才重命名
     * @param oldKey
     * @param newKey
     */
    public boolean renameKeyNotExist(String oldKey, String newKey) {
        return template.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 删除 key
     * @param key
     */
    public void deleteKey(String key) {
        template.delete(key);
    }

    /**
     * 删除多个 key
     * @param keys
     */
    public void deleteKey(String... keys) {
        Set<String> kSet = Stream.of(keys).map(k -> k).collect(Collectors.toSet());
        template.delete(kSet);
    }

    public void deleteKey(Collection<String> keys) {
        Set<String> kSet = keys.stream().map(k -> k).collect(Collectors.toSet());
        template.delete(kSet);
    }

    /**
     * 设置 key 的生命周期
     * @param key
     * @param time
     * @param timeUnit
     */
    public void expireKey(String key, long time, TimeUnit timeUnit) {
        template.expire(key, time, timeUnit);
    }

    /**
     * 指定 key 过期时间
     * @param key
     * @param date
     */
    public void expireKeyAt(String key, Date date) {
        template.expireAt(key, date);
    }

    /**
     * 获取过期时间
     * @param key
     * @param timeUnit
     * @return
     */
    public long getKeyExpire(String key, TimeUnit timeUnit) {
        return template.getExpire(key, timeUnit);
    }

    /**
     * 设置 key 为永久有效
     * @param key
     */
    public void persistKey(String key) {
        template.persist(key);
    }
}

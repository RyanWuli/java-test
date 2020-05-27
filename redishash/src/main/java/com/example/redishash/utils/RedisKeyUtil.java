package com.example.redishash.utils;

/**
 * @Author: Ryan
 * @Date: 2020/5/11 16:49
 * @Version: 1.0
 * @Description: redis key 的工具类
 */
public class RedisKeyUtil {

    /**
     * redis 的 key
     * 形式为：
     * 表名：主键名：主键值：列名
     * @param tableName
     * @param majorKey
     * @param majorKeyValue
     * @param column
     * @return
     */
    public static String getKeyWithColumn(String tableName, String majorKey, String majorKeyValue, String column) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue).append(":");
        buffer.append(column);
        return buffer.toString();
    }

    /**
     * redis 的 key
     * 形式为：
     * 表名：主键名：主键值
     * @param tableName
     * @param majorKey
     * @param majorKeyValue
     * @return
     */
    public static String getKey(String tableName, String majorKey, String majorKeyValue) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue);
        return buffer.toString();
    }
}

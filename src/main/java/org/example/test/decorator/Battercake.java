package org.example.test.decorator;

/**
 * @Author: Ryan
 * @Date: 2020/5/22 14:44
 * @Version: 1.0
 * @Description: 煎饼果子实体类
 */
public class Battercake extends ABattercake {

    @Override
    public String getDesc() {
        return "煎饼";
    }

    @Override
    public int cost() {
        return 8;
    }
}

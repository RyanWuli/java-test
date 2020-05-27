package org.example.test.build.a;

/**
 * @Author: Ryan
 * @Date: 2020/5/7 16:56
 * @Version: 1.0
 * @Description: 建造者模式
 */
abstract class Builder {

    // 地基
    abstract void buildA();
    // 钢筋工程
    abstract void buildB();
    // 铺电线
    abstract void buildC();
    // 粉刷
    abstract void buildD();
    // 完工（获取产品）
    abstract Product getProduct();
}

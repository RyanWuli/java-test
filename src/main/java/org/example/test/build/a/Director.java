package org.example.test.build.a;

/**
 * @Author: Ryan
 * @Date: 2020/5/7 17:10
 * @Version: 1.0
 * @Description: 指挥者
 */
public class Director {

    // 指挥工人按顺序造房
    public Product create(Builder builder) {
        builder.buildA();
        builder.buildB();
        builder.buildC();
        builder.buildD();
        return builder.getProduct();
    }
}

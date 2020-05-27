package org.example.test.build.a;

/**
 * @Author: Ryan
 * @Date: 2020/5/7 17:06
 * @Version: 1.0
 * @Description:
 */
public class ConcreteBuilder extends Builder {

    private Product product;

    public ConcreteBuilder() {
        product = new Product();
    }

    @Override
    void buildA() {
        product.setBuildA("地基");
    }

    @Override
    void buildB() {
        product.setBuildB("钢精工程");
    }

    @Override
    void buildC() {
        product.setBuildC("铺电线");
    }

    @Override
    void buildD() {
        product.setBuildD("粉刷");
    }

    @Override
    Product getProduct() {
        return product;
    }
}

package org.example.test.decorator;

/**
 * @Author: Ryan
 * @Date: 2020/5/22 14:46
 * @Version: 1.0
 * @Description: 装饰父类
 */
public class AbstractDecorator extends ABattercake {

    private ABattercake aBattercake;

    public AbstractDecorator(ABattercake aBattercake) {
        this.aBattercake = aBattercake;
    }

    @Override
    public String getDesc() {
        return this.aBattercake.getDesc();
    }

    @Override
    public int cost() {
        return this.aBattercake.cost();
    }
}

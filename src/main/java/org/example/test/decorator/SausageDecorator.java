package org.example.test.decorator;

/**
 * @Author: Ryan
 * @Date: 2020/5/22 14:52
 * @Version: 1.0
 * @Description: 香肠装饰类（加香肠）
 */
public class SausageDecorator extends AbstractDecorator {
    public SausageDecorator(ABattercake aBattercake) {
        super(aBattercake);
    }

    @Override
    public String getDesc() {
        return super.getDesc() + " 加一根香肠";
    }

    @Override
    public int cost() {
        return super.cost() + 2;
    }
}

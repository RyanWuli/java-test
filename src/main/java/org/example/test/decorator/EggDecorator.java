package org.example.test.decorator;

/**
 * @Author: Ryan
 * @Date: 2020/5/22 14:49
 * @Version: 1.0
 * @Description: 鸡蛋的装饰类（加鸡蛋）
 */
public class EggDecorator extends AbstractDecorator {

    public EggDecorator(ABattercake aBattercake) {
        super(aBattercake);
    }

    @Override
    public String getDesc() {
        return super.getDesc() + " 加一个鸡蛋";
    }

    @Override
    public int cost() {
        return super.cost() + 1;
    }
}

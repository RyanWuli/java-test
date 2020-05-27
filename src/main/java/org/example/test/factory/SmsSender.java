package org.example.test.factory;

/**
 * @Author: Ryan
 * @Date: 2020/5/7 16:37
 * @Version: 1.0
 * @Description:
 */
public class SmsSender implements Sender {
    @Override
    public void send() {
        System.out.println("This is SmsSender!");
    }
}

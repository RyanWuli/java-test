package org.example.test.factory;

/**
 * @Author: Ryan
 * @Date: 2020/5/7 16:36
 * @Version: 1.0
 * @Description: 工厂模式
 */
public class MailSender implements Sender {
    @Override
    public void send() {
        System.out.println("This is MailSender!");
    }
}

package org.example.test.factory;

/**
 * @Author: Ryan
 * @Date: 2020/5/7 16:40
 * @Version: 1.0
 * @Description:
 */
public class SendSmsFactory implements Provider {
    @Override
    public Sender provider() {
        return new SmsSender();
    }
}

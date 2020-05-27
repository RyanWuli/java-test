package org.example.test.factory;

/**
 * @Author: Ryan
 * @Date: 2020/5/7 16:39
 * @Version: 1.0
 * @Description:
 */
public class SendMailFactory implements Provider {
    @Override
    public Sender provider() {
        return new MailSender();
    }
}

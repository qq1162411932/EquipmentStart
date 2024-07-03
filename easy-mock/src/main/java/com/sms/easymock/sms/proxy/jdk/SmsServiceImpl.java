package com.sms.easymock.sms.proxy.jdk;


import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {

    @Override
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }

    @Override
    public String update(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}

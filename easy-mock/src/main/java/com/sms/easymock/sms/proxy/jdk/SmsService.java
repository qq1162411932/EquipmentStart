package com.sms.easymock.sms.proxy.jdk;

public interface SmsService {
    String send(String message);

    String update(String message);
}

package com.sms.easymock.sms.proxy.jdk.test;

import com.sms.easymock.sms.proxy.jdk.DebugInvocationHandler;
import com.sms.easymock.sms.proxy.jdk.SmsService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Proxy;

public class JdkProxyFactory {

    private static ApplicationContext applicationContext; // 静态变量保存ApplicationContext

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context; // 注入ApplicationContext对象
    }

    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(), // 目标类的类加载器
                target.getClass().getInterfaces(),  // 代理需要实现的接口，可指定多个
                new DebugInvocationHandler(target)   // 代理对象对应的自定义 InvocationHandler
        );
    }

    public static void main(String[] args) {
        // 获取Spring Boot的ApplicationContext
        ApplicationContext context = SpringApplication.run(JdkProxyFactory.class, args);
        
        // 设置ApplicationContext到JdkProxyFactory中
        JdkProxyFactory.setApplicationContext(context);
        
        // 获取Spring管理的SmsService bean，并通过代理调用
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(applicationContext.getBean(SmsService.class));
        smsService.send("java");
    }
}

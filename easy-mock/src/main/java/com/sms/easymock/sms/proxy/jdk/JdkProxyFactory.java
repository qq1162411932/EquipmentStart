package com.sms.easymock.sms.proxy.jdk;

import java.lang.reflect.Proxy;

public class JdkProxyFactory {

    /**
     * 静态方法中无法使用 @Autowired 会失效
     * @param target
     * @return
     */
    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(), // 目标类的类加载器
                target.getClass().getInterfaces(),  // 代理需要实现的接口，可指定多个
                new DebugInvocationHandler(target)   // 代理对象对应的自定义 InvocationHandler
        );
    }

    public static void main(String[] args) {
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("java");
    }
}

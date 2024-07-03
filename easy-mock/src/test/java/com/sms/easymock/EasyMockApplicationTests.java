package com.sms.easymock;

import com.sms.easymock.service.ThirdPartyService;
import com.sms.easymock.sms.proxy.jdk.DebugInvocationHandler;
import com.sms.easymock.sms.proxy.jdk.JdkProxyFactory;
import com.sms.easymock.sms.proxy.jdk.SmsService;
import com.sms.easymock.sms.proxy.jdk.SmsServiceImpl;
import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@RunWith(SpringRunner.class)
@SpringBootTest
class EasyMockApplicationTests {

	@Autowired
	SmsService smsService;
	@Autowired
	ApplicationContext applicationContext;
	ThirdPartyService thirdPartyService = EasyMock.createMock(ThirdPartyService.class);;

	@BeforeEach
	public void setUp() {
		EasyMock.reset(thirdPartyService);
	}

	@Test
	void contextLoads() {
		// 定义模拟行为
		EasyMock.expect(thirdPartyService.getDeviceInfo(1)).andReturn("Mocked Data");

		// 准备模拟对象
		EasyMock.replay(thirdPartyService);

		// 调用模拟对象的方法
		String result = thirdPartyService.getDeviceInfo(1);

		// 验证结果
		assertEquals("Mocked Data 2", result);

		// 验证模拟对象是否按预期运行
		EasyMock.verify(thirdPartyService);
	}

	@Test
	void testJdkProxy(){
		SmsService bean = applicationContext.getBean(SmsService.class);
		SmsService smsService = (SmsService) JdkProxyFactory.getProxy(bean);
		smsService.send("java");
		smsService.update("python");
//		Object ob = Proxy.newProxyInstance(
//				SmsService.class.getClassLoader(), // 目标类的类加载器
//				SmsService.class.getInterfaces(),  // 代理需要实现的接口，可指定多个
//				new DebugInvocationHandler(SmsService.class));
//		SmsService s = (SmsService)ob;
//		s.send("java");
//		System.out.println(smsService);
	}
}

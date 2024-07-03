//package com.sms.easymock;
//
//import com.github.tomakehurst.wiremock.WireMockServer;
//import com.github.tomakehurst.wiremock.client.WireMock;
//import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
//import com.github.tomakehurst.wiremock.junit.WireMockRule;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.web.client.RestTemplate;
//
//import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
//import static com.github.tomakehurst.wiremock.client.WireMock.get;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//@SpringBootTest
//public class WireMockTest {
//
//    private WireMockServer wireMockServer;
//
////    @BeforeEach
////    public void setUp() {
////        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8089).extensions());
////        wireMockServer.start();
////        WireMock.configureFor("localhost", 8089);
////    }
////
////    @AfterEach
////    public void tearDown() {
////        wireMockServer.stop();
////    }
////
////    @Test
////    public void testGetData() {
////        // 配置 WireMock 服务器返回的模拟响应
////        WireMock.stubFor(get(WireMock.urlEqualTo("/data/123"))
////                .willReturn(aResponse()
////                        .withStatus(200)
////                        .withBody("Mocked Data")));
////
////        RestTemplate restTemplate = new RestTemplate();
////        String response = restTemplate.getForObject("http://localhost:8089/data/123", String.class);
////
////        assertEquals("Mocked Data", response);
////    }
//       @ExtendWith()
//        public WireMockRule wireMockRule = new WireMockRule(8089); // No-args constructor
//
//}

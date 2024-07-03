package com.sms.easymock;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class JUnitJupiterProgrammaticProxyTest {

    @RegisterExtension
    static WireMockExtension wm = WireMockExtension.newInstance()
            .proxyMode(true)
            .build();

    CloseableHttpClient client;

    @BeforeEach
    void init() {
        client = HttpClientBuilder.create()
                .useSystemProperties() // This must be enabled for auto proxy config
                .build();
    }

    @Test
    void configures_jvm_proxy_and_enables_browser_proxying() throws Exception {
        wm.stubFor(get("/things")
                .withHost(equalTo("one.my.domain"))
                .willReturn(ok("1")));

        wm.stubFor(get("/things")
                .withHost(equalTo("two.my.domain"))
                .willReturn(ok("2")));

        assertEquals(getContent("http://one.my.domain/things"), "1");
        assertEquals(getContent("http://two.my.domain/things"), "2");
    }

    private String getContent(String url) throws Exception {
        try (CloseableHttpResponse response = client.execute(new HttpGet(url))) {
            return EntityUtils.toString(response.getEntity());
        }
    }
}
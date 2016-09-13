package com.marklogic.cf.servicebroker.config;

import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import com.marklogic.cf.servicebroker.repository.MarkLogicManageAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${ml.host:localhost}")
    private String host;

    @Bean
    public String host() {
        return host;
    }

    @Value("${ml.port:8002}")
    private int port;

    @Value("${ml.uid:admin}")
    private String uid;

    @Value("${ml.pw:admin}")
    private String pw;

    @Bean
    public MarkLogicManageAPI markLogicManageAPI() {
        return Feign
                .builder()
                .requestInterceptor(new BasicAuthRequestInterceptor(uid, pw))
                .target(MarkLogicManageAPI.class,
                        "http://" + host + ":" + port);
    }
}
package com.marklogic.cf.servicebroker.config;

import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import com.marklogic.cf.servicebroker.repository.MarkLogicManageAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("cloud")
@Configuration
public class CloudConfig {

    @Value("${ml.host:financial.demo.marklogic.com}")
    private String host;

    @Bean
    public String host() {
        return host;
    }

    @Value("${ml.port:8002}")
    private int port;

    @Value("${ml.uid:tom}")
    private String uid;

    @Value("${ml.pw:tom}")
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
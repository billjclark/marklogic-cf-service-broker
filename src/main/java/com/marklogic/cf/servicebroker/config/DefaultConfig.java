package com.marklogic.cf.servicebroker.config;

import com.marklogic.cf.servicebroker.repository.MarkLogicManageAPI;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//@Configuration
@PropertySource("classpath:application.properties")
public class DefaultConfig {

//    @Value("${host}")
//    private String host;
//
//    @Bean
//    public String host() {
//        return host;
//    }

    @Value("${ml.clusterName}")
    private String clusterName;

//    @Bean
//    public String clusterName() {
//        return clusterName;
//    }

    @Value("${ml.port}")
    private int port;

    @Bean
    public int port() {
        return port;
    }

    @Value("${ml.uid}")
    private String uid;

    @Bean
    public String uid() {
        return uid;
    }

    @Value("${ml.pw}")
    private String pw;

    @Bean
    public String pw() {
        return pw;
    }

//    @Bean
//    public MarkLogicManageAPI markLogicManageAPI() {
//        return Feign
//                .builder()
//                .encoder(new GsonEncoder()).decoder(new GsonDecoder())
//                .requestInterceptor(new BasicAuthRequestInterceptor(uid(), pw()))
//                .target(MarkLogicManageAPI.class,
//                        "http://" + host() + ":" + port());
//    }

}
package com.marklogic.cf.servicebroker.config;

import com.marklogic.cf.servicebroker.model.ServiceInstance;
import com.marklogic.cf.servicebroker.repository.MarkLogicManageAPI;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:application.properties")
@Profile("test")
public class Config {

//    @Bean
//    public String host() {
//        return "localhost";
//    }

    @Value("${ml.host}")
    private String host;

    @Bean
    public String clusterName() {
        return "toms-macbook-pro.local";
    }

    @Bean
    public int port() {
        return 8002;
    }

    @Bean
    public String uid() {
        return "admin";
    }

    @Bean
    public String pw() {
        return "admin";
    }

    @Bean
    public MarkLogicManageAPI markLogicManageAPI() {
        return Feign
                .builder()
                .encoder(new GsonEncoder()).decoder(new GsonDecoder())
                .requestInterceptor(new BasicAuthRequestInterceptor(uid(), pw()))
                .target(MarkLogicManageAPI.class,
                        "http://" + host + ":" + port());
    }

}
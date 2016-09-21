package com.marklogic.cf.servicebroker.config;

import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import com.marklogic.cf.servicebroker.repository.MarkLogicManageAPI;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;


@Configuration
@Profile("cloud")
@EnableJpaRepositories("com.marklogic.cf.servicebroker.model")
public class CloudConfig {

    @Bean
    public Cloud cloud() {
        return new CloudFactory().getCloud();
    }

    @Bean
    @ConfigurationProperties(DataSourceProperties.PREFIX)
    public DataSource dataSource() {
        return cloud().getSingletonServiceConnector(DataSource.class, null);
    }

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
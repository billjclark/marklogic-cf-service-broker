package com.marklogic.cf.servicebroker.config;

import com.marklogic.cf.servicebroker.model.ServiceInstance;
import com.marklogic.cf.servicebroker.repository.MarkLogicManageAPI;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

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

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource) {
        return createEntityManagerFactoryBean(dataSource,
                MySQL5Dialect.class.getName());
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    protected LocalContainerEntityManagerFactoryBean createEntityManagerFactoryBean(
            DataSource dataSource, String dialectClassName) {
        Map<String, String> properties = new HashMap<String, String>();
        properties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "update");
        properties.put(org.hibernate.cfg.Environment.DIALECT, dialectClassName);
        properties.put(org.hibernate.cfg.Environment.SHOW_SQL, "true");

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(ServiceInstance.class.getPackage().getName());
        em.setPersistenceProvider(new HibernatePersistenceProvider());
        em.setJpaPropertyMap(properties);
        return em;
    }

}
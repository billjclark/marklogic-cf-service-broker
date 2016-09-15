package com.marklogic.cf.servicebroker.config;

import org.springframework.cloud.servicebroker.model.Catalog;
import org.springframework.cloud.servicebroker.model.Plan;
import org.springframework.cloud.servicebroker.model.ServiceDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
class CatalogService {

    @Bean
    public Catalog catalog() {
        return new Catalog(Collections.singletonList(
                new ServiceDefinition(
                        "marklogic-service-broker",
                        "MarkLogicDB",
                        "A simple MarkLogic service broker implementation",
                        true,
                        false,
                        Collections.singletonList(
                                new Plan("demo-plan",
                                        "DefaultMarkLogicPlan",
                                        "This is a demo plan.",
                                        getPlanMetadata())),
                        Arrays.asList("marklogic", "document"),
                        getServiceDefinitionMetadata(),
                        null,
                        null)));
    }

/* Used by Pivotal CF console */

    private Map<String, Object> getServiceDefinitionMetadata() {
        Map<String, Object> sdMetadata = new HashMap<>();
        sdMetadata.put("displayName", "MarkLogicDB");
        sdMetadata.put("imageUrl", "http://www.marklogic.com/wp-content/themes/marklogic-v2/img/marklogic.png");
        sdMetadata.put("longDescription", "MarkLogic Service");
        sdMetadata.put("providerDisplayName", "MarkLogic");
        sdMetadata.put("documentationUrl", "https://github.com/billjclark/marklogic-cf-service-broker");
        sdMetadata.put("supportUrl", "https://github.com/billjclark/marklogic-cf-service-broker");
        return sdMetadata;
    }

    private Map<String, Object> getPlanMetadata() {
        Map<String, Object> planMetadata = new HashMap<>();
        planMetadata.put("costs", getCosts());
        planMetadata.put("bullets", getBullets());
        return planMetadata;
    }

    private List<Map<String, Object>> getCosts() {
        Map<String, Object> costsMap = new HashMap<>();

        Map<String, Object> amount = new HashMap<>();
        amount.put("usd", 0.0);

        costsMap.put("amount", amount);
        costsMap.put("unit", "MONTHLY");

        return Collections.singletonList(costsMap);
    }

    private List<String> getBullets() {
        return Arrays.asList("Shared MarkLogic",
                "100 MB Storage (not enforced)",
                "40 concurrent connections (not enforced)");
    }
}
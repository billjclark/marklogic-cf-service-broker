package org.springframework.cloud.servicebroker.mongodb.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.servicebroker.model.Catalog;
import org.springframework.cloud.servicebroker.mongodb.Application;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@WebAppConfiguration
public class CatalogTest {

    @Autowired
    private CatalogService catalogService;


    @Test
    public void testCatalog() throws Exception {
        Catalog cat = catalogService.catalog();
        assertNotNull(cat);
    }
}
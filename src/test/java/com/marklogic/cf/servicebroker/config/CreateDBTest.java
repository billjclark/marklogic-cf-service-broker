package com.marklogic.cf.servicebroker.config;

import com.marklogic.cf.servicebroker.Application;
import com.marklogic.cf.servicebroker.repository.MarkLogicManageAPI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@WebAppConfiguration
public class CreateDBTest {

    @Autowired
    private MarkLogicManageAPI markLogicManageAPI;


    @Test
    public void testCreateDB() throws Exception {

        // create content DB
        Map<String, String> m = new HashMap<>();
        m.put("database-name", "5736-fjgd-47563" + "-content");
        markLogicManageAPI.createDatabase(m);

        String dbcreate = markLogicManageAPI.createDatabase(m);
        assertNotNull(dbcreate);
    }
}
package com.marklogic.cf.servicebroker.config;

import com.marklogic.cf.servicebroker.Application;
import com.marklogic.cf.servicebroker.model.ServiceInstance;
import com.marklogic.cf.servicebroker.repository.MarkLogicManageAPI;
import com.marklogic.cf.servicebroker.repository.ServiceInstanceRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@WebAppConfiguration
public class CreateDBTest {

    @Autowired
    private MarkLogicManageAPI markLogicManageAPI;

    @Autowired
    private ServiceInstanceRepository repo;


    @Test
    @Ignore
    public void testCreateDB() throws Exception {

        // create content DB
        Map<String, String> m = new HashMap<>();
        m.put("database-name", "5736-fjgd-47563" + "-content");
        markLogicManageAPI.createDatabase(m);

        String dbcreate = markLogicManageAPI.createDatabase(m);
        assertNotNull(dbcreate);
    }

    @Test
    public void testIt() {
        Map<String, Object> m = new HashMap<>();
        m.put("param1", "value1");
        m.put("param2", "value2");
        m.put("param3", "value3");

        CreateServiceInstanceRequest request = new CreateServiceInstanceRequest("serviceDefinitionId", "planId", "organizationGuid", "spaceGuid", m);
        ServiceInstance si = new ServiceInstance(request.withServiceInstanceId("abcde"));

        si = repo.save(si);
        assertNotNull(si);
        assertEquals("abcde", si.getServiceInstanceId());

        si = repo.findOne(si.getServiceInstanceId());
        assertNotNull(si);

        repo.delete(si);
    }
}

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
import static junit.framework.TestCase.assertNull;
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
    public void testSaveInstanceToLocalDB() throws Exception {

        Map<String, Object> m = new HashMap<>();
        m.put("param1", "value1");
        m.put("param2", "value2");
        m.put("param3", "value3");

        CreateServiceInstanceRequest request = new CreateServiceInstanceRequest("serviceDefinitionId", "planId", "organizationGuid", "spaceGuid", m);

        request.withServiceInstanceId("1234567890");

        com.marklogic.cf.servicebroker.model.ServiceInstance instance = new com.marklogic.cf.servicebroker.model.ServiceInstance(request);

        instance = repo.save(instance);

        //instance.getParameters();

        assertNotNull(instance);

        instance = repo.findOne(instance.getServiceInstanceId());

        assertNotNull(instance);

        instance = repo.findOne("tomiscool");

        assertNull(instance);


    }

    @Test
    public void testMySQLCredsStore() {
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

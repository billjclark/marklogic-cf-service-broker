package com.marklogic.cf.servicebroker.config;

import com.marklogic.cf.servicebroker.Application;
import com.marklogic.cf.servicebroker.model.ServiceInstance;
import com.marklogic.cf.servicebroker.repository.MarkLogicManageAPI;
import com.marklogic.cf.servicebroker.repository.ServiceInstanceRepository;
import com.marklogic.cf.servicebroker.service.MarkLogicServiceInstanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.servicebroker.model.*;
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
public class ServiceInstanceServiceTest {

    @Autowired
    private MarkLogicServiceInstanceService markLogicServiceInstanceService;

    @Autowired
    private ServiceInstanceRepository repo;


    @Test
    //@Ignore
    public void testCreateServiceInstance() throws Exception {

        // create Service Instance

        Map<String, Object> m = new HashMap<>();
        m.put("param1", "value1");
        m.put("param2", "value2");
        m.put("param3", "value3");

        CreateServiceInstanceRequest request = new CreateServiceInstanceRequest("serviceDefinitionId", "planId", "organizationGuid", "spaceGuid", m);

        CreateServiceInstanceResponse serviceInstanceCreate = markLogicServiceInstanceService.createServiceInstance(request.withServiceInstanceId("abcde"));

        // TODO figure out why values are not being stored correctly.

        assertNotNull(serviceInstanceCreate);

//        DeleteServiceInstanceRequest deleteRequest = new DeleteServiceInstanceRequest(request.getServiceInstanceId(), request.getServiceDefinitionId(),
//                request.getPlanId(), request.getServiceDefinition(), request.isAsyncAccepted());
//
//        DeleteServiceInstanceResponse serviceInstanceDelete = markLogicServiceInstanceService.deleteServiceInstance(deleteRequest);
//
//        assertNotNull(serviceInstanceDelete);

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

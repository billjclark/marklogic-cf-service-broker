package com.marklogic.cf.servicebroker.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceDoesNotExistException;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceExistsException;
import org.springframework.cloud.servicebroker.model.*;
import com.marklogic.cf.servicebroker.repository.MarkLogicManageAPI;
import com.marklogic.cf.servicebroker.repository.ServiceInstanceRepository;
import org.springframework.cloud.servicebroker.service.ServiceInstanceService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MarkLogicServiceInstanceService implements ServiceInstanceService {

    private static final Logger LOG = Logger.getLogger(MarkLogicServiceInstanceService.class);

    @Autowired
    private MarkLogicManageAPI markLogicManageAPI;

    @Autowired
    private String host;

    @Autowired
    private String clusterName;

    @Autowired
    private ServiceInstanceRepository repository;

    @Override
    public CreateServiceInstanceResponse createServiceInstance(CreateServiceInstanceRequest request) {
        LOG.info("creating instance with id: " + request.getServiceInstanceId());

        com.marklogic.cf.servicebroker.model.ServiceInstance instance = repository.findOne(request.getServiceInstanceId());
        if (instance != null) {
            throw new ServiceInstanceExistsException(request.getServiceInstanceId(), request.getServiceDefinitionId());
        }

        instance = new com.marklogic.cf.servicebroker.model.ServiceInstance(request);

        // create content DB
        Map<String, String> m = new HashMap<>();
        m.put("database-name", request.getServiceInstanceId() + "-content");

        LOG.info("creating content database");
        markLogicManageAPI.createDatabase(m);

       // repository.save(instance);

        m.clear();

        // Create the modules DB

        //instance = new com.marklogic.cf.servicebroker.model.ServiceInstance(request);

        m.put("database-name", request.getServiceInstanceId() + "-modules");

        LOG.info("creating modules database");
        markLogicManageAPI.createDatabase(m);

       // repository.save(instance);

        m.clear();

        // Create the forests

        //instance = new com.marklogic.cf.servicebroker.model.ServiceInstance(request);

        m.put("forest-name", request.getServiceInstanceId() + "-content-001-1");
        m.put("host", clusterName);
        m.put("database", request.getServiceInstanceId() + "-content");

        LOG.info("creating content forrest");
        markLogicManageAPI.createForest(m);

      ///  repository.save(instance);

        m.clear();

       // instance = new com.marklogic.cf.servicebroker.model.ServiceInstance(request);

        m.put("forest-name", request.getServiceInstanceId() + "-modules-001-1");
        m.put("host", clusterName);
        m.put("database", request.getServiceInstanceId() + "-modules");

        LOG.info("creating modules forrest");
        markLogicManageAPI.createForest(m);

        LOG.info("saving instance");
        repository.save(instance);

        return new CreateServiceInstanceResponse();
    }

    @Override
    public GetLastServiceOperationResponse getLastOperation(GetLastServiceOperationRequest request) {
        return new GetLastServiceOperationResponse().withOperationState(OperationState.SUCCEEDED);
    }

     com.marklogic.cf.servicebroker.model.ServiceInstance getServiceInstance(String id) {
        return repository.findOne(id);
    }

    @Override
    public DeleteServiceInstanceResponse deleteServiceInstance(DeleteServiceInstanceRequest request) {
        String instanceId = request.getServiceInstanceId();
        com.marklogic.cf.servicebroker.model.ServiceInstance instance = repository.findOne(instanceId);
        if (instance == null) {
            throw new ServiceInstanceDoesNotExistException(instanceId);
        }

        //TODO ml db clean up and destroy db and forests

        //Don't think we need this since it already exists.
        //instance = new com.marklogic.cf.servicebroker.model.ServiceInstance(request);

        //Map<String, String> m = new HashMap<>();

        // delete content DB
//        String databaseDelete = "database-name" + request.getServiceInstanceId() + "-content";
//        markLogicManageAPI.deleteDatabase(databaseDelete);
//
//        repository.delete(instanceId);
//
//        // delete modules DB
//        databaseDelete = "database-name" + request.getServiceInstanceId() + "-modules";
//        markLogicManageAPI.deleteDatabase(databaseDelete);
//
//        repository.delete(instanceId);
//
//        // delete content Forest
//        String forestDelete = "forest-name" + request.getServiceInstanceId() + "-content-001-1";
//
//        markLogicManageAPI.deleteForest(forestDelete);
//
//        repository.delete(instanceId);
//
//        //delete modules Forest
//        forestDelete = "forest-name" + request.getServiceInstanceId() + "-modules-001-1";
//
//        markLogicManageAPI.deleteForest(forestDelete);

        repository.delete(instanceId);

        return new DeleteServiceInstanceResponse();
    }

    @Override
    public UpdateServiceInstanceResponse updateServiceInstance(UpdateServiceInstanceRequest request) {
        String instanceId = request.getServiceInstanceId();
        com.marklogic.cf.servicebroker.model.ServiceInstance instance = repository.findOne(instanceId);
        if (instance == null) {
            throw new ServiceInstanceDoesNotExistException(instanceId);
        }

        //TODO add more forests, nodes, indexes.....

        repository.delete(instanceId);
        com.marklogic.cf.servicebroker.model.ServiceInstance updatedInstance = new com.marklogic.cf.servicebroker.model.ServiceInstance(request);
        repository.save(updatedInstance);

        return new UpdateServiceInstanceResponse();
    }
}
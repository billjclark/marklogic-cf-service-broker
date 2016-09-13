package org.springframework.cloud.servicebroker.mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceDoesNotExistException;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceExistsException;
import org.springframework.cloud.servicebroker.model.*;
import org.springframework.cloud.servicebroker.mongodb.exception.MongoServiceException;
import org.springframework.cloud.servicebroker.mongodb.model.ServiceInstance;
import org.springframework.cloud.servicebroker.mongodb.repository.MarkLogicManageAPI;
import org.springframework.cloud.servicebroker.mongodb.repository.ServiceInstanceRepository;
import org.springframework.cloud.servicebroker.service.ServiceInstanceService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MarkLogicServiceInstanceService implements ServiceInstanceService {

    @Autowired
    private MarkLogicManageAPI markLogicManageAPI;

    @Autowired
    private ServiceInstanceRepository repository;

    @Override
    public CreateServiceInstanceResponse createServiceInstance(CreateServiceInstanceRequest request) {
        ServiceInstance instance = repository.findOne(request.getServiceInstanceId());
        if (instance != null) {
            throw new ServiceInstanceExistsException(request.getServiceInstanceId(), request.getServiceDefinitionId());
        }

        instance = new ServiceInstance(request);

        //TODO create content and modules DBs
        Map<String, String> m = new HashMap<>();
        m.put("database-name", request.getServiceInstanceId() + "-content");
        markLogicManageAPI.createDatabase(m);

        repository.save(instance);

        return new CreateServiceInstanceResponse();
    }

    @Override
    public GetLastServiceOperationResponse getLastOperation(GetLastServiceOperationRequest request) {
        return new GetLastServiceOperationResponse().withOperationState(OperationState.SUCCEEDED);
    }

     ServiceInstance getServiceInstance(String id) {
        return repository.findOne(id);
    }

    @Override
    public DeleteServiceInstanceResponse deleteServiceInstance(DeleteServiceInstanceRequest request) throws MongoServiceException {
        String instanceId = request.getServiceInstanceId();
        ServiceInstance instance = repository.findOne(instanceId);
        if (instance == null) {
            throw new ServiceInstanceDoesNotExistException(instanceId);
        }

        //TODO ml db clean up and destroy db and forests

        repository.delete(instanceId);
        return new DeleteServiceInstanceResponse();
    }

    @Override
    public UpdateServiceInstanceResponse updateServiceInstance(UpdateServiceInstanceRequest request) {
        String instanceId = request.getServiceInstanceId();
        ServiceInstance instance = repository.findOne(instanceId);
        if (instance == null) {
            throw new ServiceInstanceDoesNotExistException(instanceId);
        }

        //TODO add more forests, nodes, indexes.....

        repository.delete(instanceId);
        ServiceInstance updatedInstance = new ServiceInstance(request);
        repository.save(updatedInstance);

        return new UpdateServiceInstanceResponse();
    }
}
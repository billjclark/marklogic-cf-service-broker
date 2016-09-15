package com.marklogic.cf.servicebroker.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.marklogic.cf.servicebroker.repository.MarkLogicManageAPI;
import com.marklogic.cf.servicebroker.repository.ServiceInstanceBindingRepository;
import com.marklogic.cf.servicebroker.repository.ServiceInstanceRepository;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceBindingDoesNotExistException;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceBindingExistsException;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceAppBindingResponse;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.model.CreateServiceInstanceBindingResponse;
import org.springframework.cloud.servicebroker.model.DeleteServiceInstanceBindingRequest;
import com.marklogic.cf.servicebroker.model.ServiceInstanceBinding;
import org.springframework.cloud.servicebroker.service.ServiceInstanceBindingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarkLogicServiceInstanceBindingService implements ServiceInstanceBindingService {

	//@Autowired
	//private MarkLogicManageAPI admin;

	@Autowired
	private MarkLogicManageAPI markLogicManageAPI;

	@Autowired
	private ServiceInstanceRepository repository;

	private ServiceInstanceBindingRepository bindingRepository;
	
	@Override
	public CreateServiceInstanceBindingResponse createServiceInstanceBinding(CreateServiceInstanceBindingRequest request) {

		String bindingId = request.getBindingId();
		String serviceInstanceId = request.getServiceInstanceId();

		ServiceInstanceBinding binding = bindingRepository.findOne(bindingId);
		if (binding != null) {
			throw new ServiceInstanceBindingExistsException(serviceInstanceId, bindingId);
		}

		//TODO create the binding via API... Create Roles, Users with those roles and passwords.

		//create role in Security DB
		Map<String, String> m = new HashMap<>();
		m.put("role-name", request.getServiceInstanceId() + "-admin-role");
		markLogicManageAPI.createRole(m);

		m.clear();

		String pw = UUID.randomUUID().toString();

		//create user in Security DB
		m.put("user-name", request.getServiceInstanceId() + "-admin");
		m.put("password", pw);
		m.put("description", request.getServiceInstanceId() + " admin user");
		m.put("role", "[" + request.getServiceInstanceId() + "-admin-role]");
		markLogicManageAPI.createRole(m);

		//m.clear();

		//TODO Put together the VCAP_Services-type variables that are needed. Maybe use the java connection library later.

		Map<String, Object> credentials = null;
		credentials.put("username",request.getServiceInstanceId() + "-admin");
		credentials.put("password",pw);
		binding = new ServiceInstanceBinding(bindingId, serviceInstanceId, credentials, null, request.getBoundAppGuid());
		bindingRepository.save(binding);
		
		return new CreateServiceInstanceAppBindingResponse().withCredentials(credentials);
	}

	@Override
	public void deleteServiceInstanceBinding(DeleteServiceInstanceBindingRequest request) {
		String bindingId = request.getBindingId();
		ServiceInstanceBinding binding = getServiceInstanceBinding(bindingId);

		if (binding == null) {
			throw new ServiceInstanceBindingDoesNotExistException(bindingId);
		}

		//TODO call API to delete stuff
		bindingRepository.delete(bindingId);
	}

	protected ServiceInstanceBinding getServiceInstanceBinding(String id) {
		return bindingRepository.findOne(id);
	}

}

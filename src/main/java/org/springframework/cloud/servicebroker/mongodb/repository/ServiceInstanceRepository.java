package org.springframework.cloud.servicebroker.mongodb.repository;

import org.springframework.cloud.servicebroker.mongodb.model.ServiceInstance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Transactional
@Service
public interface ServiceInstanceRepository extends CrudRepository<ServiceInstance, String> {
}
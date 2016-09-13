package com.marklogic.cf.servicebroker.repository;

import com.marklogic.cf.servicebroker.model.ServiceInstance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Transactional
@Service
public interface ServiceInstanceRepository extends CrudRepository<ServiceInstance, String> {
}
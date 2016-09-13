package org.springframework.cloud.servicebroker.mongodb.repository;

import org.springframework.cloud.servicebroker.mongodb.model.ServiceInstanceBinding;
import org.springframework.data.repository.CrudRepository;

public interface ServiceInstanceBindingRepository extends CrudRepository<ServiceInstanceBinding, String> {

}

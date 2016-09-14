package com.marklogic.cf.servicebroker.repository;

import com.marklogic.cf.servicebroker.model.ServiceInstance;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceInstanceRepository extends
        PagingAndSortingRepository<ServiceInstance, String> {
}
package com.marklogic.cf.servicebroker.fixture;

import com.marklogic.cf.servicebroker.model.ServiceInstance;

public class ServiceInstanceFixture {
	public static ServiceInstance getServiceInstance() {
		return new ServiceInstance("service-instance-id", "service-definition-id", "plan-id",
				"org-guid", "space-guid", "http://dashboard.example.com");
	}
}

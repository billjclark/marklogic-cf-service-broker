package com.marklogic.cf.servicebroker.fixture;

import com.marklogic.cf.servicebroker.model.ServiceInstanceBinding;

import java.util.Collections;
import java.util.Map;

public class ServiceInstanceBindingFixture {
	public static ServiceInstanceBinding getServiceInstanceBinding() {
		Map<String, Object> credentials = Collections.singletonMap("url", (Object) "mongo://example.com");
		return new ServiceInstanceBinding("binding-id", "service-instance-id", credentials, null, "app-guid");
	}
}

package org.springframework.cloud.servicebroker.mongodb.exception;

import org.springframework.cloud.servicebroker.exception.ServiceBrokerException;

public class ServiceException extends MLServiceBrokerException {

	private static final long serialVersionUID = 8667141725171626000L;

	public ServiceException(String message) {
		super(message);
	}
	
}

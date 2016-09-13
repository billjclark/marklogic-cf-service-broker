package org.springframework.cloud.servicebroker.mongodb.exception;

public class ServiceException extends Throwable {

    private static final long serialVersionUID = 8667141725171626000L;

    public ServiceException(String message) {
        super(message);
    }

}

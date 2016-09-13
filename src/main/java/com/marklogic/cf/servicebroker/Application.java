package com.marklogic.cf.servicebroker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.activation.DataSource;

@SpringBootApplication
public class Application {

	/*@Autowired
	private DataSource dataSource;*/

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
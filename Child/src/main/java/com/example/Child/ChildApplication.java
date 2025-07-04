package com.example.Child;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ChildApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChildApplication.class, args);
	}

}

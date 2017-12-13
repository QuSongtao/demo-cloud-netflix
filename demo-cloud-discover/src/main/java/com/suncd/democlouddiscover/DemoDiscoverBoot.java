package com.suncd.democlouddiscover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DemoDiscoverBoot {

	public static void main(String[] args) {
		SpringApplication.run(DemoDiscoverBoot.class, args);
	}
}

package com.javanauta.taskscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableFeignClients
@EnableMongoAuditing
@EnableDiscoveryClient
@SpringBootApplication
public class TaskSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskSchedulerApplication.class, args);
	}

}

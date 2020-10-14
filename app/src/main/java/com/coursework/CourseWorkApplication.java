package com.coursework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan({"com.coursework.db"})
@EnableJpaRepositories(basePackages = {"com.coursework.db.repository"})
@SpringBootApplication
@EnableFeignClients
public class CourseWorkApplication {


	public static void main(String[] args) {
		SpringApplication.run(CourseWorkApplication.class, args);
	}

}

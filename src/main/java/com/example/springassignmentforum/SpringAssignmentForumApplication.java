package com.example.springassignmentforum;

import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringAssignmentForumApplication {
	@Test
	public static void main(String[] args) {
		SpringApplication.run(SpringAssignmentForumApplication.class, args);
	}

}

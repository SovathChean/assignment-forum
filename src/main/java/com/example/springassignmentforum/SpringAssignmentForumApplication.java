package com.example.springassignmentforum;

import com.example.springassignmentforum.core.service.UserService;
import org.junit.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
@EnableJpaRepositories
public class SpringAssignmentForumApplication {

	@Test
	public static void main(String[] args) {
		SpringApplication.run(SpringAssignmentForumApplication.class, args);
	}

}

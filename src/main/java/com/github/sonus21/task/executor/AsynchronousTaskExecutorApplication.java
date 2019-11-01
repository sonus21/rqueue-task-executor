package com.github.sonus21.task.executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableRedisRepositories
@EnableWebMvc
public class AsynchronousTaskExecutorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsynchronousTaskExecutorApplication.class, args);
	}

}

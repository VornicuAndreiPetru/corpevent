package com.corpevent.corpevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.corpevent.corpevent")
@EntityScan("com.corpevent.corpevent")
public class CorpeventApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorpeventApplication.class, args);
	}

}

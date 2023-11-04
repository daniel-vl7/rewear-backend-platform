package com.acme.rewear.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class RewearBackendPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewearBackendPlatformApplication.class, args);
	}

}

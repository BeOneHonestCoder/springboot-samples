package org.springboot.samples.common;

import org.springframework.boot.SpringApplication;

public class SamplesApplicationRunner {

	public static void run(Object source, String... args) {
		SpringApplication springApplication = new SpringApplication(source);
		springApplication.setWebEnvironment(true);
		springApplication.run(args);
	}

}

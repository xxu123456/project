package com.xzx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.xzx.service.IGeneratorService;

@SpringBootApplication
public class GeneratorApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(GeneratorApplication.class, args);
		IGeneratorService bean = run.getBean(IGeneratorService.class);
		bean.generator();
	}
}

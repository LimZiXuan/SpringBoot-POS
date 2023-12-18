package com.CBSEGroup11pos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@EntityScan(basePackages = "com.CBSEGroup11pos.entity")
public class SpringBootPosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPosApplication.class, args);
	}

}

package com.CBSEGroup11pos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.CBSEGroup11pos.entity")
public class SpringBootPosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPosApplication.class, args);
	}

}

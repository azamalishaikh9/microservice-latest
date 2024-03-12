package com.ali.swipeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class SwipeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwipeServiceApplication.class, args);
	}

}

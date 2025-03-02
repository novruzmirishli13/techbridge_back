package com.example.HZT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HztApplication {

	public static void main(String[] args) {
		Integer.parseInt(System.getenv("PORT"));
		SpringApplication.run(HztApplication.class, args);
		
	}

}

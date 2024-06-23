package com.zeroinon.chatterboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class ChatterboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatterboardApplication.class, args);
	}

}




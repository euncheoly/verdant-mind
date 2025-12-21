package com.zeroinon.chatterboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatterboardApplication {

	public static void main(String[] args) {
		System.out.println("############ CI TEST @" + System.currentTimeMillis() + "############");
		SpringApplication.run(ChatterboardApplication.class, args);
	}

}




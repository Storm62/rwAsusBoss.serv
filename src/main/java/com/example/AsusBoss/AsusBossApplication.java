package com.example.AsusBoss;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class AsusBossApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(AsusBossApplication.class, args);
	}

}

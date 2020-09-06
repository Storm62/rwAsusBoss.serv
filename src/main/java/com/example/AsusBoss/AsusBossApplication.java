package com.example.AsusBoss;


import com.example.AsusBoss.service.TlgBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

@SpringBootApplication
public class AsusBossApplication {

	@Autowired
	static TlgBot bot;

	static {
		ApiContextInitializer.init();
	}

	public static void main(String[] args) throws TelegramApiRequestException {
		ApiContextInitializer.init();
		TelegramBotsApi botsApi = new TelegramBotsApi();
		botsApi.registerBot(bot);

		SpringApplication.run(AsusBossApplication.class, args);
	}

}

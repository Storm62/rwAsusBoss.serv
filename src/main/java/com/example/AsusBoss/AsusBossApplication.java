package com.example.AsusBoss;


import com.example.AsusBoss.service.TlgBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

@SpringBootApplication
public class AsusBossApplication implements CommandLineRunner {

	TlgBot bot;
	@Autowired
	public AsusBossApplication(TlgBot bot) {
		this.bot = bot;
	}

	static {
		ApiContextInitializer.init();
	}

	public static void main(String[] args) {
		SpringApplication.run(AsusBossApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		TelegramBotsApi botsApi = new TelegramBotsApi();
		botsApi.registerBot(bot);
	}
}

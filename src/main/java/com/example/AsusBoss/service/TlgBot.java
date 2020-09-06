package com.example.AsusBoss.service;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class TlgBot extends TelegramLongPollingBot {

    /**
     * Метод для приема сообщений.
     * @param update Содержит сообщение от пользователя.
     */
    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        System.out.println("input message: " + message + "\nfrom: " + update.getMessage().toString());
        sendMsg(update.getMessage().getChatId().toString(), message);
    }

    @PostConstruct
    public void born() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
//        TlgBot bot = new TlgBot();
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
        sendMsg("335231553", "Я родился");
//        sendMsg("759471608", "Я родился"); // Oleg
//        sendMsg("346205847", "Я родился"); // Sergey
    }




//    @PostConstruct
//    public static void start() {
//        ApiContextInitializer.init();
//        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
//        TlgBot bot = new TlgBot();
//        try {
//            telegramBotsApi.registerBot(bot);
//        } catch (TelegramApiRequestException e) {
//            e.printStackTrace();
//        }
//
//        bot.sendMsg("335231553", "TEST");
////        bot.sendMsg("759471608", "Я родился"); // Oleg
////        bot.sendMsg("346205847", "Я родился"); // Sergey
//
//    }

    /**
     * Метод для настройки сообщения и его отправки.
     * @param chatId id чата
     * @param s Строка, которую необходимот отправить в качестве сообщения.
     */
    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        setButtons(sendMessage);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return "Noth2Bot";
    }

    @Override
    public String getBotToken() {
        return "1098282169:AAEv9Wt7xfoW34zlrOSzrt6JvHVWhl0Er88";
    }

    public synchronized void setButtons(SendMessage sendMessage) {
        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("Привет"));

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);

        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
    }


}
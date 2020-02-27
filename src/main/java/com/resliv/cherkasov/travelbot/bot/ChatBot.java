package com.resliv.cherkasov.travelbot.bot;

import com.resliv.cherkasov.travelbot.service.AdviceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@PropertySource("classpath:telegram.properties")
public class ChatBot extends TelegramLongPollingBot {

    private static final Logger LOGGER = LogManager.getLogger(ChatBot.class);

    @Autowired
    private AdviceService adviceService;

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    @Override
    public void onUpdateReceived(Update update) {
        final String text = update.getMessage().getText();
        final long chatId = update.getMessage().getChatId();

        if (text == null || text.equals("/start")) {
            sendMessage(chatId, "Добро пожаловать, " +
                    update
                    .getMessage()
                    .getFrom()
                    .getFirstName() +
                    ", в Ваш советник по городам!\n" +
                    "Введите, пожалуйста, город");
        } else {
            LOGGER.info("User: {} requested advice for city: {}.", chatId, text);

            final String advice = adviceService.getAdviceByCityName(text);
            sendMessage(chatId, advice);
        }
    }

    private void sendMessage(long chatId, String advice) {
        SendMessage message = new SendMessage()
                .setChatId(chatId)
                .setText(advice);
        try {
            execute(message);
            LOGGER.info("Response for [{}] was sent.", chatId);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}

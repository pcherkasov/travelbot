package com.resliv.cherkasov.travelbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class TravelbotApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(TravelbotApplication.class, args);
    }

}

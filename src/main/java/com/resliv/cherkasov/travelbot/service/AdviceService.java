package com.resliv.cherkasov.travelbot.service;

import com.resliv.cherkasov.travelbot.bot.ChatBot;
import com.resliv.cherkasov.travelbot.model.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdviceService {

    private static final Logger LOGGER = LogManager.getLogger(AdviceService.class);

    private final CityService cityService;

    public AdviceService(CityService cityService) {
        this.cityService = cityService;
    }

    public String getAdviceByCityName(String cityName) {
        Optional<City> city = cityService.getCityByName(cityName.toUpperCase());
        if (!city.isPresent()) {

            return "Христа ради простите, но инфы для города [" + cityName + "] ещё нет.";
        } else {
            return city.get().getAdvice();
        }
    }

}

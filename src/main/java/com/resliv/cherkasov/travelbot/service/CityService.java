package com.resliv.cherkasov.travelbot.service;

import com.resliv.cherkasov.travelbot.model.City;
import com.resliv.cherkasov.travelbot.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City addCity(City city) {
        return cityRepository.save(city);
    }

    public Optional<City> getCityByName(String cityName) {
        return cityRepository.getCityByName(cityName);
    }

    public Optional<City> getCityById(int id) {
        return cityRepository.getCityById(id);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City updateCity(City city) {
        return cityRepository.save(city);
    }

    public void deleteCity(City city) {
        cityRepository.delete(city);
    }
}

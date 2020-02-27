package com.resliv.cherkasov.travelbot.repository;

import com.resliv.cherkasov.travelbot.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
    Optional<City> getCityByName(String name);
    Optional<City> getCityById(int id);
}

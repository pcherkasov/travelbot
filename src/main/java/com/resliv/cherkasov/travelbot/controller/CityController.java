package com.resliv.cherkasov.travelbot.controller;

import com.resliv.cherkasov.travelbot.model.City;
import com.resliv.cherkasov.travelbot.service.CityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {

    private static final Logger LOGGER = LogManager.getLogger(CityController.class);

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<City>> findAll() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @PostMapping
    public ResponseEntity<City> create(@RequestBody City city) {
        return ResponseEntity.ok(cityService.addCity(city));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        Optional<City> city = cityService.getCityById(id);
        if (!city.isPresent()) {
            LOGGER.error("ID: {} is not existed", id);
            return ResponseEntity.badRequest().body("City with id: " + id + " is not existed.");
        }
        return ResponseEntity.ok(city.get());
    }

    @GetMapping("/{name}")
    public ResponseEntity findByName(@PathVariable String name) {
        Optional<City> city = cityService.getCityByName(name);
        if (!city.isPresent()) {
            LOGGER.error("City: {} is not existed", name);
            return ResponseEntity.badRequest().body("City: " + name + " is not existed.");
        }
        return ResponseEntity.ok(city.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody City city) {
        Optional<City> cityOptional = cityService.getCityById(id);
        if (!cityOptional.isPresent()) {
            LOGGER.error("ID: {} is not existed", id);
            return ResponseEntity.badRequest().body("City with id: " + id + " is not existed.");
        }
        return ResponseEntity.ok(cityService.updateCity(cityOptional.get()));
    }

    @PutMapping("/{name}")
    public ResponseEntity update(@PathVariable String name, @RequestBody City city) {
        Optional<City> cityOptional = cityService.getCityByName(name.toUpperCase());
        if (!cityOptional.isPresent()) {
            LOGGER.error("City: {} is not existed", name);
            return ResponseEntity.badRequest().body("City: " + name + " is not existed.");
        }
        return ResponseEntity.ok(cityService.updateCity(cityOptional.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        Optional<City> cityOptional = cityService.getCityById(id);
        if (!cityOptional.isPresent()) {
            LOGGER.error("ID: {} is not existed", id);
            return ResponseEntity.badRequest().body("City with id: " + id + " is not existed.");
        }
        cityService.deleteCity(cityOptional.get());
        return ResponseEntity.ok().build();
    }
}

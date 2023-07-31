package fr.fms.controller;

import fr.fms.entities.City;
import fr.fms.services.CityServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class CityController {
    @Autowired
    private CityServiceImpl cityService;

    @GetMapping("/cities")
    public ResponseEntity<List<City>> allCities(){
        List<City> allCities = cityService.getAllCities();
        return ResponseEntity.ok().body(allCities);
    }


}

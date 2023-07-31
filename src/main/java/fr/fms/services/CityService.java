package fr.fms.services;


import fr.fms.entities.City;

import java.util.List;

public interface CityService {
    public City saveCity(City city);
    public List<City> getAllCities();
}

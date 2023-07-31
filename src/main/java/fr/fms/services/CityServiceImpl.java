package fr.fms.services;

import fr.fms.entities.City;
import fr.fms.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;
    /**
     * @param city
     * @return
     */
    @Override
    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    /**
     * @return
     */
    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    /**
     * @param kw
     * @return
     */
    @Override
    public List<City> searchCitiesByName(String kw) {
        return cityRepository.findCitiesByNameContains(kw);
    }
}

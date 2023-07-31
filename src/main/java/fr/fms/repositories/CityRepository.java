package fr.fms.repositories;


import fr.fms.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findCitiesByNameContains(String keyword);

}
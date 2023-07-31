package fr.fms.services;

import fr.fms.entities.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface HotelService {
    public Hotel saveHotel(Hotel hotel);
    public Page<Hotel> getAllHotels(PageRequest pageRequest);
    public Optional<Hotel> readTraining(Long id);
    public Page<Hotel>getHotelsByCity(PageRequest pageRequest, Long id);



}

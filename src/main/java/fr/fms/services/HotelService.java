package fr.fms.services;

import fr.fms.entities.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface HotelService {
    public Hotel saveHotel(Hotel hotel);
    public Page<Hotel> getAllHotels(PageRequest pageRequest);



}

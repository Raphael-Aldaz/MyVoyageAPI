package fr.fms.services;

import fr.fms.entities.Hotel;
import fr.fms.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Page<Hotel> getAllHotels(PageRequest pageRequest) {
        return hotelRepository.findAll(pageRequest);
    }
}

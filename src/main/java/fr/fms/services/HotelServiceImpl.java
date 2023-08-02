package fr.fms.services;

import fr.fms.entities.Hotel;
import fr.fms.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Hotel> readTraining(Long id) {
        return hotelRepository.findById(id);
    }

    /**
     * @param pageRequest
     * @param id
     * @return
     */
    @Override
    public Page<Hotel> getHotelsByCity(PageRequest pageRequest, Long id) {
        return hotelRepository.getHotelsByCityId(pageRequest, id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).get();
    }

    /**
     * @param id
     */
    @Override
    public void deleteHotel(long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isPresent()){
           Hotel hotelToDelete =  hotel.get();
            hotelRepository.delete(hotelToDelete);
        }
    }
}

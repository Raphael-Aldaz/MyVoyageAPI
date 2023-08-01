package fr.fms.controller;

import fr.fms.entities.City;
import fr.fms.entities.Hotel;
import fr.fms.services.CityServiceImpl;
import fr.fms.services.HotelServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
@CrossOrigin("*")
public class HotelsController {
    @Autowired
    private HotelServiceImpl hotelService;
    @Autowired
    private CityServiceImpl cityService;
    @GetMapping("/hotels")
    public ResponseEntity<Page<Hotel>> allHotels(@RequestParam("page") int page){
        Page<Hotel> listHotel = null;
        try{
            PageRequest pageRequest = PageRequest.of(page, 6);
            listHotel = hotelService.getAllHotels(pageRequest);

        } catch (Exception e){
            log.info(e.getMessage());
        }
        return ResponseEntity.ok().body(listHotel);
    }
    @GetMapping("/hotelsBy")
    public ResponseEntity<Page<Hotel>> hotelsByCity(@RequestParam("page") int page,
                                                    @RequestParam("id") int id){
        PageRequest pageRequest = PageRequest.of(page,6);
        Page<Hotel> listHotel = hotelService.getHotelsByCity(pageRequest, (long) id);
        return ResponseEntity.ok().body(listHotel);
    }
    @GetMapping("/hotelsByDest")
    public ResponseEntity<Page<Hotel>> searchCitiesByKeyword(@RequestParam("page") int page,
                                                            @RequestParam("kw") String keyword) {
        List<City> cities = cityService.searchCitiesByName(keyword);
        if (cities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            City city = cities.get(0);
            Long id = city.getId();
            PageRequest pageRequest = PageRequest.of(page,6);
            Page<Hotel> hotels = hotelService.getHotelsByCity(pageRequest, id);
            return new ResponseEntity<>(hotels, HttpStatus.OK);
        }
    }


    @GetMapping(path="/photo/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<?> getPhoto(@PathVariable("id") Long id) throws IOException {
        byte[] file = null;
        try {
            Hotel hotel = hotelService.readTraining(id).get();
            if(hotel.getPicture() == null) hotel.setPicture("unknown.png");
            file = Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/voyage/images/" + hotel.getPicture()));
        }
        catch (Exception e) {
            log.error("pb avec download de l'image correspondant à la formation d'id : {}",id);
            return ResponseEntity.internalServerError().body(e.getCause());
        }
        return ResponseEntity.ok().body(file);
    }
}

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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
        City cities = cityService.searchCitiesByName(keyword);

            Long id = cities.getId();
            PageRequest pageRequest = PageRequest.of(page,6);
            Page<Hotel> hotels = hotelService.getHotelsByCity(pageRequest, id);
            return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<Hotel> hotelById(@PathVariable("id") Long id){
        Hotel hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok().body(hotel);
    }

    @PostMapping("/hotel")
    public ResponseEntity<Hotel> newHotel(@RequestParam("name") String name,
                                             @RequestParam("description") String description,
                                             @RequestParam("address") String address,
                                             @RequestParam("phone") String phone,
                                             @RequestParam("price") double price,
                                             @RequestParam("rate") int rate,
                                             @RequestParam("picture") MultipartFile picture,
                                             @RequestParam("cityName") String cityName,
                                             @RequestParam("country") String country
    ) throws IOException {
        Hotel newHotel = new Hotel();
        City city = cityService.searchCitiesByName(cityName);
        Path directory = Paths.get(System.getProperty("user.home") + "/voyage/images");
        city.setName(cityName);
        city.setCountry(country);
        newHotel.setName(name);
        newHotel.setDescription(description);
        newHotel.setAddress(address);
        newHotel.setPicture(picture.getOriginalFilename());
        newHotel.setPhone(phone);
        newHotel.setPrice(price);
        newHotel.setRate(rate);
        newHotel.setCity(city);
        Files.write(Paths.get( directory + "/" + newHotel.getPicture()),picture.getBytes());

        hotelService.saveHotel(newHotel);
        return ResponseEntity.ok().body(newHotel);
    }

    @PutMapping("/hotels/{id}")
    public ResponseEntity<Hotel> updateHotel(@RequestParam("name") String name,
                                             @RequestParam("description") String description,
                                             @RequestParam("address") String address,
                                             @RequestParam("phone") String phone,
                                             @RequestParam("price") double price,
                                             @RequestParam("rate") int rate,
                                             @RequestParam("picture") MultipartFile picture,
                                             @RequestParam("cityName") String cityName,
                                             @RequestParam("country") String country,
                                             @PathVariable("id") Long id
                                             ) throws IOException {
     Hotel hotel = hotelService.getHotelById(id);
     City city = cityService.searchCitiesByName(cityName);
     Path directory = Paths.get(System.getProperty("user.home") + "/voyage/images");
     city.setName(cityName);
     city.setCountry(country);
     hotel.setName(name);
     hotel.setDescription(description);
     hotel.setAddress(address);
     hotel.setPicture(picture.getOriginalFilename());
     hotel.setPhone(phone);
     hotel.setPrice(price);
     hotel.setRate(rate);
     hotel.setCity(city);
        Files.write(Paths.get( directory + "/" + hotel.getPicture()),picture.getBytes());

     hotelService.saveHotel(hotel);
     return ResponseEntity.ok().body(hotel);
    }

    @DeleteMapping("/hotel/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable("id") Long id) {
        try {
            hotelService.deleteHotel(id);
            return ResponseEntity.ok("Hotel deleted successfully");
        } catch (Exception e) {
            log.error("Error deleting hotel: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting hotel");
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

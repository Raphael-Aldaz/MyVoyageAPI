package fr.fms.controller;

import fr.fms.entities.Hotel;
import fr.fms.services.HotelServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api")
public class HotelsController {
    @Autowired
    private HotelServiceImpl hotelService;
    @GetMapping("/hotels")
    public ResponseEntity<Page<Hotel>> allHotels(@RequestParam("page") int page){
        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Hotel> listHotel = hotelService.getAllHotels(pageRequest);
        return ResponseEntity.ok().body(listHotel);
    }
}

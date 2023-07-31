package fr.fms.repositories;

import fr.fms.entities.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
  public  Page<Hotel> getHotelsByCityId(Pageable page, Long id);
}
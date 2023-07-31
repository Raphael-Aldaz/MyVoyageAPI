package fr.fms;

import fr.fms.entities.AppRole;
import fr.fms.entities.AppUser;
import fr.fms.entities.City;
import fr.fms.entities.Hotel;
import fr.fms.services.AccountServiceImpl;
import fr.fms.services.CityServiceImpl;
import fr.fms.services.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class ApiMyVoyageApplication  implements CommandLineRunner {
    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    CityServiceImpl cityService;
    @Autowired
    HotelServiceImpl hotelService;

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiMyVoyageApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        //generatedData();
        //generateUsersRoles();

    }


    private void generatedData(){
        City paris = cityService.saveCity(new City(null, "Paris", "France", null));
        City newYork  = cityService.saveCity(new City(null,"New York", "United States",null));
        City tokyo  = cityService.saveCity(new City(null,"Tokyo", "Japan", null));
        City london  = cityService.saveCity(new City(null,"London", "United Kingdom", null));
        City rome  = cityService.saveCity(new City(null,"Rome", "Italy", null));

        hotelService.saveHotel(new Hotel(null, "Petit Paris" , "https://example.com/paris.jpg", 3, "0554286573", "42 rue Dauphine 75006 Paris",
                paris));
        hotelService.saveHotel(new Hotel(null, "Elegant Paris Hotel", "https://example.com/paris.jpg", 4, "+33 1 55 55 55 55", "15 Avenue Montaigne, 75008 Paris", paris));
        hotelService.saveHotel(new Hotel(null, "Luxury New York Hotel", "https://example.com/newyork.jpg", 5, "+1 212-555-5555", "123 Broadway, New York, NY 10001", newYork));
        hotelService.saveHotel(new Hotel(null, "Tokyo Garden Hotel", "https://example.com/tokyo.jpg", 4, "+81 3-5555-5555", "2 Chome-1 Nihonbashi Muromachi, Chuo City, Tokyo 103-8328", tokyo));
        hotelService.saveHotel(new Hotel(null, "Royal London Hotel", "https://example.com/london.jpg", 5, "+44 20 5555 5555", "10 Park Ln, Mayfair, London W1K 1BE, United Kingdom", london));
        hotelService.saveHotel(new Hotel(null, "Ancient Rome Resort", "https://example.com/rome.jpg", 4, "+39 06 5555 5555", "Via dei Fori Imperiali, 00186 Rome, Italy", rome));
        hotelService.saveHotel(new Hotel(null, "Charming Parisian Hotel", "https://example.com/paris2.jpg", 3, "+33 1 55 55 55 55", "10 Rue de Rivoli, 75004 Paris", paris));
        hotelService.saveHotel(new Hotel(null, "Times Square Plaza", "https://example.com/newyork2.jpg", 4, "+1 212-555-5555", "1 Times Square, New York, NY 10036", newYork));
        hotelService.saveHotel(new Hotel(null, "Tokyo Skyline View Hotel", "https://example.com/tokyo2.jpg", 4, "+81 3-5555-5555", "1 Chome-3-1 Oshiage, Sumida City, Tokyo 131-0045", tokyo));
        hotelService.saveHotel(new Hotel(null, "Central London Boutique", "https://example.com/london2.jpg", 4, "+44 20 5555 5555", "45 Oxford St, Soho, London W1D 2DZ, United Kingdom", london));
        hotelService.saveHotel(new Hotel(null, "Renaissance Rome", "https://example.com/rome2.jpg", 5, "+39 06 5555 5555", "1 Piazza della Repubblica, 00185 Rome, Italy", rome));


    }

    private void generateUsersRoles(){
        accountService.saveUser(new AppUser(null, "raphe", "1234", new ArrayList<>(), new ArrayList<>()));
        accountService.saveUser(new AppUser(null, "bob", "1234", new ArrayList<>(), new ArrayList<>()));

        accountService.saveRole(new AppRole(null, "SUPERVISEUR"));
        accountService.saveRole(new AppRole(null, "GESTIONNAIRE"));

        accountService.addRoleToUser("raphe", "SUPERVISEUR");
        accountService.addHotelToUser("raphe", 1L);

        accountService.addRoleToUser("bob", "SUPERVISEUR");
        accountService.addHotelToUser("bob", 1L);
    }

}

package fr.fms.services;


import fr.fms.entities.AppUser;
import fr.fms.entities.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface UserService {
    public void deleteUser(long id);
}

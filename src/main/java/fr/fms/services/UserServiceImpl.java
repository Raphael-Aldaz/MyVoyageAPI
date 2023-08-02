package fr.fms.services;


import fr.fms.repositories.AppUserRepository;
import org.springframework.stereotype.Service;




@Service
public class UserServiceImpl implements UserService {

    private final AppUserRepository appUserRepository;

    public UserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    /**
     * @param id
     */
    @Override
    public void deleteUser(long id) {
        appUserRepository.deleteById(id);
    }
}

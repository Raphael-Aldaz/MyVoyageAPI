package fr.fms.services;

import fr.fms.entities.AppRole;
import fr.fms.entities.AppUser;
import fr.fms.entities.Hotel;
import fr.fms.repositories.AppRoleRepository;
import fr.fms.repositories.AppUserRepository;
import fr.fms.repositories.HotelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;


    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public AppUser saveUser(AppUser user) {
        String hashPW = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPW);
        log.info("Sauvegarde d'un nouvel utilisateur {}", user);
        return appUserRepository.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        log.info("sauvegarde d'un nouveau role en base ");
        return appRoleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        AppRole role = appRoleRepository.findByRoleName(rolename);
        AppUser user = appUserRepository.findByUsername(username);
        user.getRole().add(role);
        log.info("Association d'un role à un utilisateur");
    }
    @Override
    public AppUser findUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    /**
     * @param pageRequest
     * @return
     */
    @Override
    public Page<AppUser> listUser(PageRequest pageRequest) {
        return appUserRepository.findAll(pageRequest);
    }
}

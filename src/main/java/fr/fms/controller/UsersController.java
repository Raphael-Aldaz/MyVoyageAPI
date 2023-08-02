package fr.fms.controller;

import fr.fms.entities.AppUser;
import fr.fms.services.AccountServiceImpl;
import fr.fms.services.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@Slf4j
@RequestMapping("/api")
@CrossOrigin("*")
public class UsersController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    AccountServiceImpl accountService;

    @GetMapping("/users")
    public ResponseEntity<Page<AppUser>> getAllUsers(@RequestParam("page") int page){
        Page<AppUser> listUsers = null;
        try {
            PageRequest pageRequest = PageRequest.of(page, 6);
            listUsers = accountService.listUser(pageRequest);
            return ResponseEntity.ok().body(listUsers);
        } catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e){
            log.error("Error deleting user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user");
        }
    }

    @PostMapping("/user")
    public ResponseEntity<String> newUser(@RequestParam("username") String username,
                                           @RequestParam("password")String password,
                                           @RequestParam("role")String role){
        try {
            accountService.saveUser(new AppUser(null, username, password, new ArrayList<>(), new ArrayList<>()));
            accountService.addRoleToUser(username, role);
            return ResponseEntity.ok("User created successfully");
        } catch (Exception e){
            log.error("Error creating user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user");
        }
    }
}

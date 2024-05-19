package api.englishAPI.controller;


import api.englishAPI.dto.UserDTO;
import api.englishAPI.model.Users;
import api.englishAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Users> getAllUsers() {return userService.listAllUsers();}

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable("id") UUID id) {
        return userService.showUserData(id);
    }



    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDTO user) {
        try {
            if (userService.saveNewUser(user)) {
                return ResponseEntity.status(HttpStatus.CREATED).body("User created");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the user");
        }
    }



    @DeleteMapping("/reset")
    public void reset() {
        userService.deleteAllUsers();
    }
}

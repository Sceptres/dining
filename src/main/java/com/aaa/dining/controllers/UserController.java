package com.aaa.dining.controllers;

import com.aaa.dining.entities.User;
import com.aaa.dining.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path="/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all users
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return new ResponseEntity<Iterable<User>>(this.userRepository.findAll(), HttpStatus.OK);
    }

    // Returns the user provided the user's display name
    @GetMapping(path="/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
        Optional<User> userOptional = this.userRepository.findByName(username);

        if (userOptional.isEmpty()) return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<User>(userOptional.get(), HttpStatus.OK);
    }

    // Create a new user
    @PostMapping(path="/new/signup")
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        // Is the json data missing information
        if (
                Objects.isNull(user.getName()) || Objects.isNull(user.getCity()) ||
                Objects.isNull(user.getState()) || Objects.isNull(user.getZipcode()) ||
                Objects.isNull(user.getIsPeanutAllergic()) || Objects.isNull(user.getIsEggAllergic()) ||
                Objects.isNull(user.getIsDairyAllergic()) || Objects.nonNull(user.getId())
        ) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }

        // Is there a user with the same username
        if (this.userRepository.existsUserByName(user.getName())) {
            return new ResponseEntity<User>(HttpStatus.CONFLICT);
        }

        // Save the new user to the database
        return new ResponseEntity<User>(this.userRepository.save(user), HttpStatus.CREATED);
    }

}

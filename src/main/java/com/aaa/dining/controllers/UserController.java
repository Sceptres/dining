package com.aaa.dining.controllers;

import com.aaa.dining.entities.User;
import com.aaa.dining.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path="/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Returns the user provided the user's display name
    @GetMapping(path="/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
        Optional<User> userOptional = this.userRepository.findByName(username);

        if (userOptional.isEmpty()) return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<User>(userOptional.get(), HttpStatus.OK);
    }

}

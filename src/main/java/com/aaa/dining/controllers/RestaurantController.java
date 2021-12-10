package com.aaa.dining.controllers;

import com.aaa.dining.entities.Restaurant;
import com.aaa.dining.repositories.DiningReviewRepository;
import com.aaa.dining.repositories.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/restaurants")
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;
    private final DiningReviewRepository diningReviewRepository;

    public RestaurantController(
            final RestaurantRepository restaurantRepository,
            final DiningReviewRepository diningReviewRepository
    ) {
        this.restaurantRepository = restaurantRepository;
        this.diningReviewRepository = diningReviewRepository;
    }

    // Returns the restaurant provided the correct id
    @GetMapping(path="/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable(name="restaurantId") Long id) {
        Optional<Restaurant> restaurantOptional = this.restaurantRepository.findById(id);

        if (restaurantOptional.isEmpty()) return new ResponseEntity<Restaurant>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Restaurant>(restaurantOptional.get(), HttpStatus.OK);
    }
}

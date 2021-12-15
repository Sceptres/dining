package com.aaa.dining.controllers;

import com.aaa.dining.entities.Restaurant;
import com.aaa.dining.exceptions.QueryNotSupportedException;
import com.aaa.dining.repositories.DiningReviewRepository;
import com.aaa.dining.repositories.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
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

    // Get all restaurants
    @GetMapping
    public ResponseEntity<Iterable<Restaurant>> getAllRestaurants() {
        return new ResponseEntity<Iterable<Restaurant>>(this.restaurantRepository.findAll(), HttpStatus.OK);
    }

    // Returns the restaurant provided the correct id
    @GetMapping(path="/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable(name="restaurantId") Long id) {
        Optional<Restaurant> restaurantOptional = this.restaurantRepository.findById(id);

        if (restaurantOptional.isEmpty()) return new ResponseEntity<Restaurant>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Restaurant>(restaurantOptional.get(), HttpStatus.OK);
    }

    // Adds a new Restaurant to the database
    @PostMapping(path="/new")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        // Is the input missing any required information?
        // Does the information have data that should not be set? ie allergy fields
        if (
            Objects.isNull(restaurant.getName()) || Objects.isNull(restaurant.getCity()) ||
            Objects.isNull(restaurant.getState()) || Objects.isNull(restaurant.getZipcode()) ||
            Objects.nonNull(restaurant.getPeanut()) || Objects.nonNull(restaurant.getEgg()) ||
            Objects.nonNull(restaurant.getDairy())
        ) {
            return new ResponseEntity<Restaurant>(HttpStatus.BAD_REQUEST);
        }

        // Does the restaurant already exist?
        if (this.restaurantRepository.existsRestaurantByNameAndZipcode(restaurant.getName(), restaurant.getZipcode())) {
            return new ResponseEntity<Restaurant>(HttpStatus.CONFLICT);
        }

        // Save the new restaurant
        return new ResponseEntity<Restaurant>(this.restaurantRepository.save(restaurant), HttpStatus.CREATED);
    }

    // Searches restaurants by zipcode and specific allergy
    @GetMapping(path="/search")
    public ResponseEntity<List<Restaurant>> searchRestaurants(
            @RequestParam(name="zipcode", required = false) String zipcode,
            @RequestParam(name="allergy", required = false) String allergy
    ) throws QueryNotSupportedException {
        if (Objects.nonNull(zipcode) && Objects.nonNull(allergy)) { // Search by zipcode and allergy
            return new ResponseEntity<>(this.restaurantRepository.findByZipcodeAndAllergyDesc(zipcode, allergy), HttpStatus.OK);
        } else if (Objects.nonNull(zipcode)) { // Search only by zipcode
            return new ResponseEntity<>(this.restaurantRepository.findByZipcode(zipcode), HttpStatus.OK);
        } else if (Objects.nonNull(allergy)) { // Search only by allergy
            return new ResponseEntity<>(this.restaurantRepository.findByAllergyDesc(allergy), HttpStatus.OK);
        }

        // Is the input invalid
        throw new QueryNotSupportedException("Missing required search fields!");
    }
}

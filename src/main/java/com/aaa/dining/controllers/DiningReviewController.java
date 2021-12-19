package com.aaa.dining.controllers;

import com.aaa.dining.entities.DiningReview;
import com.aaa.dining.entities.Restaurant;
import com.aaa.dining.repositories.DiningReviewRepository;
import com.aaa.dining.repositories.RestaurantRepository;
import com.aaa.dining.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path="/reviews")
public class DiningReviewController {

    private final DiningReviewRepository diningReviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public DiningReviewController(
            final DiningReviewRepository diningReviewRepository,
            final RestaurantRepository restaurantRepository,
            final UserRepository userRepository
            ) {
        this.diningReviewRepository = diningReviewRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    // Get all dining reviews
    @GetMapping
    public ResponseEntity<Iterable<DiningReview>> getAllDiningReviews() {
        return new ResponseEntity<Iterable<DiningReview>>(this.diningReviewRepository.findAll(), HttpStatus.OK);
    }

    // Gets a dining review given the database id
    @GetMapping(path="/{reviewId}")
    public ResponseEntity<DiningReview> getDiningReview(@PathVariable(name="reviewId") Long reviewId) {
        Optional<DiningReview> diningReviewOptional = this.diningReviewRepository.findById(reviewId);

        // Was the review not found?
        if (diningReviewOptional.isEmpty()) return new ResponseEntity<DiningReview>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<DiningReview>(diningReviewOptional.get(), HttpStatus.OK);
    }

    // Adds a new dining review to the database
    @PostMapping(path="/new")
    public ResponseEntity<DiningReview> createDiningReview(@RequestBody DiningReview diningReview) {
        // Is the provided info missing any information?
        // Is the request trying to set the dining review status?
        if (
                Objects.isNull(diningReview.getName()) || Objects.isNull(diningReview.getRestaurantId()) ||
                Objects.isNull(diningReview.getPeanutScore()) || Objects.isNull(diningReview.getEggScore()) ||
                Objects.isNull(diningReview.getDairyScore()) || Objects.nonNull(diningReview.getStatus()) ||
                Objects.nonNull(diningReview.getId())
        ) {
            return new ResponseEntity<DiningReview>(HttpStatus.BAD_REQUEST);
        }

        // Does the restaurant not exist in the database?
        // Is the user trying to submit this review not signed up?
        if (!this.restaurantRepository.existsById(diningReview.getRestaurantId()) || !this.userRepository.existsUserByName(diningReview.getName()))
            return new ResponseEntity<DiningReview>(HttpStatus.NOT_FOUND);

        // Did the user already review this restaurant?
        if (this.diningReviewRepository.existsDiningReviewByNameAndRestaurantId(diningReview.getName(), diningReview.getRestaurantId()))
            return new ResponseEntity<DiningReview>(HttpStatus.CONFLICT);

        // Create the dining review
        diningReview.setStatus(false);
        return new ResponseEntity<DiningReview>(this.diningReviewRepository.save(diningReview), HttpStatus.CREATED);
    }
}

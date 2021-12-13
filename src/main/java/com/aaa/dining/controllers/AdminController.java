package com.aaa.dining.controllers;

import com.aaa.dining.entities.DiningReview;
import com.aaa.dining.entities.Restaurant;
import com.aaa.dining.repositories.DiningReviewRepository;
import com.aaa.dining.repositories.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/admin")
public class AdminController {

    private final DiningReviewRepository diningReviewRepository;
    private final RestaurantRepository restaurantRepository;

    public AdminController(
            final DiningReviewRepository diningReviewRepository,
            final RestaurantRepository restaurantRepository
    ) {
        this.diningReviewRepository = diningReviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    // Get all pending dining reviews
    @GetMapping(path="/pendingReviews")
    public ResponseEntity<List<DiningReview>> getPendingReviews() {
        return new ResponseEntity<List<DiningReview>>(this.diningReviewRepository.findByStatus(false), HttpStatus.OK);
    }

    // Update the dining review status
    @PutMapping(path="/pendingReviews/{reviewId}")
    public ResponseEntity<DiningReview> updateReviewStatus(
            @PathVariable(name="reviewId") Long id,
            @RequestParam(name="status") boolean status
    ) {
        Optional<DiningReview> diningReviewOptional = this.diningReviewRepository.findById(id);

        // Does the dining review with id not exist in the database?
        if (diningReviewOptional.isEmpty())
            return new ResponseEntity<DiningReview>(HttpStatus.NOT_FOUND);

        // Update dining review status
        DiningReview diningReview = diningReviewOptional.get();
        diningReview.setStatus(status);
        diningReview = this.diningReviewRepository.save(diningReview);

        if (status)
            this.updateRestaurantAllergyScores(diningReview);

        return new ResponseEntity<DiningReview>(diningReview, HttpStatus.OK);
    }

    // Updates the average allergy score for the restaurant
    // provided by the review
    private void updateRestaurantAllergyScores(DiningReview review) {
        Optional<Restaurant> restaurantOptional = this.restaurantRepository.findById(review.getRestaurantId());
        Restaurant restaurant = restaurantOptional.orElseThrow();

        // Get approved reviews
        List<DiningReview> diningReviews = this.diningReviewRepository.findByRestaurantIdAndStatus(restaurant.getId(), true);

        // Calculate averages
        Float peanutAllergyAverage = diningReviews.parallelStream().map(DiningReview::getPeanutScore).reduce(0f, Float::sum)/diningReviews.size();
        Float eggAllergyAverage = diningReviews.parallelStream().map(DiningReview::getEggScore).reduce(0f, Float::sum)/diningReviews.size();
        Float dairyAllergyAverage = diningReviews.parallelStream().map(DiningReview::getDairyScore).reduce(0f, Float::sum)/diningReviews.size();

        // Set new averages to restaurant entity
        restaurant.setPeanut(peanutAllergyAverage);
        restaurant.setEgg(eggAllergyAverage);
        restaurant.setDairy(dairyAllergyAverage);

        // Update restaurant
        this.restaurantRepository.save(restaurant);
    }

}

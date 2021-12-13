package com.aaa.dining.controllers;

import com.aaa.dining.entities.DiningReview;
import com.aaa.dining.repositories.DiningReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(name="/reviews")
public class DiningReviewController {

    private final DiningReviewRepository diningReviewRepository;

    public DiningReviewController(final DiningReviewRepository diningReviewRepository) {
        this.diningReviewRepository = diningReviewRepository;
    }

    // Gets a dining review given the database id
    @GetMapping(path="/{reviewId}")
    public ResponseEntity<DiningReview> getDiningReview(@PathVariable(name="reviewId") Long reviewId) {
        Optional<DiningReview> diningReviewOptional = this.diningReviewRepository.findById(reviewId);

        // Was the review not found?
        if (diningReviewOptional.isEmpty()) return new ResponseEntity<DiningReview>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<DiningReview>(diningReviewOptional.get(), HttpStatus.OK);
    }
}

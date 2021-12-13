package com.aaa.dining.controllers;

import com.aaa.dining.entities.DiningReview;
import com.aaa.dining.repositories.DiningReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/admin")
public class AdminController {

    private final DiningReviewRepository diningReviewRepository;

    public AdminController(final DiningReviewRepository diningReviewRepository) {
        this.diningReviewRepository = diningReviewRepository;
    }


    // Get all pending dining reviews
    @GetMapping(path="/pendingReviews")
    public ResponseEntity<List<DiningReview>> getPendingReviews() {
        return new ResponseEntity<List<DiningReview>>(this.diningReviewRepository.findByStatus(false), HttpStatus.OK);
    }

}

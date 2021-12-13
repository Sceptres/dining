package com.aaa.dining.controllers;

import com.aaa.dining.repositories.DiningReviewRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/admin")
public class AdminController {

    private final DiningReviewRepository diningReviewRepository;

    public AdminController(final DiningReviewRepository diningReviewRepository) {
        this.diningReviewRepository = diningReviewRepository;
    }

}

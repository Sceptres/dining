package com.aaa.dining.repositories;

import com.aaa.dining.entities.DiningReview;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiningReviewRepository extends CrudRepository<DiningReview, Integer> {
    List<DiningReview> findByRestaurantIdAndStatus(Long restaurantId, boolean status);
}

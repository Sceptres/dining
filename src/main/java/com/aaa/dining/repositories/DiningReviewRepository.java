package com.aaa.dining.repositories;

import com.aaa.dining.entities.DiningReview;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DiningReviewRepository extends CrudRepository<DiningReview, Integer> {
    Optional<DiningReview> findById(Long id);
    boolean existsDiningReviewByNameAndRestaurantId(String name, Long restaurantId);
    List<DiningReview> findByRestaurantIdAndStatus(Long restaurantId, boolean status);
}

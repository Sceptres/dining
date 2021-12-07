package com.aaa.dining.repositories;

import com.aaa.dining.entities.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
    boolean existsRestaurantByNameAndZipcode(String name, String zipcode);

    @Query(value="SELECT r FROM Restaurant r WHERE r.zipcode=?1 AND r.peanut IS NOT NULL AND r.egg IS NOT NULL and r.dairy IS NOT NULL " +
                 "ORDER BY r.peanut DESC, r.egg DESC, r.dairy DESC")
    List<Restaurant> findByZipcodeAndHasReviewDesc(String zipcode);
}

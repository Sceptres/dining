package com.aaa.dining.repositories;

import com.aaa.dining.entities.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
    boolean existsRestaurantByNameAndZipcode(String name, String zipcode);
}

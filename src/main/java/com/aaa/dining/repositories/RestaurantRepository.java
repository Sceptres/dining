package com.aaa.dining.repositories;

import com.aaa.dining.entities.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
    Optional<Restaurant> findById(Long id);
    boolean existsRestaurantByNameAndZipcode(String name, String zipcode);
    List<Restaurant> findByZipcode(String zipcode);
    List<Restaurant> findByPeanutIsNotNullOrderByPeanutDesc();
    List<Restaurant> findByEggIsNotNullOrderByEggDesc();
    List<Restaurant> findByDairyIsNotNullOrderByDairyDesc();

    @Query(value="SELECT r FROM Restaurant r WHERE r.zipcode=?1 AND r.peanut IS NOT NULL" +
                 " ORDER BY r.peanut DESC")
    List<Restaurant> findByZipcodeAndPeanutDesc(String zipcode);

    @Query(value="SELECT r FROM Restaurant r WHERE r.zipcode=?1 AND r.egg IS NOT NULL" +
                 " ORDER BY r.egg DESC")
    List<Restaurant> findByZipcodeAndEggDesc(String zipcode);

    @Query(value="SELECT r FROM Restaurant r WHERE r.zipcode=?1 AND r.egg IS NOT NULL" +
                 " ORDER BY r.egg DESC")
    List<Restaurant> findByZipcodeAndDairyDesc(String zipcode);

    default List<Restaurant> findByZipcodeAndAllergyDesc(String zipcode, String allergy) {
        switch (allergy) {
            case "peanut":
                return this.findByZipcodeAndPeanutDesc(zipcode);
            case "egg":
                return this.findByZipcodeAndEggDesc(zipcode);
            case "dairy":
                return this.findByZipcodeAndDairyDesc(zipcode);
            default:
                return List.of();
        }
    }

    default List<Restaurant> findByAllergyDesc(String allergy) {
        switch (allergy) {
            case "peanut":
                return this.findByPeanutIsNotNullOrderByPeanutDesc();
            case "egg":
                return this.findByEggIsNotNullOrderByEggDesc();
            case "dairy":
                return this.findByDairyIsNotNullOrderByDairyDesc();
            default:
                return List.of();
        }
    }
}

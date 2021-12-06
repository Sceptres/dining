package com.aaa.dining.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="REVIEWS")
public class DiningReview {

    // Id
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    // The id of the restaurant
    @Getter
    @Setter
    @Column(name="RESTAURANT_ID")
    private Long restaurantId;

    // Name of user who submitted the review
    @Getter
    @Setter
    @Column(name="NAME")
    private String name;

    // Optional allergy scores
    // Peanut score
    @Getter
    @Setter
    @Column(name="PEANUT")
    private Float peanutScore;

    // Egg score
    @Getter
    @Setter
    @Column(name="EGG")
    private Float eggScore;

    // Dairy score
    @Getter
    @Setter
    @Column(name="DAIRY")
    private Float dairyScore;

    // Optional review comment
    @Getter
    @Setter
    @Column(name="COMMENT")
    private String comment;
}

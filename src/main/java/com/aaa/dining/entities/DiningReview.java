package com.aaa.dining.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.DecimalFormat;

@Entity
@Table(name="REVIEWS")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class DiningReview {

    private static final DecimalFormat df = new DecimalFormat("###.##");

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
    @Column(name="PEANUT")
    private Float peanutScore;

    // Egg score
    @Getter
    @Column(name="EGG")
    private Float eggScore;

    // Dairy score
    @Getter
    @Column(name="DAIRY")
    private Float dairyScore;

    // Optional review comment
    @Getter
    @Setter
    @Column(name="COMMENT")
    private String comment = "";

    @Getter
    @Setter
    @Column(name="STATUS")
    private Boolean status;

    public void setPeanutScore(Float peanutScore) {
        this.peanutScore = Float.valueOf(DiningReview.df.format(peanutScore));
    }

    public void setEggScore(Float eggScore) {
        this.eggScore = Float.valueOf(DiningReview.df.format(eggScore));
    }

    public void setDairyScore(Float dairyScore) {
        this.dairyScore = Float.valueOf(DiningReview.df.format(dairyScore));;
    }
}

package com.aaa.dining.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name="RESTAURANTS")
public class Restaurant {

    // Id
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    // Restaurant name column
    @Getter
    @Setter
    @Column(name="NAME")
    private String name;

    // Allergy rating fields
    // Peanut allergy rating
    @Getter
    @Setter
    @Column(name="PEANUT")
    private Float peanut;

    // Egg allergy rating
    @Getter
    @Setter
    @Column(name="EGG")
    private Float egg;

    //Dairy allergy rating
    @Getter
    @Setter
    @Column(name="DAIRY")
    private Float dairy;
}

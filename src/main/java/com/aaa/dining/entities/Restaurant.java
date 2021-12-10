package com.aaa.dining.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name="RESTAURANTS")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
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

    // The restaurants city
    @Getter
    @Setter
    @Column(name="CITY")
    private String city;

    // The restaurants state
    @Getter
    @Setter
    @Column(name="STATE")
    private String state;

    // The restaurants zipcode
    @Getter
    @Setter
    @Column(name="ZIPCODE")
    private String zipcode;

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

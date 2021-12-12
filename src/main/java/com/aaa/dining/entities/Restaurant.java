package com.aaa.dining.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.DecimalFormat;


@Entity
@Table(name="RESTAURANTS")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class Restaurant {

    private final static DecimalFormat df = new DecimalFormat("###.##");

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
    @Column(name="PEANUT")
    private Float peanut;

    // Egg allergy rating
    @Getter
    @Column(name="EGG")
    private Float egg;

    //Dairy allergy rating
    @Getter
    @Column(name="DAIRY")
    private Float dairy;

    public void setPeanut(Float peanut) {
        this.peanut = Float.valueOf(Restaurant.df.format(peanut));
    }

    public void setEgg(Float egg) {
        this.egg = Float.valueOf(Restaurant.df.format(egg));
    }

    public void setDairy(Float dairy) {
        this.dairy = Float.valueOf(Restaurant.df.format(dairy));
    }
}

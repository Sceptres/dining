package com.aaa.dining.entities;

import javax.persistence.*;


@Entity
@Table(name="RESTAURANTS")
public class Restaurant {

    // Id
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    // Restaurant name column
    @Column(name="NAME")
    String name;

    // Allergy rating fields
    // Peanut allergy rating
    @Column(name="PEANUT")
    Float peanut;

    // Egg allergy rating
    @Column(name="EGG")
    Float egg;

    //Dairy allergy rating
    @Column(name="DAIRY")
    Float dairy;

    // Instance field getter and setter methods

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPeanut() {
        return this.peanut;
    }

    public void setPeanut(Float peanut) {
        this.peanut = peanut;
    }

    public Float getEgg() {
        return this.egg;
    }

    public void setEgg(Float egg) {
        this.egg = egg;
    }

    public Float getDairy() {
        return this.dairy;
    }

    public void setDairy(Float dairy) {
        this.dairy = dairy;
    }
}

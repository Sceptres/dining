package com.aaa.dining.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="USER")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    // Id
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    // The name of the user
    @Getter
    @Setter
    @Column(name="NAME")
    private String name;

    // The users city
    @Getter
    @Setter
    @Column(name="CITY")
    private String city;

    // The users state
    @Getter
    @Setter
    @Column(name="STATE")
    private String state;

    // The users zipcode
    @Getter
    @Setter
    @Column(name="ZIPCODE")
    private String zipcode;

    // Is the user interested in peanut allergies
    @Getter
    @Setter
    @Column(name="PEANUT")
    private Boolean isPeanutAllergic;

    // Is the user interested in egg allergies
    @Getter
    @Setter
    @Column(name="EGG")
    private Boolean isEggAllergic;

    // Is the user interested in dairy allergies
    @Getter
    @Setter
    @Column(name="DAIRY")
    private Boolean isDairyAllergic;

}

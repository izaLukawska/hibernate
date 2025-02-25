package org.lukawska.course.hibernate.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;

    private String city;

    protected Address(){}

    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }
}

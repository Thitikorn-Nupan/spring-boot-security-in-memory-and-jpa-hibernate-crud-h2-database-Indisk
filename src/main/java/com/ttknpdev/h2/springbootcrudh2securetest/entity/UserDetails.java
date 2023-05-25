package com.ttknpdev.h2.springbootcrudh2securetest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@ToString

@Entity
@Table(name = UserDetails.TABLE)
public class UserDetails {

    protected final static String TABLE = "details";
    /* define fields on database */
    @Id
    private Long id;
    /* don't use "order" on field primary key because it'll access fails */
    private String fullname;
    private Float weight;
    private Float height;
    private String city;
    @Setter(AccessLevel.PRIVATE)
    private String date;

    public UserDetails(String fullname, Float weight, Float height, String city) {
        this.fullname = fullname;
        this.weight = weight;
        this.height = height;
        this.city = city;
        this.date = String.valueOf(LocalDate.now());
    }

    public UserDetails() {
        date = String.valueOf(LocalDate.now());
    }
}

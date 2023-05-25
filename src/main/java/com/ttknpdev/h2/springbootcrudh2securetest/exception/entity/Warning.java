package com.ttknpdev.h2.springbootcrudh2securetest.exception.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class Warning {
    private Short status;
    private String message;
    private String cause;
    @Setter(AccessLevel.PRIVATE)
    private String date;

    public Warning(Short status,String message, String cause) {
        this.status = status;
        this.message = message;
        this.cause = cause;
        date = String.valueOf(LocalDate.now());
    }

    public Warning() {
        date = String.valueOf(LocalDate.now());
    }
}

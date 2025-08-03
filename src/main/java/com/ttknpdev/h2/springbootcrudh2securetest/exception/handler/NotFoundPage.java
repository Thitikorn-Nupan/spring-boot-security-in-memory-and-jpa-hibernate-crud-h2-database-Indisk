package com.ttknpdev.h2.springbootcrudh2securetest.exception.handler;

public class NotFoundPage extends RuntimeException {
    private final String causeCurrent;

    public NotFoundPage(String causeCurrent) {
        super(causeCurrent);
        this.causeCurrent = causeCurrent;
    }

    public String getCauseCurrent() {
        return causeCurrent;
    }
}

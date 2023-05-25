package com.ttknpdev.h2.springbootcrudh2securetest.exception.handler;

public class NotAllowedMethod extends RuntimeException {
    private String causeCurrent;
    public NotAllowedMethod(String causeCurrent) {
        super(causeCurrent);
        this.causeCurrent = causeCurrent;
    }
    public String getCauseCurrent() {
        return causeCurrent;
    }
}

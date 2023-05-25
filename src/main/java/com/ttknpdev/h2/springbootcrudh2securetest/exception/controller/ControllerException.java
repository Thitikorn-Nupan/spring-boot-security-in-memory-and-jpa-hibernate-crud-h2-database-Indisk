package com.ttknpdev.h2.springbootcrudh2securetest.exception.controller;

import com.ttknpdev.h2.springbootcrudh2securetest.exception.entity.Warning;
import com.ttknpdev.h2.springbootcrudh2securetest.exception.handler.NotAllowedMethod;
import com.ttknpdev.h2.springbootcrudh2securetest.exception.handler.NotFoundPage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice /* @ControllerAdvice is an annotation, to handle the exceptions globally. */
public class ControllerException {
    @ExceptionHandler(value = NotFoundPage.class)
    public ResponseEntity<Warning> getNotFound(NotFoundPage notFoundPage) {
        Warning warning = new Warning();
        warning.setStatus((short) HttpStatus.NOT_FOUND.value());
        warning.setCause(notFoundPage.getCauseCurrent());
        warning.setMessage("controller from ControllerException class");
        ResponseEntity<Warning> response = new ResponseEntity<>(warning,HttpStatus.NOT_FOUND);
        return response;
    }

    @ExceptionHandler(value = NotAllowedMethod.class)
    public ResponseEntity<Warning> getNotAllowedMethod(NotAllowedMethod notAllowedMethod) {
        Warning warning = new Warning();
        warning.setStatus((short) HttpStatus.NOT_ACCEPTABLE.value());
        warning.setCause(notAllowedMethod.getCauseCurrent());
        warning.setMessage("controller from ControllerException class");
        ResponseEntity<Warning> response = new ResponseEntity<>(warning,HttpStatus.NOT_ACCEPTABLE);
        return response;
    }

}

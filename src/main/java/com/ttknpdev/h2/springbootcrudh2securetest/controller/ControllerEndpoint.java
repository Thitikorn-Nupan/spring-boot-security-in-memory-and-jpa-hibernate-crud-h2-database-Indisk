package com.ttknpdev.h2.springbootcrudh2securetest.controller;

import com.ttknpdev.h2.springbootcrudh2securetest.entity.UserDetails;
import com.ttknpdev.h2.springbootcrudh2securetest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/indisk")
public class ControllerEndpoint {

    private UserService service;
    @Autowired
    public ControllerEndpoint(UserService service) {
        this.service = service;
    }
    @PostMapping(value = "/create")
    private ResponseEntity create(@RequestBody UserDetails userDetails) {
        return ResponseEntity
                .accepted()
                .body( (UserDetails) service.create(userDetails) );
    }
    @PostMapping(value = "/creates")
    private ResponseEntity creates(@RequestBody List<UserDetails> userDetails) {
        return ResponseEntity
                .accepted()
                .body(service.creates(userDetails) );
    }

    @GetMapping(value = "/reads")
    private ResponseEntity reads() {
        return ResponseEntity
                .accepted()
                .body(service.reads());
    }

    @GetMapping(value = "/read/{id}")
    private ResponseEntity<UserDetails> read(@PathVariable Long id) {
        return ResponseEntity
                .accepted()
                .body( (UserDetails) service.read(id));
    }

    @DeleteMapping(value = "/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(service.delete(id));
    }
    @PutMapping(value = "/update")
    private ResponseEntity update(@RequestBody UserDetails userDetails) {
        return ResponseEntity
                .ok()
                .body(service.update(userDetails));
    }




}

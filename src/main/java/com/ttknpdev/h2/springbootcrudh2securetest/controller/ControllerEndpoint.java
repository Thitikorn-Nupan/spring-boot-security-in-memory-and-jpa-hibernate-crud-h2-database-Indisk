package com.ttknpdev.h2.springbootcrudh2securetest.controller;

import com.ttknpdev.h2.springbootcrudh2securetest.entity.UserDetails;
import com.ttknpdev.h2.springbootcrudh2securetest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/user")
public class ControllerEndpoint {

    private final UserService<UserDetails> service;

    @Autowired
    public ControllerEndpoint(UserService<UserDetails> service) {
        this.service = service;
    }

    @GetMapping(value = "/creates/test")
    private ResponseEntity createTest() {
        return ResponseEntity
                .accepted()
                // String fullname, Float weight, Float height, String city
                .body(service.creates(List.of(
                        new UserDetails(1L,"Alex Ryder",56.5F,165.5F,"Bangkok"),
                        new UserDetails(2L,"Alun Slider",56.5F,165.5F,"Bangkok"),
                        new UserDetails(3L,"Jacky Chain",56.5F,165.5F,"Bangkok")
                        )));
    }

    // The generic type can be empty
    @PostMapping(value = "/create")
    private ResponseEntity create(@RequestBody UserDetails userDetails) {
        return ResponseEntity
                .accepted()
                .body(service.create(userDetails));
    }

    @PostMapping(value = "/creates")
    private ResponseEntity<List<UserDetails>> creates(@RequestBody List<UserDetails> userDetails) {
        return ResponseEntity
                .accepted()
                .body(service.creates(userDetails) );
    }

    @GetMapping(value = "/reads")
    private ResponseEntity<List<UserDetails>> reads() {
        return ResponseEntity
                .ok()
                .body(service.reads());
    }

    @GetMapping(value = "/read/{id}")
    private ResponseEntity<UserDetails> read(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(service.read(id));
    }

    @DeleteMapping(value = "/delete/{id}")
    private ResponseEntity<Map<String,UserDetails>> delete(@PathVariable Long id) {
        return ResponseEntity
                .accepted()
                .body(service.delete(id));
    }

    @PutMapping(value = "/update")
    private ResponseEntity<Map<String,UserDetails>> update(@RequestBody UserDetails userDetails) {
        return ResponseEntity
                .accepted()
                .body(service.update(userDetails));
    }


}

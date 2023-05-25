package com.ttknpdev.h2.springbootcrudh2securetest.access_repository;


import com.ttknpdev.h2.springbootcrudh2securetest.entity.UserDetails;
import com.ttknpdev.h2.springbootcrudh2securetest.exception.handler.NotAllowedMethod;
import com.ttknpdev.h2.springbootcrudh2securetest.exception.handler.NotFoundPage;
import com.ttknpdev.h2.springbootcrudh2securetest.repository.UserRepositories;
import com.ttknpdev.h2.springbootcrudh2securetest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccessRepositories implements UserService<UserDetails> {
    
    private UserRepositories repositories;
    @Autowired
    public AccessRepositories(UserRepositories repositories) {
        this.repositories = repositories;
    }
    
    private UserDetails validateBeforeQuery(UserDetails userDetails) {
        if (!(userDetails.getFullname().trim().isEmpty()) 
                && !(userDetails.getCity().trim().isEmpty()) 
                && (userDetails.getId() != 0)
                && (userDetails.getWeight() != 0) 
                && (userDetails.getHeight() != 0)) {
            return userDetails;
        }
        return new UserDetails();
    }
    
    @Override
    public UserDetails create(UserDetails obj) {
        UserDetails newUser = null;
        if (validateBeforeQuery(obj).getId() != null) {
            newUser = repositories.save(obj);
        }
        else {
            throw new NotAllowedMethod("maybe , some fields it was null");
        }
        return newUser;
    }

    @Override
    public List<UserDetails> creates(List<UserDetails> listObj) {
        List<UserDetails> userDetailsListNew = new ArrayList<>();
        if (listObj.size() > 0) {
            for (int i = 0 ; i<= listObj.size()-1 ; i++) {

                if (validateBeforeQuery(listObj.get(i)).getId() != null) {
                    userDetailsListNew.add(listObj.get(i));
                }
                else {

                    throw new NotAllowedMethod("found some fields it was null");
                    /* break; it means leave method (No clear any order)*/
                }

            }

            userDetailsListNew = repositories.saveAll(userDetailsListNew);
        }
        return userDetailsListNew;
    }

    @Override
    public List<UserDetails> reads() {
        return (List<UserDetails>) repositories.findAll();
    }

    @Override
    public UserDetails read(Long id) {
        UserDetails found = new UserDetails();
        repositories.findById(id)
                .ifPresent(user-> {
                    found.setId(user.getId());
                    found.setCity(user.getCity());
                    found.setWeight(user.getWeight());
                    found.setHeight(user.getHeight());
                    found.setFullname(user.getFullname());
                });
        return found;
    }

    @Override
    public Map<String, UserDetails> update(UserDetails obj) {
        Map<String,UserDetails> response = new HashMap<>();
        if (repositories.findById(obj.getId()).isPresent()) {

            repositories.findById(obj.getId())
                    .ifPresent((user) -> {
                        user.setId(obj.getId());
                        user.setCity(obj.getCity());
                        user.setWeight(obj.getWeight());
                        user.setHeight(obj.getHeight());
                        user.setFullname(obj.getFullname());
                        repositories.save(user);
                        response.put("updated",user);
                    });
        }
        else {
            throw new NotFoundPage("maybe , id isn't correct");
        }
        return response;
    }

    @Override
    public Map<String, UserDetails> delete(Long id) {
        Map<String,UserDetails> response = new HashMap<>();
        repositories.findById(id)
                .ifPresent(user-> {
                    repositories.delete(user);
                    response.put("deleted",user);
                });
        return response;
    }
}

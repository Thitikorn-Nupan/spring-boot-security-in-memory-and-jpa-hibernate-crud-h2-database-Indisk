package com.ttknpdev.h2.springbootcrudh2securetest.access_repository;

import com.ttknpdev.h2.springbootcrudh2securetest.entity.UserDetails;
import com.ttknpdev.h2.springbootcrudh2securetest.exception.handler.NotAllowedMethod;
import com.ttknpdev.h2.springbootcrudh2securetest.exception.handler.NotFoundPage;
import com.ttknpdev.h2.springbootcrudh2securetest.repository.UserRepository;
import com.ttknpdev.h2.springbootcrudh2securetest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserDTO implements UserService<UserDetails> {
    
    private final UserRepository userRepository;

    @Autowired
    public UserDTO(UserRepository userRepository) {
        this.userRepository = userRepository;
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
            newUser = userRepository.save(obj);
        }
        else {
            throw new NotAllowedMethod("found some fields it's null");
        }
        return newUser;
    }

    @Override
    public List<UserDetails> creates(List<UserDetails> listObj) {
        List<UserDetails> userDetailsListNew = new ArrayList<>();
        if (!listObj.isEmpty()) {
            for (int i = 0 ; i <= (listObj.size()-1) ; i++) {
                if (validateBeforeQuery(listObj.get(i)).getId() != null) {
                    userDetailsListNew.add(listObj.get(i));
                }
                else {
                    throw new NotAllowedMethod("found some fields it's null");
                    // break; it means leave method (No clear any order)
                }
            }
            userDetailsListNew = userRepository.saveAll(userDetailsListNew);
        }
        return userDetailsListNew;
    }

    @Override
    public List<UserDetails> reads() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails read(Long id) {
        /*
        // UserDetails found = new UserDetails();
        userRepository.findById(id)
                .ifPresent(user-> {
                    found.setId(user.getId());
                    found.setCity(user.getCity());
                    found.setWeight(user.getWeight());
                    found.setHeight(user.getHeight());
                    found.setFullname(user.getFullname());
                });
        */
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Map<String, UserDetails> update(UserDetails obj) {
        Map<String,UserDetails> response = new HashMap<>();
        Optional<UserDetails> userDetails = userRepository.findById(obj.getId());
        if (userDetails.isPresent()) { // if exist
            userDetails.ifPresent((user) -> {
                        user.setId(obj.getId());
                        user.setCity(obj.getCity());
                        user.setWeight(obj.getWeight());
                        user.setHeight(obj.getHeight());
                        user.setFullname(obj.getFullname());
                        userRepository.save(user);
                        response.put("updated",user);
                    });
        }
        else {
            throw new NotFoundPage("The primary key isn't existed");
        }
        return response;
    }

    @Override
    public Map<String, UserDetails> delete(Long id) {
        Map<String,UserDetails> response = new HashMap<>();
        Optional<UserDetails> userDetails = userRepository.findById(id);
        if (userDetails.isPresent()) {  // if exist
            userDetails.ifPresent(user-> {
                        userRepository.delete(user);
                        response.put("deleted",user);
                    });
        } else {
            throw new NotFoundPage("The primary key isn't existed");
        }
        return response;
    }
}

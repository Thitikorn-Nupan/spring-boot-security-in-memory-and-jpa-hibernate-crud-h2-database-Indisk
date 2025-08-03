package com.ttknpdev.h2.springbootcrudh2securetest.repository;

import com.ttknpdev.h2.springbootcrudh2securetest.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserDetails,Long> { }

package com.ttknpdev.h2.springbootcrudh2securetest.secure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails; /* ** */
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfigSecure {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() { // method for create user & roles (in memory)
        UserDetails mrAAA = User
                .withUsername("mr.a")
                .password("{noop}12345")
                .roles("NORMAL")
                .build();
        UserDetails mrBBB = User
                .withUsername("mr.b")
                .password("{noop}12345")
                .roles("EMPLOYEE","NORMAL")
                .build();
        UserDetails mrCCC = User
                .withUsername("mr.c")
                .password("{noop}12345")
                .roles("ADMIN")
                .build();
        UserDetails mrDDD = User
                .withUsername("mr.d")
                .password("{noop}12345")
                .roles("ADMIN","NORMAL","EMPLOYEE")
                .build();
        return new InMemoryUserDetailsManager(mrAAA,mrBBB,mrCCC,mrDDD);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // create accessing of roles
        httpSecurity
                .authorizeHttpRequests()
                .requestMatchers("/api/user/reads").hasAnyRole("EMPLOYEE","ADMIN")
                .requestMatchers("/api/user/read/**").hasAnyRole("EMPLOYEE","NORMAL")
                // .and().authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST,"/api/user/create").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/api/user/creates").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/user/delete/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/user/update").hasRole("ADMIN")
                .anyRequest().authenticated();
        httpSecurity.httpBasic();
        httpSecurity.csrf().disable();
        /*
        when need to use console h2 specify this httpSecurity.headers().frameOptions().disable(); it'll work
        cause : i try to access with user / pass on my memory it can into login page but page doesn't load
        */
        httpSecurity.headers().frameOptions().disable();
        return httpSecurity.build();
    }
}

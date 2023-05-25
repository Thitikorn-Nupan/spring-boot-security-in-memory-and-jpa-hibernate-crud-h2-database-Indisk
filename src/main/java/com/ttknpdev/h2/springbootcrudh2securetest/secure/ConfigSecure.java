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
    public InMemoryUserDetailsManager userDetailsManager() {
        /* method for create roles (in memory) */
        UserDetails mrAAA = User
                .withUsername("mr.aaa")
                .password("{noop}12345")
                .roles("NORMAL")
                .build();
        UserDetails mrBBB = User
                .withUsername("mr.bbb")
                .password("{noop}12345")
                .roles("EMPLOYEE","NORMAL")
                .build();
        UserDetails mrCCC = User
                .withUsername("mr.ccc")
                .password("{noop}12345")
                .roles("ADMIN")
                .build();
        UserDetails mrDDD = User
                .withUsername("mr.ddd")
                .password("{noop}12345")
                .roles("ADMIN","NORMAL","EMPLOYEE")
                .build();
        return new InMemoryUserDetailsManager(mrAAA,mrBBB,mrCCC,mrDDD);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        /* create accessing of roles */
        httpSecurity

                .authorizeHttpRequests()
                .requestMatchers("/api/indisk/reads").hasAnyRole("EMPLOYEE","ADMIN")
                .requestMatchers("/api/indisk/read/**").hasAnyRole("EMPLOYEE","NORMAL")
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST,"/api/indisk/create").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/api/indisk/creates").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/indisk/delete/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/indisk/update").hasRole("ADMIN")
                .anyRequest().authenticated();

        httpSecurity.httpBasic();
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable(); /* when need to use console h2 specify this line it works
        , because i try to access with user / pass on my memory it can into login page but page doesn't load */

        return httpSecurity.build();
    }
}

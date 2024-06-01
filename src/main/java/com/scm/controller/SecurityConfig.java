package com.scm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.scm.services.impl.SecurityCustomUserDetailsService;

@Configuration
public class SecurityConfig { 

    //User Create and login using java code with in memory service
    // @Bean
    // public UserDetailsService userDetailsService(){

    //   UserDetails user = User.withDefaultPasswordEncoder().username("admin123").password("1234").roles("ADMIN","USER").build();

    //   var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user);
    //   return inMemoryUserDetailsManager;
    
    // }

    @Autowired
    private SecurityCustomUserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider(){
     DaoAuthenticationProvider daoAuthenticationProvider =  new DaoAuthenticationProvider();
     
     daoAuthenticationProvider.setUserDetailsService(userDetailsService);
     daoAuthenticationProvider.setPasswordEncoder(passwordEncoder()); 
     return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
    }

}

package com.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.scm.entities.User;

public interface UserRepository extends JpaRepository<User,String> {


  //Custom Query Method
  Optional<User> findByEmail(String email);
} 

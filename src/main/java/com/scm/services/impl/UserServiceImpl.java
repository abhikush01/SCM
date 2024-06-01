package com.scm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.helper.AppConstants;
import com.scm.helper.ResourceNotFoundEXception;
import com.scm.repositories.UserRepository;
import com.scm.services.UserService;

@Service
public class UserServiceImpl implements UserService{


  @Autowired
  private UserRepository userRepo;
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private PasswordEncoder passwordEncoder;


  @Override
  public User saveUser(User user) {
    String userId = UUID.randomUUID().toString();
    user.setUserId(userId);
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    //Set User Role

    user.setRoleList(List.of(AppConstants.ROLE_USER));


    logger.info(user.getProvider().toString());
   return userRepo.save(user);
  }

  @Override
  public Optional<User> getUserById(String id) {
   return userRepo.findById(id);
   
  }

  @Override
  public Optional<User> updateUser(User user) {
    User userData = userRepo.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundEXception("User Not Found"));
    userData.setName(user.getName());
    userData.setEmail(user.getEmail());
    userData.setPassword(user.getPassword());
    userData.setAbout(user.getAbout());
    userData.setPhoneNumber(user.getPhoneNumber());
    userData.setProfilePic(user.getProfilePic());
    userData.setEnabled(user.isEnabled());
    userData.setEmailVerified(user.isEmailVerified());
    userData.setPhoneVerified(user.isPhoneVerified());
    userData.setProvider(user.getProvider());
    userData.setProviderUserId(user.getProviderUserId());

    User newUser= userRepo.save(userData);

    return Optional.ofNullable(newUser);
  }

  @Override
  public void deleteUser(String id) {
    User curr = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundEXception("User Not Found"));
    userRepo.delete(curr);

  }

  @Override
  public boolean isUserExist(String userId) {
    User curr = userRepo.findById(userId).orElse(null);
    return curr != null;
  }

  @Override
  public boolean isUserExistByEmail(String email) {
    User curr = userRepo.findByEmail(email).orElse(null);
    return curr != null;
  }

  @Override
  public List<User> getAllUsers() {
   return userRepo.findAll();
  }


}

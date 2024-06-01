package com.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class PageController {
    @Autowired
    private UserService userService;
  
  @GetMapping("/home")
  public String getMethodName() {
      return "home";
  }

  @GetMapping("/about")
  public String getAbout() {
      return "about";
  }
  

  @GetMapping("/services")
  public String getServices() {
      return "service";
  }

  @GetMapping("/contact")
  public String getContact() {
      return "contact";
  }

  @GetMapping("/login")
  public String getLogin() {
      return "login";
  }

  @GetMapping("/signup")
  public String getSignup(Model model) {
    UserForm userForm = new UserForm();
   
    model.addAttribute("userForm",userForm);
      return "signup";
  }


  //Processing

  @PostMapping("/do-register")
  public String doRegister(@Valid @ModelAttribute UserForm userForm,BindingResult  rBindingResult, HttpSession session) {
    
    // User user = User.builder().name(userForm.getName()).email(userForm.getEmail()).about(userForm.getAbout()).phoneNumber(userForm.getPhoneNumber()).password(userForm.getPassword()).build();

    if(rBindingResult.hasErrors()){
      return "signup";
    }

    User user = new User();
    user.setAbout(userForm.getAbout());
    user.setName(userForm.getName());
    user.setEmail(userForm.getEmail());
    user.setPassword(userForm.getPassword());
    user.setPhoneNumber(userForm.getPhoneNumber());
    
    
    User userData = userService.saveUser(user);

    Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

    session.setAttribute("message",message);
 
     
    return "redirect:/signup";
  }
  
}

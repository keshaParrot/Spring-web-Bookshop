package com.DenysiukProg.spring6webapp.controllers;

import com.DenysiukProg.spring6webapp.domain.entity.UserEntity;
import com.DenysiukProg.spring6webapp.domain.dto.RegistrationDto;
import com.DenysiukProg.spring6webapp.repositories.UserRepository;
import com.DenysiukProg.spring6webapp.services.Interfaces.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller responsible for handling user authentication and registration.
 * It provides endpoints for login and registration forms, validates user input,
 * and manages the creation of new user accounts in the system.
 */
@Controller
@AllArgsConstructor
public class AuthentificationController {
    private final UserService userService;
    private final UserRepository userRepository;

    /**
     * Display the login page.
     *
     * @return the name of the login view template
     */
    @GetMapping("/login")
    public String loginPage(){
        return "user-login";
    }

    /**
     * Display the registration form.
     *
     * @return the name of the registration view template
     */
    @GetMapping("/register")
    public String getRegisterForm(Model model){
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "user-register";
    }
    /**
     * Process the user registration.
     *
     * @param user the registration data transfer object containing user input
     * @param result contains any binding or validation errors
     * @return a redirection string to the appropriate URL based on outcome
     */
    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user")RegistrationDto user,
                           BindingResult result, Model model) {

        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            return "redirect:/register?fail";
        }

        UserEntity existingUserUsername = userService.findByUsername(user.getUsername());
        if(existingUserUsername != null && existingUserUsername.getUsername() != null && !existingUserUsername.getUsername().isEmpty()) {
            return "redirect:/register?fail";
        }

        if(result.hasErrors()) {
            model.addAttribute("user", user);
            return "user-register";
        }

        userService.saveUser(user);
        return "redirect:/personalAccount?success";
    }



}

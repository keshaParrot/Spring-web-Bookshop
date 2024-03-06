package com.DenysiukProg.spring6webapp.controllers;

import com.DenysiukProg.spring6webapp.dto.RegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/personalAccount")
    public String getAccountInformation(Model model){
        return "personalAccount";
    }
}

package com.DenysiukProg.spring6webapp.services;

import com.DenysiukProg.spring6webapp.dto.RegistrationDto;
import jakarta.servlet.Registration;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
}

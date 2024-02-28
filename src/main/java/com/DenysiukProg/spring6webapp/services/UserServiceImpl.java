package com.DenysiukProg.spring6webapp.services;

import com.DenysiukProg.spring6webapp.domain.Role;
import com.DenysiukProg.spring6webapp.domain.User;
import com.DenysiukProg.spring6webapp.dto.RegistrationDto;
import com.DenysiukProg.spring6webapp.repositories.RoleRepository;
import com.DenysiukProg.spring6webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.expression.Sets;

import java.util.Arrays;

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(registrationDto.getPassword());
        Role role = roleRepository.findByName("USER");
        user.setRoles(role);
        userRepository.save(user);
    }

}

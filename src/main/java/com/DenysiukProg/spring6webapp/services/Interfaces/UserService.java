package com.DenysiukProg.spring6webapp.services.Interfaces;

import com.DenysiukProg.spring6webapp.domain.Book;
import com.DenysiukProg.spring6webapp.domain.UserEntity;
import com.DenysiukProg.spring6webapp.dto.RegistrationDto;

import java.util.List;


public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);

    String count();

    List<Book> findUserBook(Long userId);
}

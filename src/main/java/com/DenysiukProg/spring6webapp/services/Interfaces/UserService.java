package com.DenysiukProg.spring6webapp.services.Interfaces;

import com.DenysiukProg.spring6webapp.domain.Book;
import com.DenysiukProg.spring6webapp.domain.UserEntity;
import com.DenysiukProg.spring6webapp.dto.BookDto;
import com.DenysiukProg.spring6webapp.dto.RegistrationDto;
import com.DenysiukProg.spring6webapp.dto.UserDto;

import java.util.List;


public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    void updateUserData(UserDto userDto);

    List<String> getAllUsernames();

    List<String> getAllEmails();

    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);

    List<UserEntity> findUsersByUsernameContaining(String username);

    String count();
    List<Book> findUserBook(Long userId);


}

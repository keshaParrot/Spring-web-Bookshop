package com.DenysiukProg.spring6webapp.services;

import com.DenysiukProg.spring6webapp.domain.Book;
import com.DenysiukProg.spring6webapp.domain.Role;
import com.DenysiukProg.spring6webapp.domain.UserEntity;
import com.DenysiukProg.spring6webapp.dto.RegistrationDto;
import com.DenysiukProg.spring6webapp.dto.UserDto;
import com.DenysiukProg.spring6webapp.repositories.RoleRepository;
import com.DenysiukProg.spring6webapp.repositories.UserRepository;
import com.DenysiukProg.spring6webapp.services.Interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registrationDto.getUsername());
        userEntity.setEmail(registrationDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role role = roleRepository.findByName("USER");
        userEntity.setRoles(role);
        userRepository.save(userEntity);
        System.out.println("\nuser saved\n");
    }
    @Override
    public UserEntity findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public String count(){
        return String.valueOf(userRepository.count());
    }
    @Override
    public List<Book> findUserBook(Long userId) {
        return userRepository.getReferenceById(userId).getBooks();
    }

    public static UserDto fromUserEntity(UserEntity userEntity) {
        UserDto dto = new UserDto();
        dto.setId(userEntity.getId());
        dto.setUsername(userEntity.getUsername());
        dto.setFirstName(userEntity.getFirstName());
        dto.setLastName(userEntity.getLastName());
        dto.setEmail(userEntity.getEmail());

        dto.setRoles(userEntity.getRoles());
        dto.setReviews(userEntity.getReviews());
        dto.setBooks(userEntity.getBooks());

        return dto;
    }
}

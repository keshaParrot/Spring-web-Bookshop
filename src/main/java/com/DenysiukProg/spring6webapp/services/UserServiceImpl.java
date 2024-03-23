package com.DenysiukProg.spring6webapp.services;

import com.DenysiukProg.spring6webapp.domain.*;
import com.DenysiukProg.spring6webapp.dto.RegistrationDto;
import com.DenysiukProg.spring6webapp.dto.UserDto;
import com.DenysiukProg.spring6webapp.repositories.AuthorRepository;
import com.DenysiukProg.spring6webapp.repositories.RoleRepository;
import com.DenysiukProg.spring6webapp.repositories.UserRepository;
import com.DenysiukProg.spring6webapp.services.Interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, AuthorRepository authorRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorRepository = authorRepository;
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
        userEntity.addRole(role);
        userRepository.save(userEntity);
        System.out.println("\nuser saved\n");
    }
    @Override
    public void updateUserData(UserDto userDto) {

        for (Role role : userDto.getRoles()) {
            if (role.getName().equals("AUTHOR")) {
                Author author = new Author();
                author.setFirstName(userDto.getFirstName());
                author.setLastName(userDto.getLastName());
                authorRepository.save(author);
            }
            else {
                authorRepository.findById(userDto.getId()).ifPresent(author -> userRepository.deleteById(userDto.getId()));
            }
        }

        userRepository.save(dtoToEntity(userDto));

    }
    @Override
    public List<String> getAllUsernames(){
        return userRepository.getAllUsernames().stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }
    @Override
    public List<String> getAllEmails(){
        return userRepository.getAllEmails().stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
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
    public List<UserEntity> findUsersByUsernameContaining(String username) {
        return userRepository.findUsersByUsernameContaining(username);
    }
    @Override
    public String count(){
        return String.valueOf(userRepository.count());
    }
    @Override
    public List<Book> findUserBook(Long userId) {
        return userRepository.getReferenceById(userId).getBooks();
    }

    public static UserDto entityToDto(UserEntity userEntity) {
        UserDto dto = new UserDto();
        dto.setId(userEntity.getId());
        dto.setUsername(userEntity.getUsername());
        dto.setFirstName(userEntity.getFirstName());
        dto.setLastName(userEntity.getLastName());
        dto.setEmail(userEntity.getEmail());
        dto.setAddress(userEntity.getAddress());
        dto.setPhoneNumber(userEntity.getPhoneNumber());

        dto.setRoles(userEntity.getRoles());
        dto.setReviews(userEntity.getReviews());
        dto.setBooks(userEntity.getBooks());

        return dto;
    }
    public static UserEntity dtoToEntity(UserDto userDto){
            UserEntity user = new UserEntity();

            user.setId(userDto.getId());
            user.setUsername(userDto.getUsername());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setAddress(userDto.getAddress());
            user.setPhoneNumber(userDto.getPhoneNumber());

            user.setRoles(userDto.getRoles());
            user.setReviews(userDto.getReviews());
            user.setBooks(userDto.getBooks());

            return user;

    }
}

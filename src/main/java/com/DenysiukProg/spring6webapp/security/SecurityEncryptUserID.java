package com.DenysiukProg.spring6webapp.security;

import com.DenysiukProg.spring6webapp.domain.UserEntity;
import com.DenysiukProg.spring6webapp.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SecurityEncryptUserID {
    private Map<String, UserEntity> userEntityMap = new HashMap<>();
    private final UserRepository userRepository;

    public SecurityEncryptUserID(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public String getEncryptedUserId(String username){
        UserEntity user = userRepository.findByUsername(username);



        return "";
    }
    public UserEntity getUserEntityByEncryptedId(String EID){
        return userEntityMap.get(EID);
    }
}

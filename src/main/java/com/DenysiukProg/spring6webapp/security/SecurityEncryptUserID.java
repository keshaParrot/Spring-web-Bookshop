package com.DenysiukProg.spring6webapp.security;

import com.DenysiukProg.spring6webapp.domain.entity.Author;
import com.DenysiukProg.spring6webapp.domain.entity.Publisher;
import com.DenysiukProg.spring6webapp.domain.entity.UserEntity;
import com.DenysiukProg.spring6webapp.services.Interfaces.AuthorService;
import com.DenysiukProg.spring6webapp.services.Interfaces.PublisherService;
import com.DenysiukProg.spring6webapp.services.Interfaces.UserService;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;


@Component
public class SecurityEncryptUserID {
    private final Map<String, String> userEntityMap = new HashMap<>();
    private final Map<String, String> authorMap = new HashMap<>();
    private final Map<String, String> publisherMap = new HashMap<>();
    private final UserService userService;
    private final AuthorService authorService;
    private final PublisherService publisherService;

    public SecurityEncryptUserID(UserService userService, AuthorService authorService, PublisherService publisherService) {
        this.userService = userService;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }

    public String getEncryptedUserId(String username){
        UserEntity user = userService.findByUsername(username);
        String key = generateKey();

        if(userEntityMap.containsKey(username)) {
            return userEntityMap.get(username);
        }

        userEntityMap.put(user.getUsername(),key);
        System.out.println("user "+ username+" is saved to key map with key" + userEntityMap.get(username));
        return key;
    }
    public String getEncryptedAuthorId(String fullName){
        authorService.findAuthorsByFullName(fullName);
        String key = generateKey();

        if(authorMap.containsKey(fullName)){
            return authorMap.get(fullName);
        }

        authorMap.put(fullName, key);
        System.out.println("author "+ fullName+" is saved to key map with key" + authorMap.get(fullName));
        return key;
    }
    public String getEncryptedPublisherId(String publisherName){
        publisherService.findPublisherByPublisherName(publisherName);
        String key = generateKey();

        if(publisherMap.containsKey(publisherName)){
            return publisherMap.get(publisherName);
        }

        publisherMap.put(publisherName, key);
        System.out.println("Publisher "+ publisherName+" is saved to key map with key" + publisherMap.get(publisherName));
        return key;
    }
    private String generateKey(){
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(20);

        for (int i = 0; i < 20; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return sb.toString();
    }
    public UserEntity getUserEntityByEncryptedId(String EID){
        for (Map.Entry<String, String> entry : userEntityMap.entrySet()){
            if (EID.equals(entry.getValue())) {
                return userService.findByUsername(entry.getKey());
            }
        }
        return null;
    }
    public String getUserNameByEncryptedId(String EID){
        for (Map.Entry<String, String> entry : userEntityMap.entrySet()){
            if (EID.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
    public Author getAuthorByEncryptedId(String EID){
        for (Map.Entry<String, String> entry : authorMap.entrySet()){
            if (EID.equals(entry.getValue())) {
                return authorService.findAuthorsByFullName(entry.getKey());
            }
        }

        return null;
    }
    public String getAuthorFullNameByEncryptedId(String EID){
        for (Map.Entry<String, String> entry : authorMap.entrySet()){
            if (EID.equals(entry.getValue())) {
                return entry.getKey();
            }
        }

        return null;
    }
    public Publisher getPublisherByEncryptedId(String EID){
        for (Map.Entry<String, String> entry : publisherMap.entrySet()){
            if (EID.equals(entry.getValue())) {
                return publisherService.findPublisherByPublisherName(entry.getKey());
            }
        }

        return null;
    }
    public String getPublisherNameByEncryptedId(String EID){
        for (Map.Entry<String, String> entry : publisherMap.entrySet()){
            if (EID.equals(entry.getValue())) {
                return entry.getKey();
            }
        }

        return null;
    }
}

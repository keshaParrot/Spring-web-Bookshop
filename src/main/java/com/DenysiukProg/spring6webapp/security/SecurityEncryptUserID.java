package com.DenysiukProg.spring6webapp.security;

import com.DenysiukProg.spring6webapp.domain.entity.Author;
import com.DenysiukProg.spring6webapp.domain.entity.Publisher;
import com.DenysiukProg.spring6webapp.domain.entity.UserEntity;
import com.DenysiukProg.spring6webapp.services.Interfaces.AuthorService;
import com.DenysiukProg.spring6webapp.services.Interfaces.PublisherService;
import com.DenysiukProg.spring6webapp.services.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for encrypting and decrypting user, author, and publisher IDs.
 */
@Component
public class SecurityEncryptUserID {
    private final Map<String, String> userEntityMap = new HashMap<>();
    private final Map<String, String> authorMap = new HashMap<>();
    private final Map<String, String> publisherMap = new HashMap<>();
    private final UserService userService;
    private final AuthorService authorService;
    private final PublisherService publisherService;

    @Autowired
    public SecurityEncryptUserID(UserService userService, AuthorService authorService, PublisherService publisherService) {
        this.userService = userService;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }

    /**
     * Generates an encrypted ID for the given username.
     *
     * @param username The username for which to generate an encrypted ID.
     * @return The encrypted ID.
     */
    public String getEncryptedUserId(String username){
        UserEntity user = userService.findByUsername(username);
        String key = generateKey();

        if(userEntityMap.containsKey(username)) {
            return userEntityMap.get(username);
        }

        userEntityMap.put(user.getUsername(),key);
        return key;
    }
    /**
     * Generates an encrypted ID for the given author's full name.
     *
     * @param fullName The full name of the author for which to generate an encrypted ID.
     * @return The encrypted ID.
     */
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
    /**
     * Generates an encrypted ID for the given publisher's name.
     *
     * @param publisherName The name of the publisher for which to generate an encrypted ID.
     * @return The encrypted ID.
     */
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
    /**
     * Generates a random key for encryption.
     *
     * @return The generated key.
     */
    private String generateKey(){
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(20);

        for (int i = 0; i < 20; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return sb.toString();
    }
    /**
     * Retrieves the UserEntity object associated with the given encrypted user ID.
     *
     * @param EID The encrypted user ID for which to retrieve the UserEntity.
     * @return The UserEntity object associated with the encrypted user ID.
     */
    public UserEntity getUserEntityByEncryptedId(String EID){
        for (Map.Entry<String, String> entry : userEntityMap.entrySet()){
            if (EID.equals(entry.getValue())) {
                return userService.findByUsername(entry.getKey());
            }
        }
        return null;
    }
    /**
     * Retrieves the username associated with the given encrypted user ID.
     *
     * @param EID The encrypted user ID for which to retrieve the username.
     * @return The username associated with the encrypted user ID.
     */
    public String getUserNameByEncryptedId(String EID){
        for (Map.Entry<String, String> entry : userEntityMap.entrySet()){
            if (EID.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
    /**
     * Retrieves the Author object associated with the given encrypted author ID.
     *
     * @param EID The encrypted author ID for which to retrieve the Author object.
     * @return The Author object associated with the encrypted author ID.
     */
    public Author getAuthorByEncryptedId(String EID){
        for (Map.Entry<String, String> entry : authorMap.entrySet()){
            if (EID.equals(entry.getValue())) {
                return authorService.findAuthorsByFullName(entry.getKey());
            }
        }

        return null;
    }
    /**
     * Retrieves the full name of the author associated with the given encrypted author ID.
     *
     * @param EID The encrypted author ID for which to retrieve the full name.
     * @return The full name of the author associated with the encrypted author ID.
     */
    public String getAuthorFullNameByEncryptedId(String EID){
        for (Map.Entry<String, String> entry : authorMap.entrySet()){
            if (EID.equals(entry.getValue())) {
                return entry.getKey();
            }
        }

        return null;
    }
    /**
     * Retrieves the Publisher object associated with the given encrypted publisher ID.
     *
     * @param EID The encrypted publisher ID for which to retrieve the Publisher object.
     * @return The Publisher object associated with the encrypted publisher ID.
     */
    public Publisher getPublisherByEncryptedId(String EID){
        for (Map.Entry<String, String> entry : publisherMap.entrySet()){
            if (EID.equals(entry.getValue())) {
                return publisherService.findPublisherByPublisherName(entry.getKey());
            }
        }

        return null;
    }
    /**
     * Retrieves the name of the publisher associated with the given encrypted publisher ID.
     *
     * @param EID The encrypted publisher ID for which to retrieve the name.
     * @return The name of the publisher associated with the encrypted publisher ID.
     */
    public String getPublisherNameByEncryptedId(String EID){
        for (Map.Entry<String, String> entry : publisherMap.entrySet()){
            if (EID.equals(entry.getValue())) {
                return entry.getKey();
            }
        }

        return null;
    }
}

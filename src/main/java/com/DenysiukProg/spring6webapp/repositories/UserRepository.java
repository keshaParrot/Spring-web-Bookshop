package com.DenysiukProg.spring6webapp.repositories;

import com.DenysiukProg.spring6webapp.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

/**
 * Repository for working with Review table on database.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Finds a user by email.
     *
     * @param email The email of the user to find.
     * @return The user with the specified email.
     */
    UserEntity findByEmail(String email);

    /**
     * Finds a user by username.
     *
     * @param userName The username of the user to find.
     * @return The user with the specified username.
     */
    UserEntity findByUsername(String userName);

    /**
     * Finds the first user by username.
     *
     * @param username The username of the user to find.
     * @return The first user found with the specified username.
     */
    UserEntity findFirstByUsername(String username);

    /**
     * Retrieves all usernames.
     *
     * @return List of all usernames.
     */
    @Query("SELECT ue.username from UserEntity ue")
    List<String> getAllUsernames();

    /**
     * Retrieves all emails.
     *
     * @return List of all emails.
     */
    @Query("SELECT ue.email from UserEntity ue")
    List<String> getAllEmails();

    /**
     * Finds users whose username contains the specified string.
     *
     * @param username The string to search for in usernames.
     * @return List of users whose username contains the specified string.
     */
    @Query("SELECT u FROM UserEntity u WHERE LOWER(u.username) LIKE %:username%")
    List<UserEntity> findUsersByUsernameContaining(String username);
}

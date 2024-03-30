package com.DenysiukProg.spring6webapp.repositories;

import com.DenysiukProg.spring6webapp.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String userName);
    UserEntity findFirstByUsername(String username);
    @Query("SELECT ue.username from UserEntity ue")
    List<String> getAllUsernames();
    @Query("SELECT ue.email from UserEntity ue")
    List<String> getAllEmails();
    @Query("SELECT u FROM UserEntity u WHERE LOWER(u.username) LIKE %:username%")
    List<UserEntity> findUsersByUsernameContaining(String username);
}

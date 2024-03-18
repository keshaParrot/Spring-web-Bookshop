package com.DenysiukProg.spring6webapp.repositories;

import com.DenysiukProg.spring6webapp.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String userName);
    UserEntity findFirstByUsername(String username);
}

package com.DenysiukProg.spring6webapp.repositories;

import com.DenysiukProg.spring6webapp.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for working with role table on database.
 */
public interface RoleRepository extends JpaRepository<Role,Long> {

    /**
     * Finds a role by name.
     *
     * @param name The name of the role to find.
     * @return The role with the specified name.
     */
    Role findByName(String name);
}

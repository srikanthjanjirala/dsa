package com.dsa.practice.repository;

import com.dsa.practice.enums.RoleName;
import com.dsa.practice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}

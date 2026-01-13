package com.dsa.practice.repository;

import com.dsa.practice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("Select u from User u WHERE u.email = :email")
    Optional<User> getUserByEmail(@Param("email") String email);
}
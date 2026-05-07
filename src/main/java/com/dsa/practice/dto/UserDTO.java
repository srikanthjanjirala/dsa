package com.dsa.practice.dto;

import java.util.Set;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String city,
        String department,
        String roleName,        // from Role
        String profileBio,      // example from Profile
        String phone,
        Set<String> courses     // only course names
) {
}

package com.vishal.learning.dto;

import com.vishal.learning.entities.enums.Role;

public record SignupDTO(
        String email,
        String password,
        String name,
        Role role
) {
}

package com.glensoft.demo.student;

import jakarta.validation.constraints.NotEmpty;

public record StudentDTO(
        @NotEmpty(message = "First name should not be empty")
        String firstName,
        @NotEmpty(message = "Last name should not be empty")
        String lastName,
        String email,
        Integer schoolId
) {
}

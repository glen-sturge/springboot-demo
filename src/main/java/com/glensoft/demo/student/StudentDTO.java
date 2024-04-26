package com.glensoft.demo.student;

public record StudentDTO(
        String firstName,
        String lastName,
        String email,
        Integer schoolId
) {
}

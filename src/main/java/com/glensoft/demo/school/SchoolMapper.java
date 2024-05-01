package com.glensoft.demo.school;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolMapper {
    public School toSchool(SchoolDTO dto) {
        return new School(dto.name());
    }

    public List<SchoolDTO> toSchoolDTOList(List<School> schools) {
        return schools.stream()
                .map(this::toSchoolDTO)
                .collect(Collectors.toList());
    }

    public SchoolDTO toSchoolDTO(School school) {
        return new SchoolDTO(school.getName());
    }
}

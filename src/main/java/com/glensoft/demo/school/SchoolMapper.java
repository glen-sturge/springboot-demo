package com.glensoft.demo.school;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// the main idea for this 'Mapper' type of Service class is that you are taking objects
// and converting them to DTO or taking the DTO and converting it to the object.
@Service
public class SchoolMapper {
    public School toSchool(SchoolDTO dto) {
        return new School(dto.name());
    }

    // I'm not sure which way I like better
    // either having a method here to handle List
    // or doing the looping in the service class.
    // both work. Will see as the tutorial progresses.
    public List<SchoolDTO> toSchoolDTOList(List<School> schools) {
        return schools.stream()
                .map(this::toSchoolDTO)
                .collect(Collectors.toList());
        //keeping this here for now as the difference between ways.
        //doing a branch for each update, so will remove after next update.
//        List<SchoolDTO> schoolsDTOs = new ArrayList<>();
//        schools.forEach(school -> {
//            schoolsDTOs.add(toSchoolDTO(school));
//        });
//        return schoolsDTOs;
    }
    public SchoolDTO toSchoolDTO(School school) {
        return new SchoolDTO(school.getName());
    }
}

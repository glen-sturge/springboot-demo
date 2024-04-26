package com.glensoft.demo.student;

import com.glensoft.demo.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Student toStudent(StudentDTO dto) {
        var student = new Student();
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        var school = new School();
        school.setId(dto.schoolId());
        student.setSchool(school);
        return student;
    }

    public StudentResponseDTO toStudentResponseDTO(Student student) {
        return new StudentResponseDTO(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
    }

    // method to process a list of Students then return as a List of StudentResponseDTO
    // he is doing the stream and mapping in the service class. So I am just leaving this here
    //for historical reasons for now.
//    public List<StudentResponseDTO> toStudentResponseDTOList(List<Student> studentList) {
//        return studentList.stream()
//                .map(this::toStudentResponseDTO)
//                .collect(Collectors.toList());
//    }
}

/*
* Notes:
*
* In the toStudent method, which we are using during Student creation
* prior to using a DTO for taking the input, when we were taking a Student object instead
* which looked something like
*
*{
*    "firstName": "John",
*    "lastName": "Pearce",
*    "email": "john.pearce2@example.com",
*    "school": {"id" : 2 }
*}
*Where after switching to a DTO, b/c of how we set the schoolId 'school.setId(dto.schoolId());'
*The json will just look like
*
*{
*    "firstName": "John",
*    "lastName": "Pearce",
*    "email": "john.pearce2@example.com",
*    "schoolId": 2
*}
*
* definitely cleans it up. At this point I am very enthused about DTO patterns.
*
*
* */
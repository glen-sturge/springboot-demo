package com.glensoft.demo.student;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponseDTO> showStudentList() { return studentService.findAllStudent(); }

    @GetMapping("/student/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentResponseDTO findStudentById(
            @PathVariable Integer id
    ) {
        return studentService.findStudentById(id);
    }

    @GetMapping("/students/search/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponseDTO> searchStudentsByFirstName(
            @PathVariable String name
    ) {
        return studentService.searchStudentByFirstName(name);
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDTO post(
            @RequestBody StudentDTO dto
    ) {
       return studentService.createStudent(dto);
    }

    @DeleteMapping("/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudentById(
            @PathVariable Integer id
    ) {
        studentService.deleteStudentById(id);
    }

}

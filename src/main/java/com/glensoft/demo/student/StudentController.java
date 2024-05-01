package com.glensoft.demo.student;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
            @Valid @RequestBody StudentDTO dto
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exp
    ) {
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName =((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}

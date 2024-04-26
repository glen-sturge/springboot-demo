package com.glensoft.demo.student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository repository, StudentMapper studentMapper) {
        this.repository = repository;
        this.studentMapper = studentMapper;
    }

//    public List<StudentResponseDTO> findAllStudent() {
//        return studentMapper.toStudentResponseDTOList(repository.findAll());
//    }
    public List<StudentResponseDTO> findAllStudent() {
        return repository.findAll()
                .stream()
                .map(studentMapper::toStudentResponseDTO)
                .collect(Collectors.toList());
    }

    public StudentResponseDTO findStudentById(Integer id) {
        return studentMapper.toStudentResponseDTO(repository.findById(id).orElse(new Student()));
    }

//    public List<StudentResponseDTO> searchStudentsByFirstName(String name) {
//        return studentMapper.toStudentResponseDTOList(repository.findAllByFirstNameContaining(name));
//    }
    public List<StudentResponseDTO> searchStudentByFirstName(String name) {
        return repository.findAllByFirstNameContaining(name)
                .stream()
                .map(studentMapper::toStudentResponseDTO)
                .collect(Collectors.toList());
    }

    public StudentResponseDTO createStudent(StudentDTO dto) {
        var student = studentMapper.toStudent(dto);
        var savedStudent = repository.save(student);
        return studentMapper.toStudentResponseDTO(savedStudent);
    }

    public void deleteStudentById(Integer id) {
        repository.deleteById(id);
    }
}

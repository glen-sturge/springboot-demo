package com.glensoft.demo.student;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class StudentServiceTest {

    // Which service we want to test
    @InjectMocks
    private StudentService studentService;

    // Declare the dependencies
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_student() {
        // Given
        StudentDTO dto = new StudentDTO(
                "John",
                "Doe",
                "john@mail.com",
                1
        );

        Student student = new Student(
                "John",
                "Doe",
                "john@mail.com",
                20
        );
        Student savedStudent = new Student(
                "John",
                "Doe",
                "john@mail.com",
                20
        );
        savedStudent.setId(1);


        // Mock the calls
        when(studentMapper.toStudent(dto))
                .thenReturn(student);
        when(studentRepository.save(student))
                .thenReturn(savedStudent);
        when(studentMapper.toStudentResponseDTO(savedStudent))
                .thenReturn(new StudentResponseDTO(
                        "John",
                        "Doe",
                        "john@mail.com"
                ));

        // When
        StudentResponseDTO studentResponseDTO = studentService.createStudent(dto);

        // Then (start asserting)
        assertEquals(dto.firstName(), studentResponseDTO.firstName());
        assertEquals(dto.lastName(), studentResponseDTO.lastName());
        assertEquals(dto.email(), studentResponseDTO.email());
        verify(studentMapper, times(1))
                .toStudent(dto);
        verify(studentRepository, times(1))
                .save(student);
        verify(studentMapper, times(1))
                .toStudentResponseDTO(savedStudent);
    }

    @Test
    public void should_return_list_of_studentResponseDTO() {
        // Given
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(
                "John",
                "Doe",
                "john@mail.com",
                20
        ));

        // Mock responses
        when(studentRepository.findAll())
                .thenReturn(studentList);
        when(studentMapper.toStudentResponseDTO(any(Student.class)))
                .thenReturn(new StudentResponseDTO(
                        "John",
                        "Doe",
                        "john@mail.com"
                ));

        // When
        List<StudentResponseDTO> studentResponseDTOList = studentService.findAllStudent();

        // Then
        assertEquals(studentList.size(), studentResponseDTOList.size());
        verify(studentRepository, times(1)).findAll();
        verify(studentMapper, times(1)).toStudentResponseDTO(any(Student.class));
    }

    @Test
    public void should_return_studentResponseDTO_by_studentId() {
        // Given
        Integer studentId = 1;
        Student student = new Student(
                "John",
                "Doe",
                "john@mail.com",
                20
        );

        // Mock Responses
        when(studentRepository.findById(studentId))
                .thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDTO(any(Student.class)))
                .thenReturn(new StudentResponseDTO(
                        "John",
                        "Doe",
                        "john@mail.com"
                ));

        // When
        StudentResponseDTO studentResponseDTO = studentService.findStudentById(studentId);

        // Then
        assertEquals(student.getFirstName(), studentResponseDTO.firstName());
        assertEquals(student.getLastName(), studentResponseDTO.lastName());
        assertEquals(student.getEmail(), studentResponseDTO.email());
        verify(studentRepository, times(1))
                .findById(studentId);
        verify(studentMapper, times(1))
                .toStudentResponseDTO(any(Student.class));
    }

    @Test
    public void should_return_list_of_studentResponseDTO_by_student_first_name() {
        // Given
        String studentFirstName = "John";
        List<Student> studentList = new ArrayList<>();
        Student student = new Student(
                "John",
                "Doe",
                "john@mail.com",
                20
        );
        student.setId(1);
        studentList.add(student);
        // Mock calls
        when(studentRepository.findAllByFirstNameContaining(studentFirstName))
                .thenReturn(studentList);
        when(studentMapper.toStudentResponseDTO(any(Student.class)))
                .thenReturn(new StudentResponseDTO(
                        "John",
                        "Doe",
                        "john@mail.com"
                ));
        // when
        List<StudentResponseDTO> studentResponseDTOList =
                studentService.searchStudentByFirstName(studentFirstName);

        // then
        assertEquals(studentList.size(), studentResponseDTOList.size());
        assertEquals(studentFirstName, studentResponseDTOList.get(0).firstName());
        verify(studentRepository, times(1))
                .findAllByFirstNameContaining(studentFirstName);
        verify(studentMapper, times(1))
                .toStudentResponseDTO(any(Student.class));
    }
}
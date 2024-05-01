package com.glensoft.demo.school;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    public SchoolService(SchoolRepository repository, SchoolMapper schoolMapper) {
        this.schoolRepository = repository;
        this.schoolMapper = schoolMapper;
    }

    public List<SchoolDTO> findAllSchool() {
        return schoolRepository.findAll()
            .stream()
            .map(schoolMapper::toSchoolDTO)
            .collect(Collectors.toList());
    }

    public List<School> findAllDetailed() {
        return schoolRepository.findAll();
    }

    public SchoolDTO createSchool(SchoolDTO dto) {
        var school = schoolMapper.toSchool(dto);
        return schoolMapper.toSchoolDTO(schoolRepository.save(school));
    }

    public SchoolDTO findSchoolById(Integer id) {
        return schoolMapper.toSchoolDTO(schoolRepository.findById(id).orElse(new School()));
    }

    public void deleteSchoolById(Integer id) {
        schoolRepository.deleteById(id);
    }

    public List<SchoolDTO> searchSchoolByName(String name) {
        return schoolRepository.findAllByNameContaining(name)
                .stream()
                .map(schoolMapper::toSchoolDTO)
                .collect(Collectors.toList());
    }

}

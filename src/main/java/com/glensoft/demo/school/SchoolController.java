package com.glensoft.demo.school;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/schools")
    @ResponseStatus(HttpStatus.OK)
    public List<SchoolDTO> findAll() {
        return schoolService.findAllSchool();
    }
    @GetMapping("/schools/detailed")
    @ResponseStatus(HttpStatus.OK)
    public List<School> findAllDetailed() {
        return schoolService.findAllDetailed();
    }

    @PostMapping("/schools")
    @ResponseStatus(HttpStatus.CREATED)
    public SchoolDTO createSchool(@RequestBody SchoolDTO dto) { return schoolService.createSchool(dto); }

    @GetMapping("/schools/search/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<SchoolDTO> searchSchoolsByName(@PathVariable String name) { return schoolService.searchSchoolByName(name); }

    @GetMapping("/school/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SchoolDTO findSchoolById(@PathVariable Integer id) {
        return schoolService.findSchoolById(id);
    }
    @DeleteMapping("/school/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSchoolById(@PathVariable Integer id) { schoolService.deleteSchoolById(id); }
}

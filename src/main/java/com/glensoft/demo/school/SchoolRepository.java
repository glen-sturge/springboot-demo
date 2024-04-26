package com.glensoft.demo.school;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, Integer> {
    List<School> findAllByNameContaining(String name);
}

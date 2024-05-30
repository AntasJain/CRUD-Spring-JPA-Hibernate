package com.antasjain.cruddemo.dao;

import com.antasjain.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student student);

    Student findById(Integer id);

    List<Student> findAll();

}

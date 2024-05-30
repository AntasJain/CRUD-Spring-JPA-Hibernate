package com.antasjain.cruddemo.dao;

import com.antasjain.cruddemo.entity.Student;

public interface StudentDAO {
    void save(Student student);

    Student findById(Integer id);

}

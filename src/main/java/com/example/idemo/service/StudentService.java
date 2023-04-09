package com.example.idemo.service;

import com.example.idemo.model.Student;
import com.example.idemo.model.User;

import java.util.List;

public interface StudentService {


    void saveStudent(Student student);

    List<Student> findAllByCreatedUser(User user);

    Student findOne(Long id);

    List<Student> findAll();

}

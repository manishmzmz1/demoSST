package com.example.idemo.service.Impl;

import com.example.idemo.model.Student;
import com.example.idemo.model.User;
import com.example.idemo.repository.StudentRepository;
import com.example.idemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> findAllByCreatedUser(User user) {
        return studentRepository.findAllByCreatedUser(user);
    }

    @Override
    public Student findOne(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}

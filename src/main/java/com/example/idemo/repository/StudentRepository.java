package com.example.idemo.repository;

import com.example.idemo.model.Student;
import com.example.idemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    List<Student> findAllByCreatedUser(User user);

}

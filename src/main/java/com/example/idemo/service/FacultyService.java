package com.example.idemo.service;

import com.example.idemo.model.Faculty;

import java.util.List;

public interface FacultyService {

    Faculty saveData(Faculty faculty);

    Faculty findOne(Long id);

    List<Faculty> findAll();
}

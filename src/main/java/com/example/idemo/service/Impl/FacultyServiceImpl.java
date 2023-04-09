package com.example.idemo.service.Impl;

import com.example.idemo.model.Faculty;
import com.example.idemo.repository.FacultyRepository;
import com.example.idemo.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public Faculty saveData(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findOne(Long id) {
        return facultyRepository.findById(id).get();
    }

    @Override
    public List<Faculty> findAll() {
        return facultyRepository.findAll();
    }
}

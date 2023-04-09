package com.example.idemo.service;

import com.example.idemo.model.User;

import java.util.List;

public interface UserService {

    User findUserByUsername(String username);

    User saveData(User user);

    List<User> findAll();

    User findOne(Long id);
}

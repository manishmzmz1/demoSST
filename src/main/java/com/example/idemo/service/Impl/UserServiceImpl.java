package com.example.idemo.service.Impl;

import com.example.idemo.model.User;
import com.example.idemo.repository.UserRepository;
import com.example.idemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;



    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveData(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findById(id).get();
    }
}

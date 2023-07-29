package com.example.spring_boot.service;

import com.example.spring_boot.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllUser();
    void save(User user);
    User getUser(Long id);
    void delete(Long id);
}

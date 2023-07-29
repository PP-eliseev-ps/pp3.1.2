package com.example.spring_boot.dao;

import com.example.spring_boot.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    void save(User user);
    User getUser(Long id);
    void delete(Long id);
}

package com.example.dao;

import com.example.entity.User;
import java.util.List;

public interface UsersDAO {
    User findById(String id);
    User login(String id, String password);
    List<User> findAll();
    void insert(User user);
    void update(User user);
    void delete(String id);
}

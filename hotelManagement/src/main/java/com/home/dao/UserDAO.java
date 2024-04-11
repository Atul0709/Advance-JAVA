package com.home.dao;

import com.home.model.User;

public interface UserDAO {
    User getUserByUsername(String username);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
}

package com.bsuir.buspark.bl;

import com.bsuir.buspark.entity.User;

import java.util.List;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
    List<User> getDrivers();
}
package com.bsuir.buspark.dal;

import com.bsuir.buspark.entity.Role;
import com.bsuir.buspark.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByRolesContains(Role role);
}

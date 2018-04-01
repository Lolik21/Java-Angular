package com.bsuir.buspark.dal;

import com.bsuir.buspark.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findByName(String name);
}

package com.bsuir.buspark.bl;

import com.bsuir.buspark.entity.Role;

import java.util.List;

public interface RoleService {
    Role create(Role role);
    Role update(int oldRoleId, Role role);
    Role read(int roleId);
    void delete(int roleId);
    List<Role> getAll();
    List<Role> findByName(String name);
}

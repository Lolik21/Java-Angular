package com.bsuir.buspark.bl.impl;

import com.bsuir.buspark.bl.RoleService;
import com.bsuir.buspark.dal.RoleRepository;
import com.bsuir.buspark.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(int oldRoleId, Role role) {

        Role selectedRole = roleRepository.findById(Long.valueOf(oldRoleId)).get();
        selectedRole.setName(role.getName());
        selectedRole.setUsers(role.getUsers());
        return roleRepository.save(selectedRole);
    }

    @Override
    public Role read(int roleId) {
        return roleRepository.findById(Long.valueOf(roleId)).get();
    }

    @Override
    public void delete(int roleId) {
        roleRepository.deleteById(Long.valueOf(roleId));
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}

package com.bsuir.buspark.bl.impl;

import com.bsuir.buspark.bl.RoleService;
import com.bsuir.buspark.bl.exception.RoleNotFoundException;
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

        Role selectedRole = roleRepository.findById(oldRoleId).orElseThrow(
                () -> new RoleNotFoundException("Cannot update role with requested ID")
        );
        selectedRole.setName(role.getName());
        selectedRole.setUsers(role.getUsers());
        return roleRepository.save(selectedRole);
    }

    @Override
    public Role read(int roleId) {
        return roleRepository.findById(roleId).orElseThrow(
                () -> new RoleNotFoundException("Cannot find role with requested ID")
        );
    }

    @Override
    public void delete(int roleId) {
        roleRepository.findById(roleId).orElseThrow(
                () -> new RoleNotFoundException("Cannot delete role with requested ID")
        );
        roleRepository.deleteById(roleId);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}

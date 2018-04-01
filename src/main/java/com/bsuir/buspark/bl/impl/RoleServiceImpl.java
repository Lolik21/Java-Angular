package com.bsuir.buspark.bl.impl;

import com.bsuir.buspark.bl.RoleService;
import com.bsuir.buspark.bl.UserService;
import com.bsuir.buspark.bl.exception.notFound.RoleNotFoundException;
import com.bsuir.buspark.bl.exception.other.RoleAlreadyExistsException;
import com.bsuir.buspark.dal.RoleRepository;
import com.bsuir.buspark.dal.UserRepository;
import com.bsuir.buspark.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

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
        Role role = roleRepository.findById(roleId).orElseThrow(
                () -> new RoleNotFoundException("Cannot delete role with requested ID")
        );

        if (userRepository.findByRolesContains(role) == null)
        {
            roleRepository.deleteById(roleId);
        }
        else
        {
            throw new RoleAlreadyExistsException("Role is in use");
        }
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }
}

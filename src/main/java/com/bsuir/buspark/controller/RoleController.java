package com.bsuir.buspark.controller;
import com.bsuir.buspark.bl.RoleService;
import com.bsuir.buspark.bl.validator.RoleValidatorImpl;
import com.bsuir.buspark.bl.validator.Validator;
import com.bsuir.buspark.entity.City;
import com.bsuir.buspark.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles/")
public class RoleController {
    @Autowired
    private RoleService roleService;

    private Validator validator = new RoleValidatorImpl();

    @RequestMapping(method = RequestMethod.GET, value = "/")
    List<Role> getAllRoles() {
        return this.roleService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    Role addNewRole(Role role) {
        this.validator.validate(role);
        return this.roleService.create(role);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    void deleteRole (@PathVariable int id) {
        roleService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    Role getRoleById(@PathVariable int id)
    {
        return roleService.read(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}")
    Role updateRole(@PathVariable int id, Role role){
        this.validator.validate(role);
        return roleService.update(id, role);
    }
}

package com.bsuir.buspark.controller;
import com.bsuir.buspark.bl.RoleService;
import com.bsuir.buspark.bl.UserService;
import com.bsuir.buspark.bl.validator.RoleValidatorImpl;
import com.bsuir.buspark.bl.validator.Validator;
import com.bsuir.buspark.entity.City;
import com.bsuir.buspark.entity.Role;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles/")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    private Validator validator = new RoleValidatorImpl();

    @RequestMapping(method = RequestMethod.GET, value = "/")
    List<Role> getAllRoles() {
        return this.roleService.getAll();
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

    @RequestMapping(method = RequestMethod.PUT, value = "/{userId}")
    void addRoleToUser(@PathVariable int userId, Role role)
    {
        validator.validate(role);
        userService.addRoleToUser(userId,role);
    }
}

package com.bsuir.buspark.controller;

import com.bsuir.buspark.bl.RoleService;
import com.bsuir.buspark.bl.UserService;
import com.bsuir.buspark.bl.validator.UserValidatorImpl;
import com.bsuir.buspark.bl.validator.Validator;
import com.bsuir.buspark.entity.Role;
import com.bsuir.buspark.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
@RequestMapping("/auth/")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public User registration (User user) {

        Validator validator = new UserValidatorImpl(userService);
        validator.validate(user);

        Role userRole = new Role();
        userRole.setName("USER");
        userRole = roleService.create(userRole);
        HashSet<Role> registeredRoleSet = new HashSet<>();
        registeredRoleSet.add(userRole);
        user.setRoles(registeredRoleSet);
        User outUser = userService.save(user);
        outUser.setPasswordConfirm(null);
        return outUser;
    }
}

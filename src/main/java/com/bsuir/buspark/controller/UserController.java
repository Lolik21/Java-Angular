package com.bsuir.buspark.controller;

import com.bsuir.buspark.bl.UserService;
import com.bsuir.buspark.bl.validator.UserValidatorImpl;
import com.bsuir.buspark.bl.validator.Validator;
import com.bsuir.buspark.entity.Ticket;
import com.bsuir.buspark.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;

    private Validator validator = new UserValidatorImpl(userService);

    @RequestMapping(method = RequestMethod.GET, value = "/")
    List<User> getAllUsers() {
        return this.userService.getAll();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    void deleteUser (@PathVariable int id) {
        userService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    User getUserById(@PathVariable int id)
    {
        return userService.read(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/drivers/")
    List<User> getDrivers()
    {
        return userService.getDrivers();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    User updateUser(@PathVariable int id, User user){
        this.validator.validate(user);
        return userService.update(id, user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/{userName}")
    User getByUserName(@PathVariable String  userName){
        return userService.findByUsername(userName);
    }

}
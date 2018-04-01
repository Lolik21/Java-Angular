package com.bsuir.buspark.bl.validator;

import com.bsuir.buspark.bl.UserService;
import com.bsuir.buspark.bl.exception.validation.UserValidationException;
import com.bsuir.buspark.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;


public class UserValidatorImpl implements Validator {

    private UserService userService;

    public UserValidatorImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void validate(Object classToValidate) {
        User user = (User) classToValidate;

        if (user == null)
        {
            throw new UserValidationException("User is null");
        }

        if (user.getUsername() == null)
        {
            throw new UserValidationException("Username is required");
        }

        if (user.getName() == null)
        {
            throw new UserValidationException("Name is required");
        }

        if (user.getSurname() == null)
        {
            throw new UserValidationException("Surname is required");
        }

        if (user.getPassword() == null)
        {
            throw new UserValidationException("Password is required");
        }

        if (user.getPasswordConfirm() == null)
        {
            throw new UserValidationException("Password conformation is required");
        }

        if (user.getUsername().length() < 8 || user.getUsername().length() > 32) {
            throw new UserValidationException("Username filed must be 8 to 32 characters long");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            throw new UserValidationException("User is already exists in database");
        }

        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            throw new UserValidationException("Password be 8 to 32 characters long");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            throw new UserValidationException("Password conformation is failed");
        }

        if (user.getName().length() < 2){
            throw new UserValidationException("Name filed must be 2 to 100 characters long");
        }

        if (user.getName().length() > 100){
            throw new UserValidationException("Name filed must be 2 to 100 characters long");
        }

        if (user.getSurname().length() < 2){
            throw new UserValidationException("Surname filed must be 2 to 100 characters long");
        }

        if (user.getSurname().length() > 100){
            throw new UserValidationException("Surname filed must be 2 to 100 characters long");
        }

        Pattern pattern = Pattern.compile("^[a-z,A-Z]+$");

        if (!pattern.matcher(user.getName()).matches()){
            throw new UserValidationException("Name pattern mismatch");
        }

        if (!pattern.matcher(user.getSurname()).matches()){
            throw new UserValidationException("Surname pattern mismatch");
        }

        Pattern patternLogin = Pattern.compile("^[a-z,A-Z,0-9]+$");

        if (!patternLogin.matcher(user.getUsername()).matches()){
            throw new UserValidationException("Username pattern mismatch");
        }

        if (!patternLogin.matcher(user.getPassword()).matches()){
            throw new UserValidationException("Password pattern mismatch");
        }
    }
}
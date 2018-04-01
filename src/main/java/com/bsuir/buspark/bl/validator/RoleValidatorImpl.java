package com.bsuir.buspark.bl.validator;

import com.bsuir.buspark.bl.exception.validation.UserValidationException;
import com.bsuir.buspark.entity.Role;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class RoleValidatorImpl implements Validator {

    @Override
    public void validate(Object classToValidate) {
        Role role = (Role)classToValidate;
        if (role == null)
        {
            throw new UserValidationException("Role is null");
        }

        Pattern pattern = Pattern.compile("^[a-z,A-Z]+$");

        if (!pattern.matcher(role.getName()).matches()){
            throw new UserValidationException("Role name pattern mismatch");
        }
    }
}

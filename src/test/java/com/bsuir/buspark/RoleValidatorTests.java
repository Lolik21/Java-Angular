package com.bsuir.buspark;

import com.bsuir.buspark.bl.exception.validation.UserValidationException;
import com.bsuir.buspark.bl.validator.RoleValidatorImpl;
import com.bsuir.buspark.entity.Role;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class RoleValidatorTests {

    private RoleValidatorImpl roleValidator = new RoleValidatorImpl();

    @Test
    @Parameters({"ADMIN",
                 "USER",
                 "ACCOUTENTC",
                 "ACHTICTER",
                 "SUPERADMIN"})
    public void validRoleData(String name) {
        Role role = new Role();
        role.setName(name);
        roleValidator.validate(role);
    }

    @Test(expected = UserValidationException.class)
    @Parameters({"ADMIN!!!",
            "@#USER",
            "ACC123OUTENTC",
            "ACHTI$$CTER",
            "SUPER___ADMIN"})
    public void InvalidRoleData(String name) {
        Role role = new Role();
        role.setName(name);
        roleValidator.validate(role);
    }

    @Test(expected = UserValidationException.class)
    public void InvalidRoleDataNameNull() {
        Role role = new Role();
        role.setName(null);
        roleValidator.validate(role);
    }

    @Test(expected = UserValidationException.class)
    public void InvalidRoleNull() {
        roleValidator.validate(null);
    }
}
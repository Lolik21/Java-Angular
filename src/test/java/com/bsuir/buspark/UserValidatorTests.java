package com.bsuir.buspark;

import com.bsuir.buspark.bl.UserService;
import com.bsuir.buspark.bl.exception.validation.UserValidationException;
import com.bsuir.buspark.bl.impl.user.UserServiceImpl;
import com.bsuir.buspark.bl.validator.UserValidatorImpl;
import com.bsuir.buspark.entity.User;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(JUnitParamsRunner.class)
@WebAppConfiguration
public class UserValidatorTests{

    private UserService userService = mock(UserServiceImpl.class);

    private UserValidatorImpl userValidator;

    @Before
    public void init()
    {
        when(this.userService.findByUsername(any(String.class))).thenReturn(null);
        userValidator = new UserValidatorImpl(userService);
    }

    @Test
    @Parameters({"Ilya,Kremniou,SuperAdmin,4,123123123,123123123",
                 "Kostya,Shutko,Shoker31,4,DASD123ds,DASD123ds",
                 "Andrey,Dizel,King2233,4,12312334,12312334",
                 "Gog,Freeman,Quin3721,4,sd23asdd,sd23asdd",
                 "Yan,Shadol,SuperAdmin2,4,DASdd42sa,DASdd42sa",
                 "Yoyo,Shimano,SuperAdmin3,4,123asd123123,123asd123123",
                 "Vika,Nechay,VikaGirl32,4,12sads43123,12sads43123"})
    public void validUserData(String name, String surname, String username, String tickets, String password, String confPassword) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setTickets(tickets);
        user.setPassword(password);
        user.setPasswordConfirm(confPassword);
        user.setRoles(new HashSet<>());

        userValidator.validate(user);
    }

    @Test(expected = UserValidationException.class)
    @Parameters({"Ilya1,Kremniou,SuperAdmin,4,123123123,123123123",
            "2Kostya,Shutko,Shoker31,4,DASD123ds,DASD123ds",
            "Andrey@#,Dizel,King2233,4,12312334,12312334",
            "Gog__,Freeman,Quin3721,4,sd23asdd,sd23asdd",
            "...Yan,Shadol,King2233,4,DASdd42sa,DASdd42sa",
            "Yo4yo,Shimano,Quin3721,4,123asd123123,123asd123123",
            "V()ika,Nechay,VikaGirl32,4,12sads43123,12sads43123"})
    public void InvalidUserDataName(String name, String surname, String username, String tickets, String password, String confPassword) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setTickets(tickets);
        user.setPassword(password);
        user.setPasswordConfirm(confPassword);
        user.setRoles(new HashSet<>());

        userValidator.validate(user);
    }

    @Test(expected = UserValidationException.class)
    @Parameters({"Ilya,Kremniou1,SuperAdmin,4,123123123,123123123",
            "Kostya,1Shutko,Shoker31,4,DASD123ds,DASD123ds",
            "Andrey,Dizel__,King2233,4,12312334,12312334",
            "Gog,Fre#@eman,Quin3721,4,sd23asdd,sd23asdd",
            "Yan,Sha<>dol,King2233,4,DASdd42sa,DASdd42sa",
            "Yoyo,Shim12ano,Quin3721,4,123asd123123,123asd123123",
            "Vika,Nechay41,VikaGirl32,4,12sads43123,12sads43123"})
    public void InvalidUserDataSurname(String name, String surname, String username, String tickets, String password, String confPassword) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setTickets(tickets);
        user.setPassword(password);
        user.setPasswordConfirm(confPassword);
        user.setRoles(new HashSet<>());

        userValidator.validate(user);
    }

    @Test(expected = UserValidationException.class)
    @Parameters({"Ilya,Kremniou,SuperA#@dmin,4,123123123,123123123",
            "Kostya,Shutko,#@Shoker31,4,DASD123ds,DASD123ds",
            "Andrey,Dizel,Su__perAdmin,4,12312334,12312334",
            "Gog,Freeman,Super<>Admin,4,sd23asdd,sd23asdd",
            "Yan,Shadol,SuperAd()min,4,DASdd42sa,DASdd42sa",
            "Yoyo,Shimano,SuperA!!dmin,4,123asd123123,123asd123123",
            "Vika,Nechay,VikaGir l32,4,12sads43123,12sads43123"})
    public void InvalidUserDataUserName(String name, String surname, String username, String tickets, String password, String confPassword) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setTickets(tickets);
        user.setPassword(password);
        user.setPasswordConfirm(confPassword);
        user.setRoles(new HashSet<>());

        userValidator.validate(user);
    }

    @Test(expected = UserValidationException.class)
    @Parameters({"Ilya,Kremniou,SuperAdmin,4,1231#@23123,231#@23123",
            "Kostya,Shutko,Shoker31,4,DAS!!D123ds,DAS!!D123ds",
            "Andrey,Dizel,Quin3721,4,12$%312334,12$%312334",
            "Gog,Freeman,King2233,4,sd23as__dd,sd23as__dd",
            "Yan,Shadol,Shoker31,4,DASdd4    2sa,DASdd4    2sa",
            "Yoyo,Shimano,King2233,4,123asd)(((123123,123asd)(((123123",
            "Vika,Nechay,VikaGirl32,4,12sads43123.....,12sads43123....."})
    public void InvalidUserDataPassword(String name, String surname, String username, String tickets, String password, String confPassword) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setTickets(tickets);
        user.setPassword(password);
        user.setPasswordConfirm(confPassword);
        user.setRoles(new HashSet<>());
        userValidator.validate(user);
    }
}
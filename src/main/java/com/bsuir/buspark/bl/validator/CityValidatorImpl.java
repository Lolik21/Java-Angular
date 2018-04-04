package com.bsuir.buspark.bl.validator;

import com.bsuir.buspark.bl.exception.validation.CityValidationException;
import com.bsuir.buspark.bl.exception.validation.UserValidationException;
import com.bsuir.buspark.entity.City;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class CityValidatorImpl implements Validator {
    @Override
    public void validate(Object classToValidate) {
        City city = (City)classToValidate;

        if (city == null)
        {
            throw new CityValidationException("City is null");
        }

        if (city.getName() == null)
        {
            throw new CityValidationException("City name is null");
        }

        if (city.getDistance() < 1 || city.getDistance() > 10000)
        {
            throw new CityValidationException("City distance can be only from 1 to 10000 km");
        }

        Pattern pattern = Pattern.compile("^[a-z,A-Z ]+$");
        if (!pattern.matcher(city.getName()).matches()){
            throw new CityValidationException("City name pattern mismatch");
        }
    }
}

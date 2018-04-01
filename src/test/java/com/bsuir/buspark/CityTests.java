package com.bsuir.buspark;

import com.bsuir.buspark.bl.exception.validation.CityValidationException;
import com.bsuir.buspark.bl.validator.CityValidatorImpl;
import com.bsuir.buspark.entity.City;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class CityTests {

    private CityValidatorImpl cityValidator = new CityValidatorImpl();

    @Test
    @Parameters({"Minsk,200", "Grodno,400", "Some City, 20", "City,30", "Polotsk,240"})
    public void validCityData(String name, int distance) {
       City city = new City();
       city.setDistance(distance);
       city.setName(name);

       cityValidator.validate(city);
    }

    @Test(expected = CityValidationException.class)
    @Parameters({"Minsk,200000", "Grodno,0", "Some City, -1", "City,-99999999", "Polotsk, 10001"})
    public void invalidCityDataDistance(String name, int distance) {
        City city = new City();
        city.setDistance(distance);
        city.setName(name);

        cityValidator.validate(city);
    }

    @Test(expected = CityValidationException.class)
    @Parameters({"Min32sk,200", "Grod@!no,100", "Some City123, 199", "C!!$%ity,100", "Polotsk___N, 1001"})
    public void invalidCityDataName(String name, int distance) {
        City city = new City();
        city.setDistance(distance);
        city.setName(name);

        cityValidator.validate(city);
    }
}

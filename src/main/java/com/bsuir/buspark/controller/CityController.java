package com.bsuir.buspark.controller;

import com.bsuir.buspark.bl.CityService;
import com.bsuir.buspark.bl.validator.CityValidatorImpl;
import com.bsuir.buspark.bl.validator.Validator;
import com.bsuir.buspark.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities/")
public class CityController {
    @Autowired
    private CityService cityService;

    private Validator validator = new CityValidatorImpl();

    @RequestMapping(method = RequestMethod.GET, value = "/")
    List<City> getAllCities() {
        return this.cityService.getAll();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, value = "/")
    City addNewCity(City city) {
        this.validator.validate(city);
        return this.cityService.create(city);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    void deleteCity (@PathVariable int id) {
        cityService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    City getCityById(@PathVariable int id)
    {
        return cityService.read(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    City updateCity(@PathVariable int id, City city){
        this.validator.validate(city);
        return cityService.update(id, city);
    }
}

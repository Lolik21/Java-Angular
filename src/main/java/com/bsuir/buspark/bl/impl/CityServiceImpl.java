package com.bsuir.buspark.bl.impl;

import com.bsuir.buspark.bl.CityService;
import com.bsuir.buspark.dal.CityRepository;
import com.bsuir.buspark.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;

    @Override
    public City create(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City update(int oldCityId, City city) {
        City selectedCity = cityRepository.findById(Long.valueOf(oldCityId)).get();
        selectedCity.setDistance(city.getDistance());
        selectedCity.setName(city.getName());
        return cityRepository.save(selectedCity);
    }

    @Override
    public City read(int cityId) {
        return cityRepository.findById(Long.valueOf(cityId)).get();
    }

    @Override
    public void delete(int cityId) {
        cityRepository.deleteById(Long.valueOf(cityId));
    }

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }
}

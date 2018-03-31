package com.bsuir.buspark.controller;

import com.bsuir.buspark.bl.BusService;
import com.bsuir.buspark.bl.validator.BusValidatorImpl;
import com.bsuir.buspark.bl.validator.Validator;
import com.bsuir.buspark.entity.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/buses/")
public class BusController {
    @Autowired
    private BusService busService;

    private Validator validator = new BusValidatorImpl();

    @RequestMapping(method = RequestMethod.GET, value = "/")
    List<Bus> getAllBuses() {
        return this.busService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    Bus addNewBus(Bus bus) {
        this.validator.validate(bus);
        return this.busService.create(bus);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    void deleteBus (@PathVariable int id) {
        busService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    Bus getBusById(@PathVariable int id)
    {
        return busService.read(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}")
    Bus updateTicket(@PathVariable int id, Bus bus){
        this.validator.validate(bus);
        return busService.update(id, bus);
    }
}

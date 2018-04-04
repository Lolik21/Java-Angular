package com.bsuir.buspark;

import com.bsuir.buspark.bl.exception.validation.TicketValidationException;
import com.bsuir.buspark.bl.validator.TicketValidatorImpl;
import com.bsuir.buspark.entity.Bus;
import com.bsuir.buspark.entity.City;
import com.bsuir.buspark.entity.Ticket;
import com.bsuir.buspark.entity.User;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

@RunWith(JUnitParamsRunner.class)
public class TicketValidatorTests {

    private TicketValidatorImpl ticketValidator = new TicketValidatorImpl();

    @Test
    @Parameters({"100,International,20",
            "20,notInternational,50",
            "80,International,150",
            "5,notInternational,3000",
            "15,International,10000",
            "15,INTERNATIONAL,10000",
            "15,NOTINTERNATIONAL,10000"})
    public void validTicketData(int count, String isInternational, int distance) {
        Ticket ticket = new Ticket();
        ticket.setCount(count);
        ticket.setIsInternational(isInternational);
        ticket.setDistance(distance);
        ticket.setBus(new Bus());
        ticket.setArrivalTime(new Date(System.currentTimeMillis()));
        ticket.setDepartmentTime(new Date(System.currentTimeMillis()- 10000));
        ticket.setDepartmentCity(new City());
        ticket.setArrivalCity(new City());
        ticket.setDriver(new User());

        ticketValidator.validate(ticket);
    }

    @Test(expected = TicketValidationException.class)
    @Parameters({"100,International,20"})
    public void validTicketDataBusIsNull(int count, String isInternational, int distance) {
        Ticket ticket = new Ticket();
        ticket.setCount(count);
        ticket.setIsInternational(isInternational);
        ticket.setDistance(distance);
        ticket.setBus(null);
        ticket.setArrivalTime(new Date(System.currentTimeMillis()));
        ticket.setDepartmentTime(new Date(System.currentTimeMillis()- 10000));
        ticket.setDepartmentCity(new City());
        ticket.setArrivalCity(new City());
        ticket.setDriver(new User());

        ticketValidator.validate(ticket);
    }

    @Test(expected = TicketValidationException.class)
    @Parameters({"100,International,20"})
    public void validTicketDataArrTimeNull(int count, String isInternational, int distance) {
        Ticket ticket = new Ticket();
        ticket.setCount(count);
        ticket.setIsInternational(isInternational);
        ticket.setDistance(distance);
        ticket.setBus(new Bus());
        ticket.setArrivalTime(null);
        ticket.setDepartmentTime(new Date(System.currentTimeMillis()- 10000));
        ticket.setDepartmentCity(new City());
        ticket.setArrivalCity(new City());
        ticket.setDriver(new User());

        ticketValidator.validate(ticket);
    }

    @Test(expected = TicketValidationException.class)
    @Parameters({"100,International,20"})
    public void validTicketDataDepTimeIsNull(int count, String isInternational, int distance) {
        Ticket ticket = new Ticket();
        ticket.setCount(count);
        ticket.setIsInternational(isInternational);
        ticket.setDistance(distance);
        ticket.setBus(new Bus());
        ticket.setArrivalTime(new Date(System.currentTimeMillis()));
        ticket.setDepartmentTime(null);
        ticket.setDepartmentCity(new City());
        ticket.setArrivalCity(new City());
        ticket.setDriver(new User());

        ticketValidator.validate(ticket);
    }


    @Test(expected = TicketValidationException.class)
    @Parameters({"100,International,20"})
    public void validTicketDataArrCityIsNull(int count, String isInternational, int distance) {
        Ticket ticket = new Ticket();
        ticket.setCount(count);
        ticket.setIsInternational(isInternational);
        ticket.setDistance(distance);
        ticket.setBus(new Bus());
        ticket.setArrivalTime(new Date(System.currentTimeMillis()));
        ticket.setDepartmentTime(new Date(System.currentTimeMillis()- 10000));
        ticket.setDepartmentCity(new City());
        ticket.setArrivalCity(null);
        ticket.setDriver(new User());

        ticketValidator.validate(ticket);
    }

    @Test(expected = TicketValidationException.class)
    @Parameters({"100,International,20"})
    public void validTicketDataDepCityIsNull(int count, String isInternational, int distance) {
        Ticket ticket = new Ticket();
        ticket.setCount(count);
        ticket.setIsInternational(isInternational);
        ticket.setDistance(distance);
        ticket.setBus(new Bus());
        ticket.setArrivalTime(new Date(System.currentTimeMillis()));
        ticket.setDepartmentTime(new Date(System.currentTimeMillis()- 10000));
        ticket.setDepartmentCity(null);
        ticket.setArrivalCity(new City());
        ticket.setDriver(new User());

        ticketValidator.validate(ticket);
    }

    @Test(expected = TicketValidationException.class)
    @Parameters({"100,International,20"})
    public void validTicketDriverIsNull(int count, String isInternational, int distance) {
        Ticket ticket = new Ticket();
        ticket.setCount(count);
        ticket.setIsInternational(isInternational);
        ticket.setDistance(distance);
        ticket.setBus(new Bus());
        ticket.setArrivalTime(new Date(System.currentTimeMillis()));
        ticket.setDepartmentTime(new Date(System.currentTimeMillis()- 10000));
        ticket.setDepartmentCity(new City());
        ticket.setArrivalCity(new City());
        ticket.setDriver(null);

        ticketValidator.validate(ticket);
    }


    @Test(expected = TicketValidationException.class)
    @Parameters({"0,International,20",
            "-1,notInternational,50",
            "101,International,150",
            "5000,notInternational,3000",
            "-5000,International,10000"})
    public void InvalidTicketDataCount(int count, String isInternational, int distance) {
        Ticket ticket = new Ticket();
        ticket.setCount(count);
        ticket.setIsInternational(isInternational);
        ticket.setDistance(distance);
        ticket.setBus(new Bus());
        ticket.setArrivalTime(new Date(System.currentTimeMillis()));
        ticket.setDepartmentTime(new Date(System.currentTimeMillis()- 1000));
        ticket.setDepartmentCity(new City());
        ticket.setArrivalCity(new City());
        ticket.setDriver(new User());

        ticketValidator.validate(ticket);
    }


    @Test(expected = TicketValidationException.class)
    @Parameters({"100,International ++,20",
            "20,notInternnal,50",
            "80,Intnational,150",
            "5,nternational,3000",
            "15,SDInternational,10000"})
    public void InvalidTicketDataInternational(int count, String isInternational, int distance) {
        Ticket ticket = new Ticket();
        ticket.setCount(count);
        ticket.setIsInternational(isInternational);
        ticket.setDistance(distance);
        ticket.setBus(new Bus());
        ticket.setArrivalTime(new Date(System.currentTimeMillis()));
        ticket.setDepartmentTime(new Date(System.currentTimeMillis()- 20000));
        ticket.setDepartmentCity(new City());
        ticket.setArrivalCity(new City());
        ticket.setDriver(new User());

        ticketValidator.validate(ticket);
    }

    @Test(expected = TicketValidationException.class)
    @Parameters({"100,International,-1",
            "20,notInternational,0",
            "80,International,10001",
            "5,notInternational,-5000",
            "15,International,500000"})
    public void InvalidTicketDataDistance(int count, String isInternational, int distance) {
        Ticket ticket = new Ticket();
        ticket.setCount(count);
        ticket.setIsInternational(isInternational);
        ticket.setDistance(distance);
        ticket.setBus(new Bus());
        ticket.setArrivalTime(new Date(System.currentTimeMillis()));
        ticket.setDepartmentTime(new Date(System.currentTimeMillis()- 100000));
        ticket.setDepartmentCity(new City());
        ticket.setArrivalCity(new City());
        ticket.setDriver(new User());

        ticketValidator.validate(ticket);
    }

    @Test(expected = TicketValidationException.class)
    @Parameters({"100,International,10"})
    public void InvalidTicketDepAfterArrival(int count, String isInternational, int distance) {
        Ticket ticket = new Ticket();
        ticket.setCount(count);
        ticket.setIsInternational(isInternational);
        ticket.setDistance(distance);
        ticket.setBus(new Bus());
        ticket.setArrivalTime(new Date(System.currentTimeMillis()));
        ticket.setDepartmentTime(new Date(System.currentTimeMillis() + 100000));
        ticket.setDepartmentCity(new City());
        ticket.setArrivalCity(new City());
        ticket.setDriver(new User());

        ticketValidator.validate(ticket);
    }

    @Test(expected = TicketValidationException.class)
    public void InvalidTicketDepAfterArrival() {
        ticketValidator.validate(null);
    }

}
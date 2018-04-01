package com.bsuir.buspark.controller;

import com.bsuir.buspark.bl.UserService;
import com.bsuir.buspark.bl.validator.TicketValidatorImpl;
import com.bsuir.buspark.bl.validator.Validator;
import com.bsuir.buspark.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/home/")
public class UserHomeController {

    @Autowired
    private UserService userService;

    private Validator ticketValidator = new TicketValidatorImpl();

    @RequestMapping(method = RequestMethod.POST, value = "/addTicketToUser")
    void addTicketToUser(int userId, int ticketId)
    {
        userService.addTicketToUser(userId, ticketId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getAllTickets")
    List<Ticket> getAllTickets(int userId)
    {
        return userService.getAllTickets(userId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/deleteTicket")
    void deleteTicket(int ticketId, int userId)
    {
        userService.deleteTicket(ticketId, userId);
    }
}


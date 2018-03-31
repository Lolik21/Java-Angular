package com.bsuir.buspark.controller;

import com.bsuir.buspark.bl.TicketService;
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
@RequestMapping("/tickets/")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    private Validator validator = new TicketValidatorImpl();

    @RequestMapping(method = RequestMethod.GET, value = "/")
    List<Ticket> getAllTickets() {
           return this.ticketService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    Ticket addNewTicket(Ticket ticket) {
        this.validator.validate(ticket);
        return this.ticketService.create(ticket);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    void deleteTicket (@PathVariable int id) {
        ticketService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    Ticket getTicketById(@PathVariable int id)
    {
        return ticketService.read(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}")
    Ticket updateTicket(@PathVariable int id, Ticket ticket){
        this.validator.validate(ticket);
        return ticketService.update(id,ticket);
    }

}

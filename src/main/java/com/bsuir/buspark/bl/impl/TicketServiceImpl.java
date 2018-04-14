package com.bsuir.buspark.bl.impl;

import com.bsuir.buspark.bl.TicketService;
import com.bsuir.buspark.bl.exception.notFound.BusNotFoundException;
import com.bsuir.buspark.dal.TicketRepository;
import com.bsuir.buspark.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket create(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket update(int oldTicketId, Ticket ticket) {
        Ticket selectedTicket = ticketRepository.findById(oldTicketId).orElseThrow(
                () -> new BusNotFoundException("Cannot update ticket with requested ID"));
        selectedTicket.setArrivalCity(ticket.getArrivalCity());
        selectedTicket.setArrivalTime(ticket.getArrivalTime());
        selectedTicket.setBus(ticket.getBus());
        selectedTicket.setCount(ticket.getCount());
        selectedTicket.setDepartmentCity(ticket.getDepartmentCity());
        selectedTicket.setDepartmentTime(ticket.getDepartmentTime());
        selectedTicket.setDistance(ticket.getDistance());
        selectedTicket.setDriver(ticket.getDriver());
        selectedTicket.setIsInternational(ticket.getIsInternational());
        return ticketRepository.save(selectedTicket);
    }

    @Override
    public Ticket read(int ticketId) {
        return ticketRepository.findById(ticketId).orElseThrow(
                () -> new BusNotFoundException("Cannot find ticket with requested ID"));
    }

    @Override
    public void delete(int ticketId) {
        ticketRepository.findById(ticketId).orElseThrow(
                () -> new BusNotFoundException("Cannot delete ticket with requested ID"));
        ticketRepository.deleteById(ticketId);
    }

    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> getAllInternational() {
        return ticketRepository.findByIsInternational("International");
    }

    @Override
    public List<Ticket> getAllNotInternational() {
        return ticketRepository.findByIsInternational("notInternational");
    }

}

package com.bsuir.buspark.bl;

import com.bsuir.buspark.entity.Ticket;
import java.util.List;

public interface TicketService {
    Ticket create(Ticket ticket);
    Ticket update(int oldTicketId, Ticket ticket);
    Ticket read(int ticketId);
    void delete(int ticketId);
    List<Ticket> getAll();
}

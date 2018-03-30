package com.bsuir.buspark.dal;

import com.bsuir.buspark.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}

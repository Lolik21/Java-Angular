package com.bsuir.buspark.dal;

import com.bsuir.buspark.entity.Bus;
import com.bsuir.buspark.entity.City;
import com.bsuir.buspark.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByBus(Bus bus);
    List<Ticket> findByDepartmentCity(City city);
    List<Ticket> findByArrivalCity(City city);
}

package com.bsuir.buspark.bl.impl;


import com.bsuir.buspark.bl.BusService;
import com.bsuir.buspark.bl.exception.other.BusIsNowInUseException;
import com.bsuir.buspark.bl.exception.notFound.BusNotFoundException;
import com.bsuir.buspark.dal.BusRepository;
import com.bsuir.buspark.dal.TicketRepository;
import com.bsuir.buspark.entity.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusServiceImpl implements BusService {
    @Autowired
    private BusRepository busRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Bus create(Bus bus) {
        return busRepository.save(bus);
    }

    @Override
    public Bus update(int oldBusId, Bus bus) {
        Bus selectedBus = busRepository.findById(oldBusId).orElseThrow(
                () -> new BusNotFoundException("Cannot update bus with requested Id")
        );
        selectedBus.setCapacity(bus.getCapacity());
        selectedBus.setModel(bus.getModel());
        selectedBus.setGovNumber(bus.getGovNumber());
        return busRepository.save(selectedBus);
    }

    @Override
    public Bus read(int busId) {
        return busRepository.findById(busId).orElseThrow(
                () -> new BusNotFoundException("Cannot find bus with requested Id")
        );
    }

    @Override
    public void delete(int busId) {
        Bus bus = busRepository.findById(busId).orElseThrow(
                () -> new BusNotFoundException("Cannot delete bus with requested Id")
        );

        if (ticketRepository.findByBus(bus).isEmpty()){
            busRepository.deleteById(busId);
        }
        else
        {
            throw new BusIsNowInUseException("Bus is now in use");
        }
    }

    @Override
    public List<Bus> getAll() {
        return busRepository.findAll();
    }
}

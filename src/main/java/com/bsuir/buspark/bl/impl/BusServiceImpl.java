package com.bsuir.buspark.bl.impl;


import com.bsuir.buspark.bl.BusService;
import com.bsuir.buspark.dal.BusRepository;
import com.bsuir.buspark.entity.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusServiceImpl implements BusService {
    @Autowired
    private BusRepository busRepository;

    @Override
    public Bus create(Bus bus) {
        return busRepository.save(bus);
    }

    @Override
    public Bus update(int oldBusId, Bus bus) {
        Bus selectedBus = busRepository.findById(Long.valueOf(oldBusId)).get();
        selectedBus.setCapacity(bus.getCapacity());
        selectedBus.setModel(bus.getModel());
        selectedBus.setGovNumber(bus.getGovNumber());
        return busRepository.save(selectedBus);
    }

    @Override
    public Bus read(int busId) {
        return busRepository.findById(Long.valueOf(busId)).get();
    }

    @Override
    public void delete(int busId) {
        busRepository.deleteById(Long.valueOf(busId));
    }

    @Override
    public List<Bus> getAll() {
        return busRepository.findAll();
    }
}

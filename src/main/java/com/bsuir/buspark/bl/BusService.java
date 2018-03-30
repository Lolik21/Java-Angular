package com.bsuir.buspark.bl;

import com.bsuir.buspark.entity.Bus;

import java.util.List;

public interface BusService {
    Bus create(Bus bus);
    Bus update(int oldBusId, Bus bus);
    Bus read(int busId);
    void delete(int busId);
    List<Bus> getAll();
}

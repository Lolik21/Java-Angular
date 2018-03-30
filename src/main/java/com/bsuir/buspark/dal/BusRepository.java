package com.bsuir.buspark.dal;

import com.bsuir.buspark.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BusRepository extends JpaRepository<Bus, Long> {
}

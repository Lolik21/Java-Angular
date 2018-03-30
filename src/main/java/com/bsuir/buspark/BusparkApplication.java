package com.bsuir.buspark;

import com.bsuir.buspark.bl.BusService;
import com.bsuir.buspark.bl.CityService;
import com.bsuir.buspark.bl.RoleService;
import com.bsuir.buspark.bl.TicketService;
import com.bsuir.buspark.entity.Bus;
import com.bsuir.buspark.entity.City;
import com.bsuir.buspark.entity.Ticket;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class BusparkApplication {

	@Bean
	CommandLineRunner init(TicketService ticketService,
						   RoleService roleService,
						   CityService cityService,
						   BusService busService) {
		return (evt) -> {
			City cityMinsk = new City();
			cityMinsk.setName("Minsk");
			cityMinsk.setDistance(100);
			City cityPolost = new City();
			cityPolost.setName("Polotsk");
			cityPolost.setDistance(250);
			City cityGrodno = new City();
			cityGrodno.setName("Grodno");
			cityGrodno.setDistance(320);
			cityService.create(cityMinsk);
			cityService.create(cityPolost);
			cityService.create(cityGrodno);


			Bus bus = new Bus();
			bus.setCapacity(100);
			bus.setGovNumber("324FA234");
			bus.setModel("Bus 32");
			Bus bus1 = new Bus();
			bus1.setCapacity(100);
			bus1.setGovNumber("3GSFA24");
			bus1.setModel("Bus 33");
			Bus bus2 = new Bus();
			bus2.setCapacity(100);
			bus2.setGovNumber("3ASFA234");
			bus2.setModel("Bus 34");
			busService.create(bus);
			busService.create(bus1);
			busService.create(bus2);


			Ticket ticket = new Ticket();
			ticket.setArrivalCity(cityMinsk);
			ticket.setDepartmentCity(cityGrodno);
			ticket.setArrivalTime(new Date(System.currentTimeMillis()));
			ticket.setDepartmentTime(new Date(System.currentTimeMillis()));
			ticket.setBus(bus);
			ticket.setCount(100);
			ticket.setDistance(1000);
			ticket.setIsInternational("international");
			Ticket ticket1 = new Ticket();
			ticket1.setArrivalCity(cityMinsk);
			ticket1.setDepartmentCity(cityGrodno);
			ticket1.setArrivalTime(new Date(System.currentTimeMillis()));
			ticket1.setDepartmentTime(new Date(System.currentTimeMillis()));
			ticket1.setBus(bus);
			ticket1.setCount(100);
			ticket1.setDistance(1000);
			ticket1.setIsInternational("international");
			Ticket ticket2 = new Ticket();
			ticket2.setArrivalCity(cityMinsk);
			ticket2.setDepartmentCity(cityGrodno);
			ticket2.setArrivalTime(new Date(System.currentTimeMillis()));
			ticket2.setDepartmentTime(new Date(System.currentTimeMillis()));
			ticket2.setBus(bus);
			ticket2.setCount(100);
			ticket2.setDistance(1000);
			ticket2.setIsInternational("international");
			ticketService.create(ticket);
			ticketService.create(ticket1);
			ticketService.create(ticket2);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(BusparkApplication.class, args);
	}
}

package com.bsuir.buspark;

import com.bsuir.buspark.bl.*;
import com.bsuir.buspark.bl.impl.user.UserDetailsServiceImpl;
import com.bsuir.buspark.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.HashSet;

@SpringBootApplication
public class BusparkApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	CommandLineRunner init(TicketService ticketService,
						   RoleService roleService,
						   CityService cityService,
						   BusService busService,
						   UserService userService) {
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

			Role userRole = new Role();
			userRole.setName("USER");
			userRole = roleService.create(userRole);

			Role userRole2 = new Role();
			userRole2.setName("USER");
			userRole2 = roleService.create(userRole2);

			Role adminRole = new Role();
			adminRole.setName("ADMIN");
			adminRole = roleService.create(adminRole);

			Role driverRole = new Role();
			driverRole.setName("DRIVER");
			driverRole = roleService.create(driverRole);

			User adminUser = new User();
			adminUser.setName("Ilya");
			adminUser.setSurname("Kremniou");
			adminUser.setUsername("SuperAdmin");
			adminUser.setPassword("123123123");
			HashSet<Role> adminRoleSet = new HashSet<>();
			adminRoleSet.add(adminRole);
			adminRoleSet.add(userRole);
			adminUser.setRoles(adminRoleSet);
			userService.save(adminUser);

			User simpleUser = new User();
			simpleUser.setName("Kostya");
			simpleUser.setSurname("Shutko");
			simpleUser.setUsername("SuperAdmin2");
			simpleUser.setPassword("123123123");
			HashSet<Role> driverRoleSet = new HashSet<>();
			driverRoleSet.add(driverRole);
			driverRoleSet.add(userRole2);
			simpleUser.setRoles(driverRoleSet);
			simpleUser = userService.save(simpleUser);

			Ticket ticket = new Ticket();
			ticket.setArrivalCity(cityMinsk);
			ticket.setDepartmentCity(cityGrodno);
			ticket.setArrivalTime(new Date(System.currentTimeMillis()));
			ticket.setDepartmentTime(new Date(System.currentTimeMillis()));
			ticket.setBus(bus);
			ticket.setCount(100);
			ticket.setDistance(1000);
			ticket.setIsInternational("International");
			ticket.setDriver(simpleUser);
			Ticket ticket1 = new Ticket();
			ticket1.setArrivalCity(cityMinsk);
			ticket1.setDepartmentCity(cityGrodno);
			ticket1.setArrivalTime(new Date(System.currentTimeMillis()));
			ticket1.setDepartmentTime(new Date(System.currentTimeMillis()));
			ticket1.setBus(bus);
			ticket1.setCount(100);
			ticket1.setDistance(1000);
			ticket1.setDriver(simpleUser);
			ticket1.setIsInternational("International");
			Ticket ticket2 = new Ticket();
			ticket2.setArrivalCity(cityMinsk);
			ticket2.setDepartmentCity(cityGrodno);
			ticket2.setArrivalTime(new Date(System.currentTimeMillis()));
			ticket2.setDepartmentTime(new Date(System.currentTimeMillis()));
			ticket2.setBus(bus);
			ticket2.setCount(100);
			ticket2.setDistance(1000);
			ticket2.setDriver(simpleUser);
			ticket2.setIsInternational("notInternational");
			ticketService.create(ticket);
			ticketService.create(ticket1);
			ticketService.create(ticket2);

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(BusparkApplication.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder authenticationManagerBuilder) throws  Exception {
		authenticationManagerBuilder.userDetailsService(new UserDetailsServiceImpl()).passwordEncoder(passwordEncoder);
	}

}

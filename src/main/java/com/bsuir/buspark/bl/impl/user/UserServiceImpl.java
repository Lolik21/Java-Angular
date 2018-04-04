package com.bsuir.buspark.bl.impl.user;

import com.bsuir.buspark.bl.UserService;
import com.bsuir.buspark.bl.exception.notFound.RoleNotFoundException;
import com.bsuir.buspark.bl.exception.notFound.TicketNotFoundException;
import com.bsuir.buspark.bl.exception.other.CityNowInUseException;
import com.bsuir.buspark.bl.exception.other.TicketAlreadyExistsException;
import com.bsuir.buspark.dal.RoleRepository;
import com.bsuir.buspark.dal.TicketRepository;
import com.bsuir.buspark.dal.UserRepository;
import com.bsuir.buspark.entity.Role;
import com.bsuir.buspark.entity.Ticket;
import com.bsuir.buspark.entity.User;
import com.bsuir.buspark.entity.helpers.UserHelpers;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return repo.findByUsername(username);
    }

    @Override
    public List<User> getDrivers() {
        List<User> users = repo.findAll();
        List<User> drivers = new ArrayList<>();
        for(User user : users)
        {
            Boolean Add = false;
            for (Role role : user.getRoles())
            {
                if (role.getName() == "DRIVER")
                {
                    Add = true;
                }
            }
            if (Add == true)
            {
                drivers.add(user);
            }
        }
        return drivers;
    }

    @Override
    public User addRoleToUser(int userId, Role role) {
        User user = repo.findById(userId).orElseThrow(
                () -> new CityNowInUseException("Cannot find user with requested Id"));

        for(Role role1 : user.getRoles())
        {
            if (role1.getName() == role.getName())
            {
                throw new CityNowInUseException("Role is already assigned to user");
            }
        }

        if (roleRepository.findByName(role.getName()).isEmpty())
        {
            throw new RoleNotFoundException("Role doesn't exists");
        }

        Role role1 = roleRepository.save(role);

        if (user.getRoles() == null)
        {
            HashSet<Role> roleSet = new HashSet<>();
            roleSet.add(role1);
            user.setRoles(roleSet);
        }
        else
        {
            Set<Role> roleSet = user.getRoles();
            roleSet.add(role1);
            user.setRoles(roleSet);
        }
        return repo.save(user);
    }

    @Override
    public void addTicketToUser(int userId, int ticketId) {
        User user = repo.findById(userId).orElseThrow(
                () -> new CityNowInUseException("Cannot find user with requested Id"));

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(
                () -> new TicketNotFoundException("Cannot find ticket with requested Id"));


        if (ticket.getCount() != 0)
        {
            UserHelpers userHelpers = new UserHelpers();

            List<Integer> ticketsInts = userHelpers.getTicketsInts(user.getTickets());

            if (ticketsInts.contains(ticketId))
            {
                throw new TicketAlreadyExistsException("Ticket already exists in user list");
            }
            ticketsInts.add(ticketId);
            user.setTickets(userHelpers.getTicketStr(ticketsInts));

            ticket.setCount(ticket.getCount() - 1);

            ticketRepository.save(ticket);

            repo.save(user);
        }
        else
        {
            throw new TicketNotFoundException("There are no more tickets");
        }

    }

    @Override
    public List<Ticket> getAllTickets(int userId) {
        User user = repo.findById(userId).orElseThrow(
                () -> new CityNowInUseException("Cannot find user with requested Id"));
        UserHelpers userHelpers = new UserHelpers();
        List<Integer> ticketsInts = userHelpers.getTicketsInts(user.getTickets());
        List<Ticket> tickets = new ArrayList<Ticket>();
        List<Integer> toRemove = new ArrayList<>();
        for (Integer ticket : ticketsInts)
        {
            Ticket addingTicket = ticketRepository.findById(ticket).orElse(null);
            if (addingTicket == null) {
                toRemove.add(ticket);
            }
            else
            {
                tickets.add(addingTicket);
            }
        }

        for(Integer i : toRemove)
        {
            ticketsInts.remove(i);
        }

        user.setTickets(userHelpers.getTicketStr(ticketsInts));
        repo.save(user);
        return tickets;
    }

    @Override
    public void deleteTicket(int ticketId, int userId) {
        User user = repo.findById(userId).orElseThrow(
                () -> new CityNowInUseException("Cannot find user with requested Id"));

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(
                () -> new TicketNotFoundException("Cannot find ticket with requested Id"));

        UserHelpers userHelpers = new UserHelpers();
        List<Integer> integerList = userHelpers.getTicketsInts(user.getTickets());
        if (!integerList.contains(ticketId))
        {
            throw new TicketNotFoundException("There are no such ticket in user list");
        }
        Integer toRemove = ticketId;
        integerList.remove(toRemove);
        user.setTickets(userHelpers.getTicketStr(integerList));

        ticket.setCount(ticket.getCount() + 1);

        ticketRepository.save(ticket);
        repo.save(user);
    }

    @Override
    public User update(int oldUserId, User user) {
        User oldUser = repo.findById(oldUserId).orElseThrow(
                () -> new CityNowInUseException("Cannot find user with requested Id"));
        oldUser.setPassword(user.getPassword());
        oldUser.setName(user.getName());
        oldUser.setSurname(user.getSurname());
        oldUser.setUsername(user.getUsername());
        oldUser.setRoles(user.getRoles());
        oldUser.setTickets(user.getTickets());
        return this.save(oldUser);
    }

    @Override
    public User read(int userId) {
        return repo.findById(userId).orElseThrow(
                () -> new CityNowInUseException("Cannot find user with requested Id"));
    }

    @Override
    public void delete(int userId) {
        repo.findById(userId).orElseThrow(
                () -> new CityNowInUseException("Cannot find user with requested Id"));
        repo.deleteById(userId);
    }

    @Override
    public List<User> getAll() {
        return repo.findAll();
    }

}
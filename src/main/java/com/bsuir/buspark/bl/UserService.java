package com.bsuir.buspark.bl;

import com.bsuir.buspark.entity.Role;
import com.bsuir.buspark.entity.Ticket;
import com.bsuir.buspark.entity.User;

import java.util.List;

public interface UserService {
    User save(User user);
    User findByUsername(String username);
    List<User> getDrivers();
    User addRoleToUser(int userId, Role role);
    void addTicketToUser(int userId, int ticketId);
    List<Ticket> getAllTickets(int userId);
    void deleteTicket(int ticketId, int userId);

    User update(int oldUserId, User user);
    User read(int userId);
    void delete(int userId);
    List<User> getAll();
}
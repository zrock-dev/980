package com.fake_orgasm.flights_management.services;

import com.fake_orgasm.flights_management.models.Category;
import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.models.Ticket;
import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.services.IUserManagement;
import com.fake_orgasm.users_management.services.exceptions.IncompleteUserException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FlightService {

    private IUserManagement userManagement;
    private IFlightManagement flightManagement;
    private ITicketManagement ticketManagement;

    public FlightService(
            IUserManagement userManagement,
            IFlightManagement flightManagement,
            ITicketManagement ticketManagement
    ) {
        this.userManagement = userManagement;
        this.flightManagement = flightManagement;
        this.ticketManagement = ticketManagement;
    }

    private User findUser(User user) {
        List<User> usersFound = userManagement.search(user.getFullName());
        if (usersFound.isEmpty()) {
            try {
                userManagement.create(user);
            } catch (IncompleteUserException e) {
                throw new RuntimeException(e);
            }
        } else {
            user = usersFound.get(0);
        }

        return user;
    }

    public boolean book(User user, String flightId, Category category) {
        boolean wasReserved = false;
        try {
            Flight flightFound = flightManagement.search(flightId);

            if (flightFound != null && flightFound.isAvailable()) {
                User userFound = findUser(user);
                Ticket newTicket = new Ticket(UUID.randomUUID().toString(),
                        flightFound.getNextNumber(), category, userFound.getId(),
                        flightFound.getId());

                flightFound.addTicket(newTicket);
                userFound.addFlight(newTicket.getId());

                userManagement.update(userFound, userFound);
                flightManagement.update(flightId, flightFound);
                ticketManagement.create(newTicket);
                wasReserved = true;
            }
        } catch (IncompleteUserException e) {
            throw new RuntimeException(e);
        }

        return wasReserved;
    }
}
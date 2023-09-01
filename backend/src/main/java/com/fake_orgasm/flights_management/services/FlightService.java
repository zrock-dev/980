package com.fake_orgasm.flights_management.services;

import com.fake_orgasm.flights_management.models.Category;
import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.models.Ticket;
import com.fake_orgasm.flights_management.repository.FlightRepository;
import com.fake_orgasm.flights_management.repository.TicketRepository;
import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.services.IUserManagement;
import com.fake_orgasm.users_management.services.exceptions.IncompleteUserException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FlightService {

    private IUserManagement userManagement;
    private FlightRepository flightManagement;
    private TicketRepository ticketManagement;

    public FlightService(
            IUserManagement userManagement,
            FlightRepository flightManagement,
            TicketRepository ticketManagement
    ) {
        this.userManagement = userManagement;
        this.flightManagement = flightManagement;
        this.ticketManagement = ticketManagement;
    }

    private User findUser(User user) {
        List<User> usersFound = userManagement.search(user.getFirstName());
        if (usersFound.isEmpty()) {
            try {
                userManagement.create(user);
            } catch (IncompleteUserException e) {
                throw new RuntimeException(e);
            }
        } else {
            for (User userFound : usersFound) {
                if (user.equals(userFound))
                    user = userFound;
            }
        }

        return user;
    }

    public boolean bookFlight(User user, String flightId, Category category) {
        boolean wasBooked = false;
        try {
            Flight flightFound = flightManagement.search(flightId);

            if (flightFound != null && flightFound.isAvailable()) {
                User userFound = findUser(user);
                Ticket newTicket = new Ticket(UUID.randomUUID().toString(),
                        flightFound.getNextNumber(), category, userFound.getId(),
                        flightFound.getId());

                flightFound.addTicketId(newTicket.getId());
                userFound.addFlight(newTicket.getId());

                userManagement.update(userFound, userFound);
                flightManagement.update(flightId, flightFound);
                ticketManagement.create(newTicket);
                wasBooked = true;
            }
        } catch (IncompleteUserException e) {
            throw new RuntimeException(e);
        }

        return wasBooked;
    }
}
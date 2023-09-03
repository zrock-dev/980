package com.fake_orgasm.flights_management.services;

import com.fake_orgasm.flights_management.models.Category;
import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.models.Ticket;
import com.fake_orgasm.flights_management.repository.FlightRepository;
import com.fake_orgasm.flights_management.repository.TicketRepository;
import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.services.IUserManagement;
import com.fake_orgasm.users_management.services.exceptions.IncompleteUserException;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Getter
@Service
public class BookingService implements IBookingService {

    private User user;
    private Flight flight;
    private Ticket ticket;
    private IUserManagement userManagement;
    private FlightRepository flightRepository;
    private TicketRepository ticketRepository;

    public BookingService(
            IUserManagement userManagement,
            FlightRepository flightManagement,
            TicketRepository ticketManagement
    ) {
        this.userManagement = userManagement;
        this.flightRepository = flightManagement;
        this.ticketRepository = ticketManagement;
    }

    public BookingService() {
        this.flightRepository = new FlightRepository();
        this.ticketRepository = new TicketRepository();

        flightRepository.createTable();
        ticketRepository.createTable();
    }

    private User findUser(User user) {
        if (userManagement != null) {
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

        }

        return user;
    }

    @Override
    public boolean booking(User userForBook, String flightId, Category category) {
        boolean wasBooked = false;
        flight = flightRepository.search(flightId);

        if (flight != null && flight.isAvailable()) {
            user = findUser(userForBook);
            ticket = new Ticket(UUID.randomUUID().toString(),
                    flight.getNextNumber(), category, userForBook.getId(),
                    flight.getId());

            flight.addTicketId(ticket.getId());
            user.addFlight(ticket.getId());
            saveBooking();
            wasBooked = true;
        }

        return wasBooked;
    }

    public void saveBooking() {
        try {
            flightRepository.update(flight.getId(), flight);
            ticketRepository.create(ticket);
            if (userManagement != null)
                userManagement.update(user, user);
        } catch (IncompleteUserException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ticket> getFlightTickets(String flightId) {
        Flight flightFound = flightRepository.search(flightId);
        return getFlightTickets(flightFound);
    }

    @Override
    public List<Ticket> getFlightTickets(Flight flight) {
        List<Ticket> tickets = ticketRepository
                .search(flight.getTicketIds().split(","));
        flight.addTicket(tickets);
        tickets = flight.getTickets();

        return tickets;
    }
}
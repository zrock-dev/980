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

/**
 * This class implements the IBookingService interface and provides functionality for booking flight tickets,
 * retrieving flight tickets, and managing the booking process.
 */
@Getter
@Service
public class BookingService implements IBookingService {

    private User user;
    private Flight flight;
    private Ticket ticket;
    private IUserManagement userManagement;
    private FlightRepository flightRepository;
    private TicketRepository ticketRepository;

    /**
     * This method constructs a BookingService instance with dependencies on user management,
     * flight repository, and ticket repository.
     *
     * @param userManagement    The IUserManagement implementation for managing user-related
     *                          operations.
     * @param flightManagement  The FlightRepository for managing flight-related operations.
     * @param ticketManagement  The TicketRepository for managing ticket-related operations.
     */
    public BookingService(
            IUserManagement userManagement,
            FlightRepository flightManagement,
            TicketRepository ticketManagement
    ) {
        this.userManagement = userManagement;
        this.flightRepository = flightManagement;
        this.ticketRepository = ticketManagement;
    }

    /**
     * This method constructs a BookingService instance with default repositories
     * for flight and ticket management.
     */
    public BookingService() {
        this.flightRepository = new FlightRepository();
        this.ticketRepository = new TicketRepository();

        flightRepository.createTable();
        ticketRepository.createTable();
    }

    /**
     * This method finds an existing user or creates a new one if not found in the user
     * management system.
     *
     * @param user The User object representing the passenger.
     * @return The existing or newly created User object.
     */
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

    /**
     * This method books a flight ticket for a given user with a specified category.
     *
     * @param userForBook The User object representing the passenger.
     * @param flightId    The ID of the flight for which the ticket is booked.
     * @param category    The Category of the ticket (e.g., Economy, Business, VIP).
     * @return True if the booking was successful, otherwise false.
     */
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

    /**
     * This method saves the booking by updating the flight, creating the ticket,
     * and updating the user information if available.
     */
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

    /**
     * This method retrieves a list of flight tickets for a specific flight identified by its ID.
     *
     * @param flightId The ID of the flight for which to retrieve tickets.
     * @return A list of Ticket objects representing the flight tickets.
     */
    @Override
    public List<Ticket> getFlightTickets(String flightId) {
        Flight flightFound = flightRepository.search(flightId);
        return getFlightTickets(flightFound);
    }

    /**
     * This method retrieves a list of flight tickets for a specific flight.
     *
     * @param flight The Flight object for which to retrieve tickets.
     * @return A list of Ticket objects representing the flight tickets.
     */
    @Override
    public List<Ticket> getFlightTickets(Flight flight) {
        List<Ticket> tickets = ticketRepository
                .search(flight.getTicketIds().split(","));
        flight.addTicket(tickets);
        tickets = flight.getTickets();

        return tickets;
    }
}
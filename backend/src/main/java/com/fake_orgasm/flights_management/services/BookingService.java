package com.fake_orgasm.flights_management.services;

import com.fake_orgasm.flights_management.models.*;
import com.fake_orgasm.flights_management.repository.AirportRepository;
import com.fake_orgasm.flights_management.repository.FlightRepository;
import com.fake_orgasm.flights_management.repository.TicketRepository;
import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.services.IUserManagement;
import com.fake_orgasm.users_management.services.exceptions.IncompleteUserException;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This class implements the IBookingService interface and provides functionality for booking flight tickets,
 * retrieving flight tickets, and managing the booking process.
 */
@Getter
@Service
public class BookingService implements IBookingService {

    private IUserManagement userManagement;
    private FlightRepository flightRepository;
    private TicketRepository ticketRepository;
    private AirportRepository airportRepository;

    /**
     * This method constructs a BookingService instance with dependencies on user management,
     * flight repository, and ticket repository.
     *
     * @param userManagement   The IUserManagement implementation for managing user-related
     *                         operations.
     * @param flightManagement The FlightRepository for managing flight-related operations.
     * @param ticketManagement The TicketRepository for managing ticket-related operations.
     */
    public BookingService(
            IUserManagement userManagement,
            FlightRepository flightManagement,
            TicketRepository ticketManagement,
            AirportRepository airportRepository
    ) {
        this.userManagement = userManagement;
        this.flightRepository = flightManagement;
        this.ticketRepository = ticketManagement;
        this.airportRepository = airportRepository;
    }

    /**
     * This method constructs a BookingService instance with default repositories
     * for flight and ticket management.
     */
    public BookingService() {
        this.flightRepository = new FlightRepository();
        this.ticketRepository = new TicketRepository();
        this.airportRepository = new AirportRepository();

        airportRepository.createTable();
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
                return findUser(usersFound, user);
            }

        }

        return user;
    }

    /**
     * This method find a user within a list of users based on equality criteria.
     * <p>
     * This method searches for a user within a provided list of users by comparing
     * each user to the specified user based on equality criteria.
     *
     * @param users The list of users to search within.
     * @param user  The user to find within the list.
     * @return The matching user if found, or null if no matching user is found.
     */
    private User findUser(List<User> users, User user) {
        for (User userFound : users) {
            if (user.equals(userFound))
                return userFound;
        }

        return null;
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
        Flight flight;
        Ticket ticket;
        User user;
        flight = flightRepository.search(flightId);

        if (flight != null && flight.isAvailable()) {
            user = findUser(userForBook);
            ticket = new Ticket(UUID.randomUUID().toString(),
                    flight.getNextNumber(), category, userForBook.getId(),
                    flight.getId());

            if (!flight.getLastTicket().isEmpty()) {
                Ticket lastTicket = ticketRepository.search(flight.getLastTicket());
                lastTicket.setNextTicket(ticket.getId());
                ticket.setPreviousTicket(lastTicket.getId());
                ticketRepository.update(lastTicket.getId(), lastTicket);
            }

            flight.addTicketId(ticket.getId());
            user.addFlight(ticket.getId());
            saveBooking(flight, ticket, user);
            wasBooked = true;
        }

        return wasBooked;
    }

    /**
     * This method saves the booking by updating the flight, creating the ticket,
     * and updating the user information if available.
     */
    public void saveBooking(Flight flight, Ticket ticket, User user) {
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
    public List<Ticket> getFlightTickets(String flightId) {
        Flight flight = flightRepository.search(flightId);
        List<Ticket> tickets = ticketRepository
                .search(flight.getTicketIds().split(","));
        flight.addTicket(tickets);
        tickets = flight.getTickets();

        return tickets;
    }

    /**
     * Deletes a booking associated with a user and a specific ticket ID.
     * <p>
     * This method attempts to delete a booking made by the specified user for a
     * ticket identified by the provided ticket ID. If the deletion is successful,
     * it returns true; otherwise, it returns false.
     *
     * @param user     The user data associated with the booking.
     * @param ticketId The ID of the ticket to be deleted from the user's bookings.
     * @return true if the booking was successfully deleted, false otherwise.
     */
    @Override
    public boolean deleteBooking(User user, String ticketId) {
        if (userManagement != null) {
            List<User> usersFound = userManagement.search(user.getFirstName());
            user = findUser(usersFound, user);
        }

        Ticket ticket = ticketRepository.search(ticketId);
        return deleteBook(user, ticket);
    }

    /**
     * Edits the category of a booking associated with a ticket.
     * <p>
     * This method allows for the modification of the booking category (e.g., class)
     * associated with a ticket. It takes the ticket ID and the new category as
     * parameters and attempts to update the booking information. If the editing
     * is successful, it returns true; otherwise, it returns false.
     *
     * @param ticketId    The ID of the ticket associated with the booking to be edited.
     * @param newCategory The new category (e.g., class) for the booking.
     * @return true if the booking was successfully edited, false otherwise.
     */
    @Override
    public boolean editBooking(String ticketId, String newCategory) {
        Ticket ticket = ticketRepository.search(ticketId);
        if (ticket != null) {
            Category category = Category.getCategory(newCategory);
            ticket.setPriority(category);
            return ticketRepository.update(ticketId, ticket);
        }

        return false;
    }

    @Override
    public List<FlightJoined> getFlightsJoined(int page) {
        return flightRepository.findAllFlightsJoined();
    }

    @Override
    public FlightJoined getFlightJoined(String flightId) {
        return flightRepository.findFlightJoined(flightId);
    }

    @Override
    public List<TicketJoined> getUserTickets(int userId, int page) {
        return null;
    }

    /**
     * This method deletes a booking associated with a user and a specific ticket.
     * <p>
     * This method performs the deletion of a booking made by a user for a given ticket.
     * It updates references and information related to the user, flight, and tickets as needed.
     *
     * @param userFound The user associated with the booking.
     * @param ticket    The ticket to be deleted.
     * @return true if the booking was successfully deleted, false otherwise.
     */
    private boolean deleteBook(User userFound, Ticket ticket) {
        if (ticket != null && userFound != null) {
            try {
                String flightId = ticket.getFlightId();
                Flight flight = flightRepository.search(flightId);

                userFound.removeFlight(ticket.getId());
                flight.removeTicket(ticket.getId());

                updateTicketReferences(ticket);
                updateArrivalNumber(ticket);

                if (userManagement != null) {
                    userManagement.update(userFound, userFound);
                }
                flightRepository.update(flightId, flight);
                ticketRepository.delete(ticket.getId());
                return true;
            } catch (IncompleteUserException e) {
                throw new RuntimeException(e);
            }
        }

        return false;
    }

    /**
     * This method updates references between tickets after a ticket is deleted.
     * <p>
     * This method updates references between tickets when a ticket is deleted.
     * It ensures that the previous and next ticket references are correctly adjusted.
     *
     * @param ticket The ticket for which references need to be updated.
     */
    private void updateTicketReferences(Ticket ticket) {
        if (ticket.hasPrevious() && ticket.hasNext()) {
            Ticket previousTicket = ticketRepository.search(ticket.getPreviousTicket());
            Ticket nextTicket = ticketRepository.search(ticket.getNextTicket());
            previousTicket.setNextTicket(nextTicket.getId());
            nextTicket.setPreviousTicket(previousTicket.getId());
            ticketRepository.update(previousTicket.getId(), previousTicket);
            ticketRepository.update(nextTicket.getId(), nextTicket);
        } else if (!ticket.hasPrevious() && ticket.hasNext()) {
            Ticket nextTicket = ticketRepository.search(ticket.getNextTicket());
            nextTicket.setPreviousTicket("");
            ticketRepository.update(nextTicket.getId(), nextTicket);
        } else if (ticket.hasPrevious() && !ticket.hasNext()) {
            Flight flight = flightRepository.search(ticket.getFlightId());
            Ticket previousTicket = ticketRepository.search(ticket.getPreviousTicket());
            flight.setLastTicket(previousTicket.getId());
            previousTicket.setNextTicket("");
            ticketRepository.update(previousTicket.getId(), previousTicket);
            flightRepository.update(flight.getId(), flight);
        }
    }

    /**
     * This method updates arrival numbers for subsequent tickets after a ticket is deleted.
     * <p>
     * This method updates the arrival numbers of subsequent tickets when a ticket is deleted.
     * It ensures that the order and numbering of tickets are maintained.
     *
     * @param ticket The ticket for which arrival numbers need to be updated.
     */
    private void updateArrivalNumber(Ticket ticket) {
        while (ticket.hasNext()) {
            ticket = ticketRepository.search(ticket.getNextTicket());
            ticket.setNumber(ticket.getNumber() - 1);
            ticketRepository.update(ticket.getId(), ticket);
        }
    }
}
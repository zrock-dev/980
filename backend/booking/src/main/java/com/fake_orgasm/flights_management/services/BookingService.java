package com.fake_orgasm.flights_management.services;

import com.fake_orgasm.flights_management.exceptions.UserNotFoundException;
import com.fake_orgasm.flights_management.models.Category;
import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.models.FlightJoined;
import com.fake_orgasm.flights_management.models.Ticket;
import com.fake_orgasm.flights_management.repository.*;
import com.fake_orgasm.users_management.models.User;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * This class implements the IBookingService interface and provides functionality
 * for booking flight tickets, retrieving flight tickets, and managing the booking process.
 */
@Getter
@Service
public class BookingService implements IBookingService {

    private final FlightRepository flightRepository;
    private final TicketRepository ticketRepository;
    private final AirportRepository airportRepository;
    private RestClient restClient;
    private User user;
    private Ticket ticket;


    /**
     * This method constructs a BookingService instance with dependencies on user management,
     * flight repository, and ticket repository.
     *
     * @param flightManagement  The FlightRepository for managing flight-related operations.
     * @param ticketManagement  The TicketRepository for managing ticket-related operations.
     * @param airportRepository he AirportRepository for managing airport-related operations.
     */
    public BookingService(
            RestClient restClient,
            FlightRepository flightManagement,
            TicketRepository ticketManagement,
            AirportRepository airportRepository) {
        this.restClient = restClient;
        this.flightRepository = flightManagement;
        this.ticketRepository = ticketManagement;
        this.airportRepository = airportRepository;
    }

    /**
     * This method constructs a BookingService instance with default repositories
     * for flight and ticket management.
     */
    public BookingService() {
        this.restClient = new RestClient(new RestTemplate());
        this.flightRepository = new FlightRepository();
        this.ticketRepository = new TicketRepository();
        this.airportRepository = new AirportRepository();

        airportRepository.createTable();
        flightRepository.createTable();
        ticketRepository.createTable();
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
        Flight flight = flightRepository.search(flightId);

        if (flight != null && flight.isAvailable()) {
            user = restClient.findUser(userForBook);
            ticket = new Ticket(
                    UUID.randomUUID().toString(),
                    flight.getNextNumber(),
                    category,
                    userForBook.getId(),
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
     *
     * @param flight is the flight that will be saved on the database.
     * @param ticket is the ticket that will be saved on the database.
     * @param user   is the user that will be saved on the database.
     */
    public void saveBooking(Flight flight, Ticket ticket, User user) {
        flightRepository.update(flight.getId(), flight);
        ticketRepository.create(ticket);
        restClient.updateUserData(user, user);

    }

    /**
     * This method deletes a booking associated with a user and a specific ticket ID.
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
        user = restClient.findUser(user);

        Ticket ticket = ticketRepository.search(ticketId);
        return deleteBook(user, ticket);
    }

    /**
     * This method edits the category of a booking associated with a ticket.
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

    /**
     * This method retrieves a list of tickets associated with a specific flight.
     * <p>
     * This method fetches a list of tickets that are associated with the specified flight ID.
     *
     * @param flightId The unique identifier of the flight for which to retrieve tickets.
     * @return A list of Ticket objects representing tickets associated with the specified flight.
     * An empty list is returned if no tickets are found for the given flight.
     */
    @Override
    public List<Ticket> getFlightTickets(String flightId) {
        Flight flight = flightRepository.search(flightId);
        List<Ticket> tickets = ticketRepository.search(flight.getTicketIds().split(","));
        flight.addTicket(tickets);
        tickets = flight.getTickets();

        return tickets;
    }

    /**
     * This method retrieves a paginated list of joined flight information.
     * <p>
     * This method fetches flight details, including associated source and
     * destination airports, for a specified page.
     *
     * @param page The page number of the results to retrieve.
     * @return A list of FlightJoined objects containing flight details
     * and associated airports for the specified page.
     */
    @Override
    public Pagination getFlightsJoined(int page) {
        return flightRepository.findAllFlightsJoined(page+1);
    }

    /**
     * This method retrieves detailed information about a specific flight,
     * including source and destination airports.
     * <p>
     * This method fetches detailed information about a flight,
     * including its source and destination airports, based on the provided flight ID.
     *
     * @param flightId The unique identifier of the flight to retrieve.
     * @return A FlightJoined object containing detailed flight information and associated airports.
     */
    @Override
    public FlightJoined getFlightJoined(String flightId) {
        FlightJoined flightJoined = flightRepository.findFlightJoined(flightId);
        List<Ticket> tickets = getFlightTickets(flightId);
        flightJoined.setTickets(tickets);
        return flightJoined;
    }


    /**
     * This method retrieves a paginated list of tickets associated with a specific user.
     * <p>
     * This method retrieves a paginated list of tickets associated with a specified user ID.
     * It includes ticket details, such as the ticket category and arrival information.
     *
     * @param userId The ID of the user for whom to retrieve tickets.
     * @param page   The page number of the results to retrieve.
     * @return A list of TicketJoined objects containing ticket details
     * and associated flight and airport information for the specified user and page.
     */
    @Override
    public Pagination getUserTickets(int userId, int page) {
        return ticketRepository.findAllTicketsJoined(userId, page +1);
    }

    /**
     * This method deletes all tickets associated with a specific user.
     * <p>
     * This method removes all tickets that are associated with
     * the specified user from the database.
     *
     * @param user the user to found and delete their tickets.
     * @return True if the deletion was successful, false otherwise
     * (e.g., if no tickets were found for the user).
     */
    @Override
    public boolean deleteAllUserTickets(User user) {
        boolean wereRemoved = false;
        user = restClient.findUser(user);
        if (user == null) {
            throw new UserNotFoundException("Non existent user");
        }

        List<String> tickets = user.getFlights();
        for (String ticket : tickets) {
            wereRemoved = deleteBooking(user, ticket);
        }
        ticketRepository.deleteAllUserTickets(user.getId());

        return wereRemoved;
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
            String flightId = ticket.getFlightId();
            Flight flight = flightRepository.search(flightId);

            userFound.removeFlight(ticket.getId());
            flight.removeTicket(ticket.getId());

            updateTicketReferences(ticket);
            updateArrivalNumber(ticket);

            restClient.updateUserData(userFound, userFound);

            ticketRepository.delete(ticket.getId());
            return true;
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
        Flight flight = flightRepository.search(ticket.getFlightId());

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
            Ticket previousTicket = ticketRepository.search(ticket.getPreviousTicket());
            flight.setLastTicket(ticket.getPreviousTicket());
            flightRepository.update(flight.getId(), flight);
            previousTicket.setNextTicket("");
            ticketRepository.update(previousTicket.getId(), previousTicket);
        }

        flight = flightRepository.search(ticket.getFlightId());
        flight.removeTicket(ticket.getId());
        flightRepository.update(flight.getId(), flight);
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

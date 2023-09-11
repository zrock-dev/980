package com.fake_orgasm.flights_management.services;

import com.fake_orgasm.flights_management.exceptions.FlightCapacityException;
import com.fake_orgasm.flights_management.models.Category;
import com.fake_orgasm.flights_management.models.FlightJoined;
import com.fake_orgasm.flights_management.models.Ticket;
import com.fake_orgasm.flights_management.models.TicketJoined;
import com.fake_orgasm.flights_management.repository.Page;
import com.fake_orgasm.users_management.models.User;
import java.util.List;

/**
 * This interface defines the contract for a booking service that manages flight reservations.
 * It includes methods for making bookings, as well as retrieving flight tickets.
 */
public interface IBookingService {

    /**
     * This method books a flight ticket for a given user with a specified category.
     *
     * @param user     The User object representing the passenger.
     * @param flightId The ID of the flight for which the ticket is booked.
     * @param category The Category of the ticket (e.g., Economy, Business, VIP).
     * @return True if the booking was successful, otherwise false.
     */
    boolean booking(User user, String flightId, Category category);

    /**
     * This method deletes a booking associated with a user and a specific ticket ID.
     * <p>
     * This method attempts to delete a booking made by the specified user for a
     * ticket identified by the provided ticket ID. If the deletion is successful,
     * it returns true; otherwise, it returns false.
     *
     * @param userData The user data associated with the booking.
     * @param ticketId The ID of the ticket to be deleted from the user's bookings.
     * @return true if the booking was successfully deleted, false otherwise.
     */
    boolean deleteBooking(User userData, String ticketId);

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
    boolean editBooking(String ticketId, String newCategory);

    /**
     * This method retrieves a list of tickets associated with a specific flight.
     * <p>
     * This method fetches a list of tickets that are associated with the specified flight ID.
     *
     * @param flightId The unique identifier of the flight for which to retrieve tickets.
     * @return A list of Ticket objects representing tickets associated with the specified flight.
     * An empty list is returned if no tickets are found for the given flight.
     */
    List<Ticket> getFlightTickets(String flightId);

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
    Page<FlightJoined> getFlightsJoined(int page);

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
    FlightJoined getFlightJoined(String flightId) throws FlightCapacityException;

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
    Page<TicketJoined> getUserTickets(int userId, int page);

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
    boolean deleteAllUserTickets(User user);
}

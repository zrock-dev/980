package com.fake_orgasm.flights_management.services;

import com.fake_orgasm.flights_management.models.Category;
import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.models.Ticket;
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
    public boolean booking(User user, String flightId, Category category);

    /**
     * This method retrieves a list of flight tickets for a specific flight identified by its ID.
     *
     * @param flightId The ID of the flight for which to retrieve tickets.
     * @return A list of Ticket objects representing the flight tickets.
     */
    public List<Ticket> getFlightTickets(String flightId);

    /**
     * This method retrieves a list of flight tickets for a specific flight.
     *
     * @param flight The Flight object for which to retrieve tickets.
     * @return A list of Ticket objects representing the flight tickets.
     */
    public List<Ticket> getFlightTickets(Flight flight);

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
    public boolean deleteBook(User userData, String ticketId);

}
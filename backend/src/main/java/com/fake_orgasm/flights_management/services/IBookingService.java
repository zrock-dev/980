package com.fake_orgasm.flights_management.services;

import com.fake_orgasm.flights_management.exceptions.FlightCapacityException;
import com.fake_orgasm.flights_management.models.Category;
import com.fake_orgasm.flights_management.models.FlightJoined;
import com.fake_orgasm.flights_management.models.TicketJoined;
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
    public boolean deleteBooking(User userData, String ticketId);

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
    public boolean editBooking(String ticketId, String newCategory);

    public List<FlightJoined> getFlightsJoined(int page);

    public FlightJoined getFlightJoined(String flightId) throws FlightCapacityException;

    List<TicketJoined> getUserTickets(int userId, int page);
}
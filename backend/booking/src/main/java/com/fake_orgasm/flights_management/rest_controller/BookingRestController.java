package com.fake_orgasm.flights_management.rest_controller;

import static com.fake_orgasm.flights_management.rest_controller.utils.RestUtil.buildResponse;

import com.fake_orgasm.flights_management.exceptions.FlightCapacityException;
import com.fake_orgasm.flights_management.models.FlightJoined;
import com.fake_orgasm.flights_management.models.Ticket;
import com.fake_orgasm.flights_management.repository.Page;
import com.fake_orgasm.flights_management.rest_controller.records.BookingRequest;
import com.fake_orgasm.flights_management.services.IBookingService;
import com.fake_orgasm.users_management.models.User;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The REST controller for managing bookings.
 * <p>
 * This class provides endpoints for creating, retrieving, updating, and deleting bookings.
 * It handles HTTP requests related to bookings and delegates the operations to the booking service.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api/booking")
public class BookingRestController {
    private final IBookingService bookingService;

    /**
     * This is a constructor method that initializes the booking service.
     *
     * @param bookingService
     */
    public BookingRestController(IBookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Book a ticket using the given booking request.
     *
     * @param bookingRequest the booking request containing user, flight id, and category
     * @return a ResponseEntity with the result of the booking attempt
     */
    @PostMapping("")
    public ResponseEntity<?> bookTicket(@RequestBody BookingRequest bookingRequest) {
        boolean wasBooked =
                bookingService.booking(bookingRequest.user(), bookingRequest.flightId(), bookingRequest.category());
        if (wasBooked) {
            return buildResponse("Booking successful.", HttpStatus.OK);
        } else {
            return buildResponse("Booking failed.", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes a booking based on the ticket ID and user information.
     *
     * @param ticketId the ID of the booking ticket
     * @param userId   the ID of the user
     * @param fn       the first name of the user
     * @param sn       the last name of the user
     * @param lfn      the first name of the user
     * @param lsn      the last name of the user
     * @return a ResponseEntity indicating the success of the deletion
     */
    @DeleteMapping("/{ticketId}/{userId}")
    public ResponseEntity<?> deleteBooking(
            @PathVariable String ticketId,
            @PathVariable int userId,
            @RequestParam String fn,
            @RequestParam String sn,
            @RequestParam String lfn,
            @RequestParam String lsn) {
        User user = new User(userId, fn, sn, lfn, lsn);
        boolean wasDeleted = bookingService.deleteBooking(user, ticketId);
        if (wasDeleted) {
            return buildResponse("Booking deleted.", HttpStatus.OK);
        } else {
            return buildResponse("Booking failed.", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Edit a booking.
     *
     * @param ticketId    the ID of the ticket to edit
     * @param newCategory the new category for the booking
     * @return a ResponseEntity with a message indicating the booking was edited
     */
    @PutMapping("/{ticketId}")
    public ResponseEntity<?> editBooking(@PathVariable String ticketId, @RequestBody String newCategory) {
        boolean wasEdited = bookingService.editBooking(ticketId, newCategory);
        if (wasEdited) {
            return buildResponse("Booking edited.", HttpStatus.OK);
        } else {
            return buildResponse("The ticket id was not found.", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves the tickets for a specific flight.
     *
     * @param flightId the ID of the flight
     * @return a ResponseEntity containing a list of tickets for the flight
     */
    @GetMapping("/tickets/{flightId}")
    public ResponseEntity<?> getFlightsTickets(@PathVariable String flightId) {
        List<Ticket> tickets = bookingService.getFlightTickets(flightId);
        return ResponseEntity.ok(tickets);
    }

    /**
     * Retrieves flights by page.
     *
     * @param page the page number to retrieve flights from
     * @return a ResponseEntity with the message "Flights retrieved."
     */
    @GetMapping("/flights")
    public ResponseEntity<?> getFlightsByPage(@RequestParam int page) {
        Page flightJoined = bookingService.getFlightsJoined(page);
        return ResponseEntity.ok(flightJoined);
    }

    /**
     * Retrieves a flight by its ID.
     *
     * @param flightId the ID of the flight
     * @return a ResponseEntity object containing the retrieved flight or an error message
     */
    @GetMapping("/flights/{flightId}")
    public ResponseEntity<?> getFlightById(@PathVariable String flightId) {
        FlightJoined flightJoined;
        try {
            flightJoined = bookingService.getFlightJoined(flightId);
            return ResponseEntity.ok(flightJoined);
        } catch (FlightCapacityException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Retrieves tickets for a given user.
     *
     * @param userId the ID of the user
     * @param page   the page number
     * @return a ResponseEntity containing the retrieved tickets
     */
    @GetMapping("/user-tickets/{userId}")
    public ResponseEntity<?> getTickets(@PathVariable int userId, @RequestParam int page) {
        Page userTickets = bookingService.getUserTickets(userId, page);
        return ResponseEntity.ok(userTickets);
    }

    /**
     * Deletes all user tickets based on the user's first name, last name, last name prefix, and last name suffix.
     *
     * @param userId the ID of the user
     * @param fn     the user's first name
     * @param sn     the user's second name
     * @param lfn    the user's last name
     * @param lsn    the user's last name
     * @return a ResponseEntity with the message "User's tickets deleted."
     */
    @DeleteMapping("/delete-ticket/{userId}")
    public ResponseEntity<?> deleteAllUserTickets(
            @PathVariable int userId,
            @RequestParam String fn,
            @RequestParam String sn,
            @RequestParam String lfn,
            @RequestParam String lsn) {
        User user = new User(userId, fn, sn, lfn, lsn);
        bookingService.deleteAllUserTickets(user);
        return buildResponse("User's tickets deleted.", HttpStatus.OK);
    }
}

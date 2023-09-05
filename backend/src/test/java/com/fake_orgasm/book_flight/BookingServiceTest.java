package com.fake_orgasm.book_flight;

import com.fake_orgasm.flights_management.models.Category;
import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.models.Ticket;
import com.fake_orgasm.flights_management.services.BookingService;
import com.fake_orgasm.users_management.models.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookingServiceTest {

    private final String FLIGHT_ID = "9740c3f2-7021-44ba-86cd-f82e620eca7b";

    @Test
    public void testBookingService() {
        BookingService bookService = new BookingService();

        // Data
        Flight flight;
        List<Ticket> tickets;
        User userForBook = new User(1234, "Luiggy", "Mamani Condori",
                LocalDate.of(1992, 1, 20), "Bolivia");

        flight = bookService.getFlightRepository().search(FLIGHT_ID);

        if (flight != null && flight.isAvailable()) {
            // add new ticket with priority VIP
            Category category = Category.VIP;
            System.out.println("New ticket category: " + category.getType());

            // Show data before
            tickets = bookService.getFlightTickets(flight);
            System.out.println("\n" + "-".repeat(50) + "BEFORE" + "-".repeat(50) + "\n");
            System.out.println(flight);
            System.out.println();
            tickets.forEach(System.out::println);

            int oldNumberOfUserFlights = userForBook.getFlights().size();
            int oldNumberOfTickets = flight.numberOfTickets();

            // flight reservation if is available
            bookService.booking(userForBook, FLIGHT_ID, category);

            // Show data after
            System.out.println("\n" + "-".repeat(50) + "AFTER" + "-".repeat(50) + "\n");
            flight = bookService.getFlightRepository().search(FLIGHT_ID);
            tickets = bookService.getFlightTickets(flight);
            System.out.println(flight);
            System.out.println();
            tickets.forEach(System.out::println);
            userForBook = bookService.getUser();

            int newNumberOfTickets = flight.numberOfTickets();
            int newNumberOfUserFlights = userForBook.getFlights().size();

            // verify the increased tickets on the flight and the user
            assertTrue(newNumberOfTickets > oldNumberOfTickets);
            assertTrue(newNumberOfUserFlights > oldNumberOfUserFlights);
        }
    }

    @Test
    public void flightCancellationTest() {
        BookingService bookService = new BookingService();
        // user to cancel a booking
        User userToCancel = new User(1234, "Jose Luis", "Moreno",
                LocalDate.of(1992, 1, 20), "Bolivia");
        String ticketId = "32e40ccf-413d-4e8e-9fd8-b0d4d977c662";
        Ticket ticketToCancel = bookService.getTicketRepository().search(ticketId);
        userToCancel.addFlight(ticketId);

        if (ticketToCancel != null) {
            // before
            String flightId = ticketToCancel.getFlightId();
            Flight flight = bookService.getFlightRepository().search(flightId);
            int beforeNumberOfTickets = flight.numberOfTickets();

            System.out.println("\n" + "-".repeat(50) + "BEFORE" + "-".repeat(50) + "\n");
            System.out.println("-".repeat(100));
            System.out.println(flight);
            System.out.println("-".repeat(100));
            bookService.getFlightTickets(flight).forEach(System.out::println);
            System.out.println("-".repeat(100));

            System.out.println("\n" + "-".repeat(50) + "DATA" + "-".repeat(50) + "\n");
            System.out.println("User to cancel the booking\n" + userToCancel);
            System.out.println("Ticket ticket to cancel the flight\n" + ticketToCancel);
            // cancel booking
            boolean wasCancel = bookService.deleteBooking(userToCancel, ticketId);
            System.out.println("\n" + "-".repeat(50) + "RESULT" + "-".repeat(50) + "\n");
            System.out.println(wasCancel ? "Successful cancellation" : "Failed cancellation");

            // after
            flight = bookService.getFlightRepository().search(flightId);
            int afterNumberOfTickets = flight.numberOfTickets();

            System.out.println("\n" + "-".repeat(50) + "AFTER" + "-".repeat(50) + "\n");
            System.out.println("-".repeat(100));
            System.out.println(flight);
            System.out.println("-".repeat(100));
            bookService.getFlightTickets(flight).forEach(System.out::println);
            System.out.println("-".repeat(100));
            System.out.println("User to cancel the booking\n" + userToCancel);

            assertTrue(afterNumberOfTickets < beforeNumberOfTickets);
        }
    }

    @Test
    public void cancelABooking() {
        BookingService bookService = new BookingService();

        // data
        Flight flight = bookService.getFlightRepository().search(FLIGHT_ID);
        String ticketId = "c202665a-a0b1-4cc6-8a0c-b6cb2084bd91";
        String newCategory = Category.REGULAR_PASSENGER.getType();
        Ticket ticket;

        // show data before
        ticket = bookService.getTicketRepository().search(ticketId);
        System.out.println("\n" + "-".repeat(50) + "TICKET DATA BEFORE" + "-".repeat(50) + "\n");
        System.out.println(ticket);

        // before edit a book
        System.out.println("\n" + "-".repeat(50) + "BEFORE" + "-".repeat(50) + "\n");
        System.out.println(flight);
        bookService.getFlightTickets(flight).forEach(System.out::println);
        System.out.println();

        // editing a book
        bookService.editBooking(ticketId, newCategory);

        // show data after
        ticket = bookService.getTicketRepository().search(ticketId);
        System.out.println("\n" + "-".repeat(50) + "TICKET DATA AFTER" + "-".repeat(50) + "\n");
        System.out.println(ticket);

        // after edit a book
        System.out.println("\n" + "-".repeat(50) + "AFTER" + "-".repeat(50) + "\n");
        System.out.println(flight);
        bookService.getFlightTickets(flight).forEach(System.out::println);
        System.out.println();

        // prove the new category saved
        assertEquals(ticket.getPriority().getType(), newCategory);
    }
}
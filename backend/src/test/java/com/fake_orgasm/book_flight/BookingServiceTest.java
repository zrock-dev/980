package com.fake_orgasm.book_flight;

import com.fake_orgasm.flights_management.models.Category;
import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.models.Ticket;
import com.fake_orgasm.flights_management.services.BookingService;
import com.fake_orgasm.users_management.models.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookingServiceTest {

    @Test
    public void testBookingService() {
        BookingService bookService = new BookingService();

        // Data
        String flightId = "c8c86159-e441-4080-b927-1a359d63e208";
        Flight flight;
        List<Ticket> tickets;
        User userForBook = new User(1234, "Luiggy", "Mamani Condori",
                LocalDate.of(1992, 1, 20), "Bolivia");

        flight = bookService.getFlightRepository().search(flightId);

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
            bookService.booking(userForBook, flightId, category);

            // Show data after
            System.out.println("\n" + "-".repeat(50) + "AFTER" + "-".repeat(50) + "\n");
            flight = bookService.getFlightRepository().search(flightId);
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
            String flightId = "43b9370d-bdde-45fd-8b6a-4aa685531fa6";
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
            boolean wasCancel = bookService.deleteBook(userToCancel, ticketId);
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
}
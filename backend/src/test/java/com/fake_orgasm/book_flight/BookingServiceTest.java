package com.fake_orgasm.book_flight;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fake_orgasm.flights_management.models.Category;
import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.models.Ticket;
import com.fake_orgasm.flights_management.services.BookingService;
import com.fake_orgasm.users_management.models.User;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BookingServiceTest {

    @Test
    public void testBookingService() {
        BookingService bookService = new BookingService();

        // Data
        String flightId = "c8c86159-e441-4080-b927-1a359d63e208";
        Flight flight;
        List<Ticket> tickets;
        User userForBook = new User(1234, "Luiggy", "Mamani Condori", LocalDate.of(1992, 1, 20), "Bolivia");

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
}

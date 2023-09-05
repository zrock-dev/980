package com.fake_orgasm.flights_management;

import com.fake_orgasm.flights_management.models.Category;
import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.models.Ticket;
import com.fake_orgasm.flights_management.services.BookingService;
import com.fake_orgasm.users_management.models.User;

import java.time.LocalDate;
import java.util.List;

public class BookingMain {

    public static void main(String[] args) {

        String FLIGHT_ID = "c053e172-f0ae-40b0-93cd-6cf9f9659e46";

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


        }
    }

}
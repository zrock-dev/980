package com.fake_orgasm.flights_management;

import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.repository.AirportRepository;
import com.fake_orgasm.flights_management.services.BookingService;
import com.fake_orgasm.generator.flight_generator.FlightGeneratorHandler;
import com.fake_orgasm.users_management.models.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<User> users = new ArrayList<>(List.of(
                new User(1234, "Jose Luis", "Moreno",
                        LocalDate.of(1992, 1, 20), "Bolivia"),
                new User(5678, "Maria", "Gomez",
                        LocalDate.of(1990, 5, 15), "Argentina"),
                new User(9876, "John", "Smith",
                        LocalDate.of(1985, 8, 20), "Estados Unidos"),
                new User(3456, "Anna", "Müller",
                        LocalDate.of(1993, 3, 10), "Alemania"),
                new User(6789, "Sophia", "Chen",
                        LocalDate.of(1988, 11, 25), "China"),
                new User(4321, "Carlos", "López",
                        LocalDate.of(1979, 7, 2), "México"),
                new User(7890, "Emily", "Johnson",
                        LocalDate.of(1995, 9, 8), "Canadá"),
                new User(2345, "Juan", "Martinez",
                        LocalDate.of(1982, 4, 12), "España"),
                new User(8901, "Luisa", "Silva",
                        LocalDate.of(1998, 12, 30), "Colombia"),
                new User(5432, "Thomas", "Brown",
                        LocalDate.of(1987, 6, 5), "Australia"),
                new User(6780, "Sophie", "Dubois",
                        LocalDate.of(1991, 2, 18), "Francia"),
                new User(1122, "Miguel", "Santos",
                        LocalDate.of(1975, 10, 14), "Perú"),
                new User(9988, "Elena", "Kowalski",
                        LocalDate.of(1997, 1, 22), "Polonia"),
                new User(7654, "Daniel", "Kim",
                        LocalDate.of(1984, 11, 8), "Corea del Sur"),
                new User(5433, "Isabella", "Perez",
                        LocalDate.of(1992, 7, 3), "Brasil"),
                new User(5433, "Daniela", "Ochoa",
                        LocalDate.of(2000, 7, 3), "Mexico")
        ));

        BookingService bookService = new BookingService();
        bookService.getTicketRepository().deleteAll();
        bookService.getFlightRepository().deleteAll();
        AirportRepository airportRepository = new AirportRepository();
        airportRepository.deleteAll();

        FlightGeneratorHandler generator = new FlightGeneratorHandler();
        generator.generateTicketsAndSave(users, 2);


/*        System.out.println("\n" + "-".repeat(50) + "AIRPORTS" + "-".repeat(50) + "\n");
        generator.getAirportRepository().findAll().forEach(System.out::println);*/
        System.out.println("\n" + "-".repeat(50) + "FLIGHTS" + "-".repeat(50) + "\n");
        ArrayList<Flight> flights = generator.getFlightRepository().findAll();
        flights.forEach(flight -> {
            System.out.println(flight);
            bookService.getFlightTickets(flight.getId()).forEach(System.out::println);
            System.out.println();
        });
/*        System.out.println("\n" + "-".repeat(50) + "TICKETS" + "-".repeat(50) + "\n");
        generator.getTicketRepository().findAll().forEach(System.out::println);*/

    }

}
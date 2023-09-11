package com.fake_orgasm.flight_generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fake_orgasm.flights_management.models.Airport;
import com.fake_orgasm.flights_management.models.Category;
import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.models.Ticket;
import com.fake_orgasm.flights_management.repository.AirportRepository;
import com.fake_orgasm.flights_management.repository.FlightRepository;
import com.fake_orgasm.flights_management.repository.Page;
import com.fake_orgasm.flights_management.repository.TicketRepository;
import com.fake_orgasm.flights_management.services.BookingService;
import com.fake_orgasm.flights_management.services.RestClient;
import com.fake_orgasm.users_management.models.User;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class FlightGeneratorHandlerTest {

    private static List<User> users;
    private static Random random;
    private static User user = new User(1234, "Luiggy", "Mamani Condori", LocalDate.of(1992, 1, 20), "Bolivia");
    private static Flight flight;

    private static Ticket ticket;
    private static List<Ticket> tickets;
    private static BookingService bookService = new BookingService();
    private static FlightGeneratorHandler generator = new FlightGeneratorHandler();

    @BeforeEach
    public void setup() {
        random = new Random();
        users = new ArrayList<>();
        users.addAll(List.of(
                new User(1234, "Jose Luis", "Moreno", LocalDate.of(1992, 1, 20), "Bolivia"),
                new User(5678, "Maria", "Gomez", LocalDate.of(1990, 5, 15), "Argentina"),
                new User(9876, "John", "Smith", LocalDate.of(1985, 8, 20), "Estados Unidos"),
                new User(3456, "Anna", "Müller", LocalDate.of(1993, 3, 10), "Alemania"),
                new User(6789, "Sophia", "Chen", LocalDate.of(1988, 11, 25), "China"),
                new User(4321, "Carlos", "López", LocalDate.of(1979, 7, 2), "México"),
                new User(7890, "Emily", "Johnson", LocalDate.of(1995, 9, 8), "Canadá"),
                new User(2345, "Juan", "Martinez", LocalDate.of(1982, 4, 12), "España"),
                new User(8901, "Luisa", "Silva", LocalDate.of(1998, 12, 30), "Colombia"),
                new User(5432, "Thomas", "Brown", LocalDate.of(1987, 6, 5), "Australia"),
                new User(6780, "Sophie", "Dubois", LocalDate.of(1991, 2, 18), "Francia"),
                new User(1122, "Miguel", "Santos", LocalDate.of(1975, 10, 14), "Perú"),
                new User(9988, "Elena", "Kowalski", LocalDate.of(1997, 1, 22), "Polonia"),
                new User(7654, "Daniel", "Kim", LocalDate.of(1984, 11, 8), "Corea del Sur"),
                new User(5433, "Isabella", "Perez", LocalDate.of(1992, 7, 3), "Brasil"),
                new User(5433, "Daniela", "Ochoa", LocalDate.of(2000, 7, 3), "Mexico")));
    }

    @Test
    public void generateFlightsTest() {
        FlightGeneratorHandler handler = new FlightGeneratorHandler();
        int amountTicketByUser = 87;

        boolean wereGenerated = handler.generateTickets(users, amountTicketByUser);
        // test status after generate flights
        assertTrue(wereGenerated);

        ArrayList<Flight> flights = handler.getFlights();
        ArrayList<Airport> airports = handler.getAirports();
        ArrayList<Ticket> tickets = handler.getTickets();
        users = handler.getUsers();
        AirportRepository airportRepository = new AirportRepository();
        FlightRepository flightRepository = new FlightRepository();
        TicketRepository ticketRepository = new TicketRepository();
        airportRepository.createTable();
        flightRepository.createTable();
        ticketRepository.createTable();
        airportRepository.create(airports);
        flightRepository.create(flights);
        ticketRepository.create(tickets);

//        // test the amount of data for airports
//        assertEquals(handler.getAmountAirports(), airports.size());
//        // test the amount of data for flights
//        assertEquals(handler.getAmountFlights(), flights.size());
//        // test the amount of data for tickets
//        assertEquals(handler.getAmountTickets(), tickets.size());
//
//        // test the amount ticket generated by user
//        User randomUser = users.get(random.nextInt(users.size()));
//        assertEquals(amountTicketByUser, randomUser.getFlights().size());
    }

    @Test
    public void generateAndDelete() {
        generator = new FlightGeneratorHandler(
                new RestClient(new RestTemplate()),
                new AirportRepository(),
                new FlightRepository(),
                new TicketRepository());
        boolean isProduction = false;
        if (!isProduction) {
            // deleteAllData();
            long startTime = System.currentTimeMillis();
            // generateAndSave();

            long endTime = System.currentTimeMillis();
            System.out.println(endTime - startTime);
            // showData();
        }
    }

    public void generateAndSave() {
        RestClient restClient = new RestClient(new RestTemplate());
        Page<User> page = restClient.getPage(0);
        int totalPages = page.totalPages();
        int pageSize = 20;
        int totalThreads = (totalPages + pageSize - 1) / pageSize;
        ExecutorService executor = Executors.newFixedThreadPool(totalThreads);
        for (int i = 0; i < 1; i++) {
            int from = i * pageSize;
            int to = Math.min((i + 1) * pageSize, totalPages);
            executor.execute(new RestGenerator(from, to));
        }
        executor.shutdown();
        while (!executor.isTerminated()) {}
    }

    public static void deleteAllData() {
        bookService.getTicketRepository().deleteAll();
        bookService.getFlightRepository().deleteAll();
        bookService.getAirportRepository().deleteAll();
    }

    public static void showData() {
        showAirports();
        showFlights();
        showTickets();
    }

    public static void showAirports() {
        System.out.println("\n" + "-".repeat(50) + "AIRPORTS" + "-".repeat(50) + "\n");
        bookService.getAirportRepository().findAll().forEach(System.out::println);
    }

    public static void showFlights() {
        System.out.println("\n" + "-".repeat(50) + "FLIGHTS" + "-".repeat(50) + "\n");
        ArrayList<Flight> flights = generator.getFlightRepository().findAll();
        flights.forEach(flight -> {
            System.out.println("~".repeat(100));
            System.out.println(bookService.getFlightJoined(flight.getId()));
        });
    }

    public static void showTickets() {
        System.out.println("\n" + "-".repeat(50) + "TICKETS" + "-".repeat(50) + "\n");
        bookService.getTicketRepository().findAll().forEach(System.out::println);
    }

    public static Flight getFlight() {
        if (flight == null) {
            ArrayList<Flight> flights = generator.getFlightRepository().findAll();
            flight = flights.get(0);
        }

        return flight;
    }

    public static void booking(Category category) {
        System.out.println("\n" + "-".repeat(50) + "BOOKING" + "-".repeat(50) + "\n");
        System.out.println("\tUser: " + user);
        System.out.println("\tUser flights: " + user.getFlights().size());
        System.out.println("\tTicket Category: " + category.getType());
        System.out.println("\n" + "-".repeat(25) + "FLIGHT BEFORE" + "-".repeat(25) + "\n");
        System.out.println(bookService.getFlightJoined(getFlight().getId()));
        bookService.booking(user, getFlight().getId(), category);
        System.out.println("\n" + "-".repeat(25) + "FLIGHT AFTER" + "-".repeat(25) + "\n");
        System.out.println(bookService.getFlightJoined(getFlight().getId()));
        user = bookService.getUser();
        ticket = bookService.getTicket();
        System.out.println("\n\tUser: " + user);
        System.out.println("\tUser flights: " + user.getFlights().size());
    }

    public static void editBooking(String newCategory) {
        if (ticket != null) {
            System.out.println("\n" + "-".repeat(50) + "EDIT BOOKING" + "-".repeat(50) + "\n");
            System.out.println("\nTicket: " + ticket);
            System.out.println("\tNew category: " + newCategory);
            System.out.println("\n" + "-".repeat(25) + "FLIGHT BEFORE" + "-".repeat(25) + "\n");
            System.out.println(bookService.getFlightJoined(getFlight().getId()));
            bookService.editBooking(ticket.getId(), newCategory);
            System.out.println("\n" + "-".repeat(25) + "FLIGHT AFTER" + "-".repeat(25) + "\n");
            System.out.println(bookService.getFlightJoined(getFlight().getId()));
            System.out.println("\nTicket: " + ticket);
        }
    }

    public static void cancelBooking() {
        if (ticket != null) {
            System.out.println("\n" + "-".repeat(50) + "CANCEL BOOKING" + "-".repeat(50) + "\n");
            System.out.println("\n" + "-".repeat(25) + "FLIGHT BEFORE" + "-".repeat(25) + "\n");
            System.out.println(bookService.getFlightJoined(getFlight().getId()));
            bookService.deleteBooking(user, ticket.getId());
            System.out.println("\n" + "-".repeat(25) + "FLIGHT AFTER" + "-".repeat(25) + "\n");
            System.out.println(bookService.getFlightJoined(getFlight().getId()));
        }
    }
}
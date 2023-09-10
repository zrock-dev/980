package com.fake_orgasm.flight_generator;

import com.fake_orgasm.flights_management.exceptions.FlightCapacityException;
import com.fake_orgasm.flights_management.models.Airport;
import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.models.Ticket;
import com.fake_orgasm.flights_management.repository.AirportRepository;
import com.fake_orgasm.flights_management.repository.FlightRepository;
import com.fake_orgasm.flights_management.repository.TicketRepository;
import com.fake_orgasm.flights_management.services.RestClient;
import com.fake_orgasm.users_management.models.User;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.stereotype.Service;

/**
 * This class handles the generation of airports, flights, and tickets,
 * as well as their storage in repositories.
 */
@Getter
@Service
public class FlightGeneratorHandler {

    private AirportGenerator airportGenerator;
    private FlightGenerator flightGenerator;
    private TicketGenerator ticketGenerator;
    private AirportRepository airportRepository;
    private FlightRepository flightRepository;
    private TicketRepository ticketRepository;
    private RestClient restClient;
    private int amountTickets;
    private int amountFlights;
    private int amountAirports;
    private ArrayList<Airport> airports;
    private ArrayList<Flight> flights;
    private ArrayList<Ticket> tickets;
    private List<User> users;

    /**
     * This method constructs a FlightGeneratorHandler instance with the specified user management
     * and repository references.
     *
     * @param restClient        The RestTemplate client.
     * @param airportRepository The repository for airports.
     * @param flightRepository  The repository for flights.
     * @param ticketRepository  The repository for tickets.
     */
    public FlightGeneratorHandler(
            RestClient restClient,
            AirportRepository airportRepository,
            FlightRepository flightRepository,
            TicketRepository ticketRepository) {
        airportGenerator = new AirportGenerator();
        flightGenerator = new FlightGenerator();

        this.restClient = restClient;
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
        this.ticketRepository = ticketRepository;
    }

    /**
     * This method constructs a FlightGeneratorHandler instance with default repository instances
     * and creates their tables.
     */
    public FlightGeneratorHandler() {
        airportGenerator = new AirportGenerator();
        flightGenerator = new FlightGenerator();

        this.airportRepository = new AirportRepository();
        this.flightRepository = new FlightRepository();
        this.ticketRepository = new TicketRepository();
        airportRepository.createTable();
        flightRepository.createTable();
        ticketRepository.createTable();
    }

    /**
     * This method generates random airports and saves them to the repository.
     *
     * @param amount The number of airports to generate.
     * @return True if airports were generated and saved successfully; false otherwise.
     */
    public boolean generateAirportsAndSave(int amount) {
        boolean wereGenerated = false;
        ArrayList<Airport> airports = airportGenerator.getAirportsRandomly(amount);
        if (!airports.isEmpty()) {
            airportRepository.create(airports);
            wereGenerated = true;
        }

        return wereGenerated;
    }

    /**
     * This method generates random flights and saves them to the repository.
     *
     * @param amount The number of flights to generate.
     * @return True if flights were generated and saved successfully; false otherwise.
     */
    public boolean generateFlightsAndSave(int amount) {
        boolean wereGenerated = false;
        try {
            ArrayList<Airport> airports = airportRepository.findAll();
            if (!airports.isEmpty()) {
                ArrayList<Flight> flights = flightGenerator.getFlightsRandomly(airports, amount);
                flightRepository.create(flights);
                wereGenerated = true;
            }
        } catch (FlightCapacityException e) {
            throw new RuntimeException(e);
        }
        return wereGenerated;
    }

    /**
     * This method generates random tickets for users and saves them to the repository.
     *
     * @param usersToGenerate The list of users to generate tickets for.
     * @param ticketsByUser   The number of tickets to generate per user.
     * @return True if tickets were generated and saved successfully; false otherwise.
     */
    public boolean generateTicketsAndSave(List<User> usersToGenerate, int ticketsByUser) {
        boolean wereGenerated = generateTickets(usersToGenerate, ticketsByUser);
        boolean wereSaved = saveNewData();

        return wereGenerated && wereSaved;
    }

    /**
     * This method generates random tickets for users.
     *
     * @param usersToGenerate The list of users to generate tickets for.
     * @param ticketsByUser   The number of tickets to generate per user.
     * @return True if tickets were generated successfully; false otherwise.
     */
    public boolean generateTickets(List<User> usersToGenerate, int ticketsByUser) {
        boolean wereGenerated = false;
        try {
            if (!usersToGenerate.isEmpty()) {
                this.users = usersToGenerate;
                int usersSize = users.size();
                calculateAmounts(users.size(), ticketsByUser);

                airports = airportGenerator.getAirportsRandomly(amountAirports);
                flights = flightGenerator.getFlightsRandomly(airports, amountFlights);
                ticketGenerator = new TicketGenerator(users, flights);
                for (int userIndex = 0; userIndex < usersSize; userIndex++) {
                    ticketGenerator.generateTickets(userIndex, ticketsByUser);
                }

                tickets = ticketGenerator.getTickets();
                flights = ticketGenerator.getFlights();
                users = ticketGenerator.getUsers();
                wereGenerated = true;
            }
        } catch (FlightCapacityException e) {
            throw new RuntimeException(e);
        }
        return wereGenerated;
    }

    /**
     * This method calculates the required number of tickets, flights, and airports based on the
     * number of users and tickets per user.
     *
     * @param usersSize     The total number of users.
     * @param ticketsByUser The number of tickets to generate per user.
     */
    private void calculateAmounts(int usersSize, int ticketsByUser) {
        amountTickets = (usersSize * ticketsByUser);
        amountFlights = amountTickets / Flight.MIN_CAPACITY;
        amountAirports = amountFlights + 2;

        if (amountFlights <= 0) {
            amountFlights = usersSize / 2;
            amountAirports = usersSize / 2;
        }
    }

    /**
     * This method updates user information in the user management service.
     *
     * @param users The list of users to update.
     */
    private void updateUsers(List<User> users) {
        if (restClient == null) {
            return;
        }
        for (User user : users) {
            restClient.updateUserData(user, user);
        }
    }

    /**
     * This method saves generated data (airports, flights, tickets) to the
     * respective repositories.
     *
     * @return True if data was saved successfully; false otherwise.
     */
    public boolean saveNewData() {
        boolean wereSaved = false;
        airportRepository.create(airports);
        flightRepository.create(flights);
        ticketRepository.create(tickets);
        updateUsers(users);
        wereSaved = true;
        return wereSaved;
    }
}

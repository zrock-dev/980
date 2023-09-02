package com.fake_orgasm.generator.flight_generator;

import com.fake_orgasm.flights_management.exceptions.FlightCapacityException;
import com.fake_orgasm.flights_management.models.Airport;
import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.models.Ticket;
import com.fake_orgasm.flights_management.repository.AirportRepository;
import com.fake_orgasm.flights_management.repository.FlightRepository;
import com.fake_orgasm.flights_management.repository.TicketRepository;
import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.services.IUserManagement;
import com.fake_orgasm.users_management.services.exceptions.IncompleteUserException;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightGeneratorHandler {

    private AirportGenerator airportGenerator;
    private FlightGenerator flightGenerator;
    private TicketGenerator ticketGenerator;
    @Getter
    private AirportRepository airportRepository;
    @Getter
    private FlightRepository flightRepository;
    @Getter
    private TicketRepository ticketRepository;
    private IUserManagement userManagement;
    @Getter
    private int amountTickets;
    @Getter
    private int amountFlights;
    @Getter
    private int amountAirports;
    @Getter
    private ArrayList<Airport> airports;
    @Getter
    private ArrayList<Flight> flights;
    @Getter
    private ArrayList<Ticket> tickets;
    @Getter
    private List<User> users;

    public FlightGeneratorHandler(IUserManagement userManagement) {
        this.userManagement = userManagement;
        initializeHandlers();
    }

    public FlightGeneratorHandler() {
        initializeHandlers();
    }

    private void initializeHandlers() {
        airportGenerator = new AirportGenerator();
        flightGenerator = new FlightGenerator();

        airportRepository = new AirportRepository();
        flightRepository = new FlightRepository();
        ticketRepository = new TicketRepository();

        airportRepository.createTable();
        flightRepository.createTable();
        ticketRepository.createTable();
    }

    public boolean generateAirportsAndSave(int amount) {
        boolean wereGenerated = false;
        ArrayList<Airport> airports = airportGenerator.getAirportsRandomly(amount);
        if (!airports.isEmpty()) {
            airportRepository.create(airports);
            wereGenerated = true;
        }

        return wereGenerated;
    }

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

    public boolean generateTicketsAndSave(List<User> usersToGenerate, int ticketsByUser) {
        boolean wereGenerated = generateTickets(usersToGenerate, ticketsByUser);
        boolean wereSaved = saveNewData();

        return wereGenerated && wereSaved;
    }

    public boolean generateTickets(List<User> usersToGenerate, int ticketsByUser) {
        boolean wereGenerated = false;
        try {
            if (!usersToGenerate.isEmpty()) {
                this.users = usersToGenerate;
                int usersSize = users.size();
                calculateAmounts(users.size(), ticketsByUser);

                airports = airportGenerator
                        .getAirportsRandomly(amountAirports);
                flights = flightGenerator
                        .getFlightsRandomly(airports, amountFlights);
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

    private void calculateAmounts(int usersSize, int ticketsByUser) {
        amountTickets = (usersSize * ticketsByUser);
        amountFlights = amountTickets / Flight.AVERAGE_CAPACITY;
        amountAirports = amountFlights / 2;

        if (amountFlights <= 0) {
            amountFlights = usersSize / 2;
            amountAirports = usersSize / 2;
        }
    }

    private void updateUsers(List<User> users) {
        try {
            for (User user : users) {
                userManagement.update(user, user);
            }
        } catch (IncompleteUserException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean saveNewData() {
        boolean wereSaved = false;
        if (userManagement != null) {
            airportRepository.create(airports);
            flightRepository.create(flights);
            ticketRepository.create(tickets);
            updateUsers(users);
            wereSaved = true;
        }

        return wereSaved;
    }

}
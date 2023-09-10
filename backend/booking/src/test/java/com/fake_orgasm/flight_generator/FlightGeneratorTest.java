package com.fake_orgasm.flight_generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fake_orgasm.flights_management.exceptions.FlightCapacityException;
import com.fake_orgasm.flights_management.models.Airport;
import com.fake_orgasm.flights_management.models.Flight;
import java.util.ArrayList;
import java.util.List;

import com.fake_orgasm.flights_management.repository.AirportRepository;
import com.fake_orgasm.flights_management.repository.FlightRepository;
import com.fake_orgasm.flights_management.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FlightGeneratorTest {

    private FlightGenerator flightGenerator;
    private ArrayList<Airport> airports;

    @BeforeEach
    public void setup() {
        AirportGenerator airportGenerator = new AirportGenerator();
        flightGenerator = new FlightGenerator();
        airports = airportGenerator.getAirportsRandomly(100);
    }

    @Test
    public void airportsNotNullGenerated() throws FlightCapacityException {
        List<Flight> flights = flightGenerator.getFlightsRandomly(airports, 100);
        TicketRepository ticketRepository = new TicketRepository();
        AirportRepository airportRepository = new AirportRepository();
        FlightRepository flightRepository = new FlightRepository();
//        ticketRepository.deleteAll();
//        airportRepository.deleteAll();
//        flightRepository.deleteAll();
        airportRepository.create(airports);
        flightRepository.create(flights);
//        assertNotNull(flights);
    }

    @Test
    public void amountAirportsGenerated() throws FlightCapacityException {
        int amount = 1000;
        List<Flight> flights = flightGenerator.getFlightsRandomly(airports, amount);
        assertEquals(amount, flights.size());
    }
}
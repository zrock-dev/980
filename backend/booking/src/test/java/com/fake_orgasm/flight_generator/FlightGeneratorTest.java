package com.fake_orgasm.flight_generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fake_orgasm.flights_management.exceptions.FlightCapacityException;
import com.fake_orgasm.flights_management.models.Airport;
import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.repository.AirportRepository;
import com.fake_orgasm.flights_management.repository.FlightRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
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
        AirportRepository airportRepository = new AirportRepository();
        airportRepository.create(airports);
        List<Flight> flights = flightGenerator.getFlightsRandomly(airports, 100);

        FlightRepository flightRepository = new FlightRepository();
        flightRepository.create(flights);
        assertNotNull(flights);
    }

    @Test
    public void amountAirportsGenerated() throws FlightCapacityException {
        int amount = 1000;
        List<Flight> flights = flightGenerator.getFlightsRandomly(airports, amount);
        assertEquals(amount, flights.size());
    }
}

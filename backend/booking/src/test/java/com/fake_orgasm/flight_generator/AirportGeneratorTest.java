package com.fake_orgasm.flight_generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fake_orgasm.flights_management.models.Airport;

import java.util.ArrayList;
import java.util.List;

import com.fake_orgasm.flights_management.repository.AirportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AirportGeneratorTest {

    private AirportGenerator airportGenerator;

    @BeforeEach
    public void setup() {
        airportGenerator = new AirportGenerator();
    }

    @Test
    public void airportsNotNullGenerated() {
        ArrayList<Airport> airports = airportGenerator.getAirportsRandomly(100);
        AirportRepository airportRepository = new AirportRepository();
        airportRepository.create(airports);
        assertNotNull(airports);
    }

    @Test
    public void amountAirportsGenerated() {
        int amount = 1000;
        List<Airport> airports = airportGenerator.getAirportsRandomly(amount);
        assertEquals(amount, airports.size());
    }
}

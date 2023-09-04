package com.fake_orgasm.generator.flight_generator;

import com.fake_orgasm.flights_management.models.Airport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AirportGeneratorTest {

    private AirportGenerator airportGenerator;

    @BeforeEach
    public void setup() {
        airportGenerator = new AirportGenerator();
    }

    @Test
    public void airportsNotNullGenerated() {
        List<Airport> airports = airportGenerator.getAirportsRandomly(100);
        assertNotNull(airports);
    }

    @Test
    public void amountAirportsGenerated() {
        int amount = 1000;
        List<Airport> airports = airportGenerator.getAirportsRandomly(amount);
        assertEquals(amount, airports.size());
    }

}
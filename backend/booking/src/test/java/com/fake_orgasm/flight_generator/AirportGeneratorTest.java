package com.fake_orgasm.flight_generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fake_orgasm.flights_management.models.Airport;
import java.util.List;
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

package com.fake_orgasm.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fake_orgasm.generator.flight_history_generator.Airport;
import com.fake_orgasm.generator.flight_history_generator.FlightHistory;
import com.fake_orgasm.generator.flight_history_generator.FlightHistoryGenerator;
import com.fake_orgasm.generator.flight_history_generator.Priority;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FlightHistoryGeneratorTest {
    private FlightHistoryGenerator flightHistoryGenerator;

    /**
     * This method initializes airport loader in all tests.
     */
    @BeforeEach
    public void setUp() {
        flightHistoryGenerator = FlightHistoryGenerator.getInstance();
    }

    @Test
    void flightHistoryGeneratorTest() {
        List<FlightHistory> airports = flightHistoryGenerator.generateFlightHistories(10000);
        assertNotNull(airports);
        assertEquals(10000, airports.size());
    }

    @Test
    void flightHistoryGeneratorByDepartureTest() {
        String dp = "El Alto International Airport";
        int n = 50000;
        var flights = flightHistoryGenerator.generateFlightByDepartureAirport(dp, n);
        assertEquals(50000, flights.size());

        assertEquals(
                "El Alto International Airport",
                flights.get(0).getDepartureAirport().getAirportName());
        assertEquals(
                "El Alto International Airport",
                flights.get(10).getDepartureAirport().getAirportName());
        assertEquals(
                "El Alto International Airport",
                flights.get(100).getDepartureAirport().getAirportName());
        assertEquals(
                "El Alto International Airport",
                flights.get(1000).getDepartureAirport().getAirportName());
        assertEquals(
                "El Alto International Airport",
                flights.get(10000).getDepartureAirport().getAirportName());
        assertEquals(
                "El Alto International Airport",
                flights.get(49999).getDepartureAirport().getAirportName());
    }

    @Test
    void flightHistoryGeneratorBytPriorityTest() {
        var airports = flightHistoryGenerator.generateFlightHistoriesByPriority("VIP", 50000);
        assertEquals(50000, airports.size());
        assertEquals("priority vip", airports.get(0).getTicketType().getPriority());
        assertEquals("priority vip", airports.get(10).getTicketType().getPriority());
        assertEquals("priority vip", airports.get(100).getTicketType().getPriority());
        assertEquals("priority vip", airports.get(1000).getTicketType().getPriority());
        assertEquals("priority vip", airports.get(10000).getTicketType().getPriority());
        assertEquals("priority vip", airports.get(49999).getTicketType().getPriority());
    }

    @Test
    void flightHistoryGeneratorByDestinationTest() {
        String dt = "Leonardo da Vinci–Fiumicino Airport";
        int n = 50000;
        var flights = flightHistoryGenerator.generateFlightByDestination(dt, n);
        assertEquals(50000, flights.size());
        assertEquals(
                "Leonardo da Vinci–Fiumicino Airport",
                flights.get(0).getDestinationAirport().getAirportName());
        assertEquals(
                "Leonardo da Vinci–Fiumicino Airport",
                flights.get(10).getDestinationAirport().getAirportName());
        assertEquals(
                "Leonardo da Vinci–Fiumicino Airport",
                flights.get(100).getDestinationAirport().getAirportName());
        assertEquals(
                "Leonardo da Vinci–Fiumicino Airport",
                flights.get(1000).getDestinationAirport().getAirportName());
        assertEquals(
                "Leonardo da Vinci–Fiumicino Airport",
                flights.get(10000).getDestinationAirport().getAirportName());
        assertEquals(
                "Leonardo da Vinci–Fiumicino Airport",
                flights.get(49999).getDestinationAirport().getAirportName());
    }

    @Test
    void flightHistoryGeneratorPersonalizedTest() {
        Airport d = new Airport("Real Madrid Airport", "Spain", "Madrid");
        Airport dep = new Airport("Barcelona Airport", "Spain", "Barcelona");
        Priority p = Priority.NORMAL;
        var flights = flightHistoryGenerator.generateCustomFlights(dep, d, p, 50000);
        assertEquals(50000, flights.size());

        assertEquals(d.getAirportName(), flights.get(0).getDestinationAirport().getAirportName());
        assertEquals(dep.getAirportName(), flights.get(0).getDepartureAirport().getAirportName());
        assertEquals(Priority.NORMAL, flights.get(0).getTicketType());
    }
}

package com.fake_orgasm.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fake_orgasm.flights_management.models.Airport;
import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.generator.FlightHistoryGenerator;
import com.fake_orgasm.flights_management.models.Category;
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
        List<Flight> airports = flightHistoryGenerator.generateFlightHistories(10000);
        assertNotNull(airports);
        assertEquals(10000, airports.size());
    }

    @Test
    void flightHistoryGeneratorByDepartureTest() {
        String dp = "El Alto International Airport";
        int n = 50000;
        var flights = flightHistoryGenerator.generateFlightByDepartureAirport(dp, n);
        assertEquals(50000, flights.size());

/*        assertEquals(
                "El Alto International Airport",
                flights.get(0).getSourceId().getName());
        assertEquals(
                "El Alto International Airport",
                flights.get(10).getSourceId().getName());
        assertEquals(
                "El Alto International Airport",
                flights.get(100).getSourceId().getName());
        assertEquals(
                "El Alto International Airport",
                flights.get(1000).getSourceId().getName());
        assertEquals(
                "El Alto International Airport",
                flights.get(10000).getSourceId().getName());
        assertEquals(
                "El Alto International Airport",
                flights.get(49999).getSourceId().getName());*/
    }

    @Test
    void flightHistoryGeneratorBytPriorityTest() {
        Category cat = Category.VIP;
        var airports = flightHistoryGenerator.generateFlightHistoriesByCategory(cat, 50000);
        assertEquals(50000, airports.size());
/*        assertEquals(1, airports.get(0).getTicketType().getPriorityNumber());
        assertEquals(1, airports.get(10).getTicketType().getPriorityNumber());
        assertEquals(1, airports.get(100).getTicketType().getPriorityNumber());
        assertEquals(1, airports.get(1000).getTicketType().getPriorityNumber());
        assertEquals(1, airports.get(10000).getTicketType().getPriorityNumber());
        assertEquals(1, airports.get(49999).getTicketType().getPriorityNumber());*/
    }

    @Test
    void flightHistoryGeneratorByDestinationTest() {
        String dt = "Leonardo da Vinci–Fiumicino Airport";
        int n = 50000;
        var flights = flightHistoryGenerator.generateFlightByDestination(dt, n);
        assertEquals(50000, flights.size());
/*        assertEquals(
                "Leonardo da Vinci–Fiumicino Airport",
                flights.get(0).getDestinationId().getName());
        assertEquals(
                "Leonardo da Vinci–Fiumicino Airport",
                flights.get(10).getDestinationId().getName());
        assertEquals(
                "Leonardo da Vinci–Fiumicino Airport",
                flights.get(100).getDestinationId().getName());
        assertEquals(
                "Leonardo da Vinci–Fiumicino Airport",
                flights.get(1000).getDestinationId().getName());
        assertEquals(
                "Leonardo da Vinci–Fiumicino Airport",
                flights.get(10000).getDestinationId().getName());
        assertEquals(
                "Leonardo da Vinci–Fiumicino Airport",
                flights.get(49999).getDestinationId().getName());*/
    }

    @Test
    void flightHistoryGeneratorPersonalizedTest() {
/*        Airport d = new Airport("Real Madrid Airport", "Spain", "Madrid");
        Airport dep = new Airport("Barcelona Airport", "Spain", "Barcelona");
        Category p = Category.REGULAR_PASSENGER;
        var flights = flightHistoryGenerator.generateCustomFlights(dep, d, p, 50000);
        assertEquals(50000, flights.size());

        assertEquals(d.getName(), flights.get(0).getDestinationId().getName());
        assertEquals(dep.getName(), flights.get(0).getSourceId().getName());*/
//        assertEquals(Category.REGULAR_PASSENGER, flights.get(0).getTicketType());
    }
}
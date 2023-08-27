package com.fake_orgasm.generator;
import com.fake_orgasm.generator.flight_history_generator.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlightHistoryGeneratorTest {
    private FlightHistoryGenerator flightHistoryGenerator;

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
        List<FlightHistory> airports = flightHistoryGenerator.generateFlightHistoriesByDepartureAirport("El Alto International Airport", 50000);
        assertEquals(50000, airports.size());
        assertEquals("El Alto International Airport",airports.get(0).getDepartureAirport().getAirportName());
        assertEquals("El Alto International Airport",airports.get(10).getDepartureAirport().getAirportName());
        assertEquals("El Alto International Airport",airports.get(100).getDepartureAirport().getAirportName());
        assertEquals("El Alto International Airport",airports.get(1000).getDepartureAirport().getAirportName());
        assertEquals("El Alto International Airport",airports.get(10000).getDepartureAirport().getAirportName());
        assertEquals("El Alto International Airport",airports.get(49999).getDepartureAirport().getAirportName());
    }

    @Test
    void flightHistoryGeneratorBytPriorityTest() {
        List<FlightHistory> airports = flightHistoryGenerator.generateFlightHistoriesByPriority("VIP", 50000);
        assertEquals(50000, airports.size());
        assertEquals("priority vip",airports.get(0).getTicketType().getPriority());
        assertEquals("priority vip",airports.get(10).getTicketType().getPriority());
        assertEquals("priority vip",airports.get(100).getTicketType().getPriority());
        assertEquals("priority vip",airports.get(1000).getTicketType().getPriority());
        assertEquals("priority vip",airports.get(10000).getTicketType().getPriority());
        assertEquals("priority vip",airports.get(49999).getTicketType().getPriority());
    }
    @Test
    void flightHistoryGeneratorByDestinationTest() {
        List<FlightHistory> airports = flightHistoryGenerator.generateFlightHistoriesByDestinationAirport("Leonardo da Vinci–Fiumicino Airport", 50000);
        assertEquals(50000, airports.size());
        assertEquals("Leonardo da Vinci–Fiumicino Airport",airports.get(0).getDestinationAirport().getAirportName());
        assertEquals("Leonardo da Vinci–Fiumicino Airport",airports.get(10).getDestinationAirport().getAirportName());
        assertEquals("Leonardo da Vinci–Fiumicino Airport",airports.get(100).getDestinationAirport().getAirportName());
        assertEquals("Leonardo da Vinci–Fiumicino Airport",airports.get(1000).getDestinationAirport().getAirportName());
        assertEquals("Leonardo da Vinci–Fiumicino Airport",airports.get(10000).getDestinationAirport().getAirportName());
        assertEquals("Leonardo da Vinci–Fiumicino Airport",airports.get(49999).getDestinationAirport().getAirportName());
    }

    @Test
    void flightHistoryGeneratorPersonalizedTest() {
        Airport destination =  new Airport("Real Madrid Airport", "Spain","Madrid");
        Airport departure =  new Airport("Barcelona Airport", "Spain","Barcelona");
        List<FlightHistory> airports = flightHistoryGenerator.generatePersonalizedFlightHistories(departure,destination,Priority.NORMAL,50000);
        assertEquals(50000, airports.size());

        assertEquals(destination.getAirportName(), airports.get(0).getDestinationAirport().getAirportName());
        assertEquals(departure.getAirportName(), airports.get(0).getDepartureAirport().getAirportName());
        assertEquals(Priority.NORMAL, airports.get(0).getTicketType());


    }
}

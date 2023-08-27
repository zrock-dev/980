package com.fake_orgasm.generator.flight_history_generator;

import java.util.ArrayList;
import java.util.List;

public class FlightHistoryGenerator {
    private static FlightHistoryGenerator instance = null;
    private List<Airport> airports;

    private FlightHistoryGenerator() {
        airports = AirportLoader.getInstance().getAirports();
    }
    
    public List<FlightHistory> generateFlightHistories(int numHistoriesToGenerate) {
        List<FlightHistory> flightHistories = new ArrayList<>();
        for (int i = 0; i < numHistoriesToGenerate; i++) {
            flightHistories.add(generateRandomFlightHistory());
        }
        return flightHistories;
    }

    public List<FlightHistory> generatePersonalizedFlightHistories(
            Airport departureAirport, Airport destinationAirport, Priority ticketType, int creationNumber) {
        List<FlightHistory> flightHistories = new ArrayList<>();
        for (int i = 0; i < creationNumber; i++) {
            flightHistories.add(new FlightHistory(departureAirport, destinationAirport, ticketType));
        }
        return flightHistories;
    }

    public List<FlightHistory> generateFlightHistoriesByDepartureAirport(
            String departureAirportName, int numHistories) {
        Airport departureAirport = findAirportByName(departureAirportName);
        if (departureAirport == null) {
            return new ArrayList<>();
        }
        return generatePersonalizedFlightHistories(departureAirport, getRandomAirport(), Priority.NORMAL, numHistories);
    }

    public List<FlightHistory> generateFlightHistoriesByDestinationAirport(
            String destinationAirportName, int numHistories) {
        Airport destinationAirport = findAirportByName(destinationAirportName);
        if (destinationAirport == null) {
            return new ArrayList<>();
        }
        return generatePersonalizedFlightHistories(getRandomAirport(), destinationAirport, Priority.NORMAL, numHistories);
    }

    public List<FlightHistory> generateFlightHistoriesByPriority(
            String ticketTypeString, int numHistories) {
        Priority ticketType = parsePriority(ticketTypeString);
        if (ticketType == null) {
            return new ArrayList<>();
        }
        return generatePersonalizedFlightHistories(getRandomAirport(), getRandomAirport(), ticketType, numHistories);
    }

    private Airport getRandomAirport() {
        return airports.get((int) (Math.random() * airports.size()));
    }

    private FlightHistory generateRandomFlightHistory() {
        Airport departureAirport = getRandomAirport();
        Airport destinationAirport;

        do {
            destinationAirport = getRandomAirport();
        } while (departureAirport == destinationAirport);

        Priority ticketType = Priority.values()[(int) (Math.random() * Priority.values().length)];

        return new FlightHistory(departureAirport, destinationAirport, ticketType);
    }

    private Airport findAirportByName(String airportName) {
        for (Airport airport : airports) {
            if (airport.getAirportName().equals(airportName)) {
                return airport;
            }
        }
        return null;
    }

    private Priority parsePriority(String ticketTypeString) {
        try {
            return Priority.valueOf(ticketTypeString);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    public static FlightHistoryGenerator getInstance() {
        if (instance == null) {
            instance = new FlightHistoryGenerator();
        }
        return instance;
    }
}

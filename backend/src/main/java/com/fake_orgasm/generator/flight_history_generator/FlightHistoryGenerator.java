package com.fake_orgasm.generator.flight_history_generator;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides functionality to generate flight history data.
 */
public final class FlightHistoryGenerator {
    private static FlightHistoryGenerator instance = null;
    private List<Airport> airports;

    /**
     * This private constructor initializes the FlightHistoryGenerator by loading airports.
     */
    private FlightHistoryGenerator() {
        airports = AirportLoader.getInstance().getAirports();
    }

    /**
     * This method generates a list of random flight history instances.
     *
     * @param numHistoriesToGenerate The number of flight histories to generate.
     * @return A list of randomly generated flight histories.
     */
    public List<FlightHistory> generateFlightHistories(int numHistoriesToGenerate) {
        List<FlightHistory> flightHistories = new ArrayList<>();
        for (int i = 0; i < numHistoriesToGenerate; i++) {
            flightHistories.add(generateRandomFlightHistory());
        }
        return flightHistories;
    }

    /**
     * This method generates a list of personalized flight history instances.
     *
     * @param dp The departure airport for the flight histories.
     * @param dt The destination airport for the flight histories.
     * @param prio The ticket type for the flight histories.
     * @param n The number of flight histories to generate.
     * @return A list of personalized flight histories.
     */
    public List<FlightHistory> generateCustomFlights(Airport dp, Airport dt, Priority prio, int n) {
        List<FlightHistory> flightHistories = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            flightHistories.add(new FlightHistory(dp, dt, prio));
        }
        return flightHistories;
    }

    /**
     * This method generates a list of flight history instances by departure airport.
     *
     * @param deparName The name of the departure airport.
     * @param n The number of flight histories to generate.
     * @return A list of flight histories with the specified departure airport.
     */
    public List<FlightHistory> generateFlightByDepartureAirport(String deparName, int n) {
        Airport departureAirport = findAirportByName(deparName);
        if (departureAirport == null) {
            return new ArrayList<>();
        }
        return generateCustomFlights(departureAirport, getRandomAirport(), Priority.NORMAL, n);
    }

    /**
     * This method generates a list of flight history instances by destination airport.
     *
     * @param dtName The name of the destination airport.
     * @param num The number of flight histories to generate.
     * @return A list of flight histories with the specified destination airport.
     */
    public List<FlightHistory> generateFlightByDestination(String dtName, int num) {
        Airport destAir = findAirportByName(dtName);
        Priority prio = Priority.NORMAL;
        if (destAir == null) {
            return new ArrayList<>();
        }
        return generateCustomFlights(getRandomAirport(), destAir, prio, num);
    }

    /**
     * This method generates a list of flight history instances by priority.
     *
     * @param priority The ticket priority string.
     * @param n The number of flight histories to generate.
     * @return A list of flight histories with the specified priority.
     */
    public List<FlightHistory> generateFlightHistoriesByPriority(String priority, int n) {
        Priority prio = parsePriority(priority);
        if (prio == null) {
            return new ArrayList<>();
        }
        return generateCustomFlights(getRandomAirport(), getRandomAirport(), prio, n);
    }

    /**
     * This method retrieves a random airport from the list of available airports.
     *
     * @return A randomly selected airport.
     */
    private Airport getRandomAirport() {
        return airports.get((int) (Math.random() * airports.size()));
    }

    /**
     * This method generates a random flight history instance.
     *
     * @return A randomly generated flight history.
     */
    private FlightHistory generateRandomFlightHistory() {
        Airport departureAirport = getRandomAirport();
        Airport destinationAirport;

        do {
            destinationAirport = getRandomAirport();
        } while (departureAirport == destinationAirport);

        Priority ticketType = Priority.values()[(int) (Math.random() * Priority.values().length)];

        return new FlightHistory(departureAirport, destinationAirport, ticketType);
    }

    /**
     * This method finds an airport by its name.
     *
     * @param airportName The name of the airport to search for.
     * @return The found airport or null if not found.
     */
    private Airport findAirportByName(String airportName) {
        for (Airport airport : airports) {
            if (airport.getAirportName().equals(airportName)) {
                return airport;
            }
        }
        return null;
    }

    /**
     * This method parses a ticket type string and returns the corresponding Priority.
     *
     * @param ticketTypeString The ticket priority string.
     * @return The corresponding Priority or null if parsing fails.
     */
    private Priority parsePriority(String ticketTypeString) {
        try {
            return Priority.valueOf(ticketTypeString);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * This method returns the singleton instance of the FlightHistoryGenerator.
     *
     * @return The instance of the FlightHistoryGenerator.
     */
    public static FlightHistoryGenerator getInstance() {
        if (instance == null) {
            instance = new FlightHistoryGenerator();
        }
        return instance;
    }
}

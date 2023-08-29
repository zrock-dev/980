package com.fake_orgasm.generator.flight_history_generator;

import com.fake_orgasm.users_management.models.Category;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the flight history generator class.
 *
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
    public List<FlightHistory> generateCustomFlights(Airport dp, Airport dt, Category prio, int n) {
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
        Airport dpt = findAirportByName(deparName);
        Category cat = Category.REGULAR_PASSENGER;
        if (dpt == null) {
            return new ArrayList<>();
        }
        return generateCustomFlights(dpt, getRandomAirport(), cat, n);
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
        Category cat = Category.REGULAR_PASSENGER;
        if (destAir == null) {
            return new ArrayList<>();
        }
        return generateCustomFlights(getRandomAirport(), destAir, cat, num);
    }

    /**
     * This method generates a list of flight history instances by priority.
     *
     * @param cat The cat string.
     * @param n The number of flight histories to generate.
     * @return A list of flight histories with the specified priority.
     */
    public List<FlightHistory> generateFlightHistoriesByCategory(Category cat, int n) {
        return generateCustomFlights(getRandomAirport(), getRandomAirport(), cat, n);
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

        Category cat = Category.values()[(int) (Math.random() * Category.values().length)];

        return new FlightHistory(departureAirport, destinationAirport, cat);
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

package com.fake_orgasm.generator.flight_generator;

import com.fake_orgasm.flights_management.exceptions.FlightCapacityException;
import com.fake_orgasm.flights_management.models.Airport;
import com.fake_orgasm.flights_management.models.Flight;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * This class is responsible for generating random flight data based on a list of airports.
 */
public class FlightGenerator {

    private Random random;

    /**
     * This method constructs a FlightGenerator instance and initializes the random number generator.
     */
    public FlightGenerator() {
        random = new Random();
    }

    /**
     * This method generates a list of random flights with the specified amount,
     * considering a list of airports as sources and destinations.
     *
     * @param airports The list of airports from which flights will depart and arrive.
     * @param amount   The number of flights to generate.
     * @return An ArrayList of randomly generated flights.
     * @throws FlightCapacityException If there is an issue with the flight capacity.
     */
    public ArrayList<Flight> getFlightsRandomly(ArrayList<Airport> airports, int amount)
            throws FlightCapacityException {
        ArrayList<Flight> flights = new ArrayList<>();
        String sourceId;
        String destinationId;
        int capacity;
        int airportSize = airports.size();
        for (int i = 0; i < amount; i++) {
            sourceId = airports.get(random.nextInt(airportSize)).getId();
            do {
                destinationId = airports.get(random.nextInt(airportSize)).getId();
            } while (sourceId.equals(destinationId));
            capacity = random.nextInt(451) + 100;
            flights.add(new Flight(UUID.randomUUID().toString(), sourceId, destinationId, getDateRandomly(), capacity));
        }

        return flights;
    }

    /**
     * This method generates a random date within the next 90 days.
     *
     * @return A Date object representing a random future date.
     */
    private Date getDateRandomly() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        int diasAleatorios = random.nextInt(90) + 1;
        calendar.add(Calendar.DAY_OF_YEAR, diasAleatorios);
        return calendar.getTime();
    }
}

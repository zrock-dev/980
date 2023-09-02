package com.fake_orgasm.generator.flight_generator;

import com.fake_orgasm.flights_management.exceptions.FlightCapacityException;
import com.fake_orgasm.flights_management.models.Airport;
import com.fake_orgasm.flights_management.models.Flight;

import java.util.*;

public class FlightGenerator {

    private Random random;

    public FlightGenerator() {
        random = new Random();
    }

    public ArrayList<Flight> getFlightsRandomly(ArrayList<Airport> airports, int amount)
            throws FlightCapacityException {
        ArrayList<Flight> flights = new ArrayList<>();
        String sourceId, destinationId;
        int capacity;
        int airportSize = airports.size();
        for (int i = 0; i < amount; i++) {
            sourceId = airports.get(random.nextInt(airportSize)).getId();
            do {
                destinationId = airports.get(random.nextInt(airportSize)).getId();
            } while (sourceId.equals(destinationId));
            capacity = random.nextInt(451) + 100;
            flights.add(new Flight(
                    UUID.randomUUID().toString(), sourceId, destinationId,
                    getDateRandomly(), capacity
            ));
        }

        return flights;
    }

    private Date getDateRandomly() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        int diasAleatorios = random.nextInt(90) + 1;
        calendar.add(Calendar.DAY_OF_YEAR, diasAleatorios);
        return calendar.getTime();
    }
}
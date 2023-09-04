package com.fake_orgasm.generator.flight_generator;

import com.fake_orgasm.flights_management.models.Airport;
import com.fake_orgasm.generator.utils.RandomFileReader;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This class is responsible for generating random airport data based on predefined
 * pools of countries and airport names.
 */
public class AirportGenerator {

    private final String COUNTRIES_PATH = "src/main/resources/generation/country_pool.txt";
    private final String NAMES_PATH = "src/main/resources/generation/airport_name_pool.txt";
    private RandomFileReader countryReader;
    private RandomFileReader nameReader;

    /**
     * This method constructs an AirportGenerator instance and initializes the
     * readers for country and airport name pools.
     */
    public AirportGenerator() {
        countryReader = new RandomFileReader(COUNTRIES_PATH, 100);
        nameReader = new RandomFileReader(NAMES_PATH, 200);
        loadReaders();
    }

    private void loadReaders() {
        countryReader.start();
        nameReader.start();
        try {
            countryReader.join();
            nameReader.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method generates a list of random airports with the specified amount.
     *
     * @param amount The number of airports to generate.
     * @return An ArrayList of randomly generated airports.
     */
    public ArrayList<Airport> getAirportsRandomly(int amount) {
        String[] countryData;
        String randomCountry, airportName;
        ArrayList<Airport> airports = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            randomCountry = countryReader.getRandomLine();
            countryData = randomCountry.split(",");
            airportName = nameReader.getRandomLine() + " "
                    + nameReader.getRandomLine() + " Airport";
            airports.add(new Airport(UUID.randomUUID().toString(),
                    airportName,
                    countryData[0],
                    countryData[1]
            ));
        }

        return airports;
    }
}
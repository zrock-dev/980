package com.fake_orgasm.flight_generator;

import com.fake_orgasm.flights_management.models.Airport;
import com.fake_orgasm.utils.RandomFileReader;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

/**
 * This class is responsible for generating random airport data based on predefined
 * pools of countries and airport names.
 */
public class AirportGenerator {
    private final Path currentDirectory = Paths.get("").toAbsolutePath();
    private final String countriesPath =
            currentDirectory+"/src/main/resources/generation/country_pool.txt";
    private final String namesPath =
            currentDirectory+"/src/main/resources/generation/airport_name_pool.txt";
    private RandomFileReader countryReader;
    private RandomFileReader nameReader;

    /**
     * This method constructs an AirportGenerator instance and initializes the
     * readers for country and airport name pools.
     */
    public AirportGenerator() {
       countryReader = new RandomFileReader(countriesPath, 100);
        nameReader = new RandomFileReader(namesPath, 200);
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
        String randomCountry;
        String airportName;
        ArrayList<Airport> airports = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            randomCountry = countryReader.getRandomLine();
            countryData = randomCountry.split(",");
            airportName = nameReader.getRandomLine() + " " + nameReader.getRandomLine() + " Airport";
            airports.add(new Airport(UUID.randomUUID().toString(), airportName, countryData[0], countryData[1]));
        }

        return airports;
    }
}

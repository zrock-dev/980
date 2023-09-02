package com.fake_orgasm.generator.flight_generator;

import com.fake_orgasm.flights_management.models.Airport;
import com.fake_orgasm.generator.utils.RandomFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AirportGenerator {

    private final String COUNTRIES_PATH = "src/main/resources/generation/country_pool.txt";
    private final String NAMES_PATH = "src/main/resources/generation/airport_name_pool.txt";
    private RandomFileReader countryReader;
    private RandomFileReader nameReader;

    public AirportGenerator() {
        countryReader = new RandomFileReader(COUNTRIES_PATH);
        nameReader = new RandomFileReader(NAMES_PATH);
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

    public ArrayList<Airport> getAirportsRandomly(int amount) {
        String[] countryData;
        String randomCountry, airportName;
        ArrayList<Airport> airports = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            randomCountry = countryReader.getRandomLine();
            countryData = randomCountry.split(",");
            airportName = nameReader.getRandomLine() + " " + nameReader.getRandomLine() + " Airport";
            airports.add(new Airport(UUID.randomUUID().toString(),
                    airportName,
                    countryData[0],
                    countryData[1]
            ));
        }

        return airports;
    }

}
package com.fake_orgasm.generator.flight_history_generator;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AirportLoader {
    private static AirportLoader instance = null;
    private List<Airport> airports;

    private AirportLoader() {
        airports = loadAirportsFromJSON();
    }

    public static AirportLoader getInstance() {
        if (instance == null) {
            instance = new AirportLoader();
        }
        return instance;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    private List<Airport> loadAirportsFromJSON() {
        List<Airport> airports_local = new ArrayList<>();
        try {
            JSONArray airportArray = readAirportArrayFromJSON();

            for (Object airportObj : airportArray) {
                JSONObject airportJson = (JSONObject) airportObj;
                Airport airport = createAirportFromJson(airportJson);
                airports_local.add(airport);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return airports_local;
    }

    private JSONArray readAirportArrayFromJSON() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        InputStream inputStream = AirportLoader.class.getResourceAsStream("/airports.json");
        if (inputStream != null) {
            return (JSONArray) parser.parse(new InputStreamReader(inputStream));
        }
        throw new IOException("Failed to read JSON file.");
    }

    private Airport createAirportFromJson(JSONObject airportJson) {
        String airportName = (String) airportJson.get("airportName");
        String country = (String) airportJson.get("country");
        String state = (String) airportJson.get("state");
        return new Airport(airportName, country, state);
    }

    public void printAirports() {
        int i = 0;
        for (Airport airport : airports) {
            i++;
            System.out.println(i + "). " + airport.getAirportName() + " - " + airport.getCountry());
        }
    }
}
package com.fake_orgasm.generator.flight_history_generator;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This is the airport loader class.
 *
 */
@Getter
@Setter
public final class AirportLoader {
    private static AirportLoader instance = null;
    private List<Airport> airports;

    /**
     * This is the constructor for this class.
     */
    private AirportLoader() {
        airports = loadAirportsFromJSON();
    }

    /**
     * This method returns the singleton instance of the AirportLoader.
     *
     * @return The instance of the AirportLoader.
     */
    public static AirportLoader getInstance() {
        if (instance == null) {
            instance = new AirportLoader();
        }
        return instance;
    }

    /**
     * This method loads the airports from the json file.
     *
     * @return The list of loaded airports.
     */
    private List<Airport> loadAirportsFromJSON() {
        List<Airport> airportsLocal = new ArrayList<>();
        try {
            JSONArray airportArray = readAirportArrayFromJSON();

            for (Object airportObj : airportArray) {
                JSONObject airportJson = (JSONObject) airportObj;
                Airport airport = createAirportFromJson(airportJson);
                airportsLocal.add(airport);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return airportsLocal;
    }

    /**
     * This method reads the airport array from json.
     *
     * @return The JSONArray of loaded airports.
     * @throws IOException
     * @throws ParseException
     */
    private JSONArray readAirportArrayFromJSON() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        InputStream inputStream = AirportLoader.class.getResourceAsStream("/airports.json");
        if (inputStream != null) {
            return (JSONArray) parser.parse(new InputStreamReader(inputStream));
        }
        throw new IOException("Failed to read JSON file.");
    }

    /**
     * This method creates an airport from a json object.
     *
     * @param airportJson
     * @return The new Airport Object
     */
    private Airport createAirportFromJson(JSONObject airportJson) {
        String airportName = (String) airportJson.get("airportName");
        String country = (String) airportJson.get("country");
        String state = (String) airportJson.get("state");
        return new Airport(airportName, country, state);
    }

    /**
     * This method prints the loaded airports to the console.
     */
    public void printAirports() {
        int i = 0;
        for (Airport airport : airports) {
            i++;
            System.out.println(i + "). " + airport.getAirportName() + " - " + airport.getCountry());
        }
    }
}

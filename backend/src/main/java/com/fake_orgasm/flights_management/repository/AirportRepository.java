package com.fake_orgasm.flights_management.repository;

import com.fake_orgasm.flights_management.models.Airport;

import java.sql.*;
import java.util.ArrayList;

/**
 * This class has the responsibility of managing airport data in the database.
 * It provides methods for creating, retrieving, and deleting airport records.
 */
public class AirportRepository {

    private FlightDatabase database;

    /**
     * This method constructs an AirportRepository and initializes it with a database connection.
     */
    public AirportRepository() {
        database = FlightDatabase.getInstance();
    }

    /**
     * This method creates an "Airport" table in the database if it does not already exist.
     */
    public void createTable() {
        try {
            Statement statement;
            statement = database.getConnection().createStatement();
            String query = "CREATE TABLE IF NOT EXISTS Airport" +
                    "(id VARCHAR(250) PRIMARY KEY," +
                    "airportName VARCHAR(250)," +
                    "country VARCHAR(250)," +
                    "stateName  VARCHAR(250));";
            statement.executeUpdate(query);
            database.createIndexById(statement, "Airport");
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method creates an airport record in the database.
     *
     * @param airport The Airport object to be created.
     * @return True if the airport was successfully created, otherwise false.
     */
    public boolean create(Airport airport) {
        boolean wasSaved = false;
        if (!exists(airport.getId()))
            return wasSaved;
        try {
            String query = "INSERT INTO Airport " +
                    "(id, airportName, country, stateName)" +
                    "values (?, ?, ?, ?)";
            PreparedStatement ps = database.getConnection().prepareStatement(query);
            ps.setString(1, airport.getId());
            ps.setString(2, airport.getName());
            ps.setString(3, airport.getCountry());
            ps.setString(4, airport.getState());
            ps.execute();
            ps.close();
            wasSaved = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wasSaved;
    }

    /**
     * This method creates multiple airport records in the database.
     *
     * @param airports An ArrayList of Airport objects to be created.
     * @return True if all airports were successfully created, otherwise false.
     */
    public boolean create(ArrayList<Airport> airports) {
        boolean wereCreated = false;

        if (airports == null || airports.isEmpty()) {
            return wereCreated;
        }

        try {
            String query = "INSERT INTO Airport " +
                    "(id, airportName, country, stateName) " +
                    "VALUES (?, ?, ?, ?)";
            Connection connection = database.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            for (Airport airport : airports) {
                if (!exists(airport.getId())) {
                    ps.setString(1, airport.getId());
                    ps.setString(2, airport.getName());
                    ps.setString(3, airport.getCountry());
                    ps.setString(4, airport.getState());
                    ps.addBatch();
                }
            }

            wereCreated = database.wereCreated(ps);
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return wereCreated;
    }

    /**
     * This method retrieves a list of all airports stored in the database.
     *
     * @return An ArrayList of Airport objects representing all airports in the database.
     */
    public ArrayList<Airport> findAll() {
        String query = "SELECT * FROM Airport;";
        try {
            ArrayList<Airport> searches = new ArrayList<>();
            PreparedStatement ps = database.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            String id, name, country, state;
            while (rs.next()) {
                id = rs.getString("id");
                name = rs.getString("airportName");
                country = rs.getString("country");
                state = rs.getString("stateName");
                searches.add(new Airport(id, name, country, state));
            }
            ps.close();
            return searches;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method searches for an airport by its ID in the database.
     *
     * @param id The ID of the airport to search for.
     * @return The Airport object if found, or null if not found.
     */
    public Airport search(String id) {
        String query = "SELECT * FROM Airport WHERE id = ?";
        try {
            PreparedStatement ps = database.getConnection().prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String airportId = rs.getString("id");
                String name = rs.getString("airportName");
                String country = rs.getString("country");
                String state = rs.getString("stateName");
                Airport airport = new Airport(airportId, name, country, state);

                ps.close();
                return airport;
            } else {
                ps.close();
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method deletes an airport record from the database by its ID.
     *
     * @param id The ID of the airport to delete.
     * @return True if the airport was successfully deleted, otherwise false.
     */
    public boolean delete(String id) {
        return database.delete("Airport", id);
    }

    /**
     * Deletes all records from the "Airport" database table.
     * <p>
     * This method delegates the task of deleting all records from the "Airport"
     * database table to the corresponding method in the associated "database" object.
     *
     * @return true if all records in the "Airport" table were successfully deleted, false otherwise.
     */
    public boolean deleteAll() {
        return database.deleteAll("Airport");
    }


    /**
     * This method verify if a data exists on the airport table using their id.
     *
     * @param id airport data id.
     * @return boolean to know if exist the aiport.
     */
    public boolean exists(String id) {
        return database.exists("Airport", id);
    }
}
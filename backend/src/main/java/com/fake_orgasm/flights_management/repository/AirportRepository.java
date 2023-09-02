package com.fake_orgasm.flights_management.repository;

import com.fake_orgasm.flights_management.models.Airport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AirportRepository {

    private FlightDatabase database;

    public AirportRepository() {
        database = FlightDatabase.getInstance();
    }

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

    public boolean create(Airport airport) {
        boolean wasSaved = false;
        if (database.doesNotExist("Airport", airport.getId())) return wasSaved;
        try {
            String query = "INSERT INTO Airport (id, airportName, country, stateName)" +
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

    public List<Airport> findAll() {
        String query = "SELECT * FROM Airport;";
        try {
            List<Airport> searches = new ArrayList<>();
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

    public boolean remove(String id) {
        return database.remove("Airport", id);
    }
}
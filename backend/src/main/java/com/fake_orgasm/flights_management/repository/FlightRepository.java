package com.fake_orgasm.flights_management.repository;

import com.fake_orgasm.flights_management.exceptions.FlightCapacityException;
import com.fake_orgasm.flights_management.models.Flight;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class has the responsibility of managing flight data in the database.
 * It provides methods for creating, retrieving, updating, and deleting flight records.
 */
public class FlightRepository {

    private FlightDatabase database;

    /**
     * This method constructs a FlightRepository and initializes it with a database connection.
     */
    public FlightRepository() {
        database = FlightDatabase.getInstance();
    }

    /**
     * This method creates a "Flight" table in the database if it does not already exist.
     */
    public void createTable() {
        try {
            Statement statement = database.getConnection().createStatement();
            String query = "CREATE TABLE IF NOT EXISTS Flight"
                    + "(id VARCHAR(250) PRIMARY KEY,"
                    + "sourceId VARCHAR(250),"
                    + "destinationId VARCHAR(250),"
                    + "arrivalDate  DATE,"
                    + "capacity INTEGER,"
                    + "tickets VARCHAR(10000),"
                    + "FOREIGN KEY (sourceId) REFERENCES Airport(id),"
                    + "FOREIGN KEY (destinationId) REFERENCES Airport(id));";
            statement.executeUpdate(query);
            database.createIndexById(statement, "Flight");
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method creates a flight record in the database.
     *
     * @param flight The Flight object to be created.
     * @return True if the flight was successfully created, otherwise false.
     */
    public boolean create(Flight flight) {
        boolean wasSaved = false;
        if (database.doesNotExist("Flight", flight.getId())) {
            return wasSaved;
        }
        try {
            String query = "INSERT INTO Flight (id, sourceId, destinationId, "
                    + "arrivalDate, capacity, tickets) "
                    + "values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = database.getConnection().prepareStatement(query);
            ps.setString(1, flight.getId());
            ps.setString(2, flight.getSourceId());
            ps.setString(3, flight.getDestinationId());
            ps.setDate(4, (Date) flight.getDate());
            ps.setInt(5, flight.getCapacity());
            ps.setString(6, flight.getPriorityTickets());
            ps.execute();
            ps.close();
            wasSaved = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wasSaved;
    }

    /**
     * This method creates multiple flight records in the database.
     *
     * @param flights A list of Flight objects to be created.
     * @return True if all flights were successfully created, otherwise false.
     */
    public boolean create(List<Flight> flights) {
        boolean wereCreated = false;

        if (flights == null || flights.isEmpty()) {
            return wereCreated;
        }

        try {
            String query = "INSERT INTO Flight"
                    + " (id, sourceId, destinationId, arrivalDate, capacity, tickets) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            Connection connection = database.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            for (Flight flight : flights) {
                ps.setString(1, flight.getId());
                ps.setString(2, flight.getSourceId());
                ps.setString(3, flight.getDestinationId());
                ps.setDate(4, new java.sql.Date(flight.getDate().getTime()));
                ps.setInt(5, flight.getCapacity());
                ps.setString(6, flight.getPriorityTickets());
                ps.addBatch();
            }

            wereCreated = database.wereCreated(ps);
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return wereCreated;
    }

    /**
     * This method retrieves a list of all flights stored in the database.
     *
     * @return A list of Flight objects representing all flights in the database.
     */
    public ArrayList<Flight> findAll() {
        String query = "SELECT * FROM Flight;";
        try {
            ArrayList<Flight> searches = new ArrayList<>();
            PreparedStatement ps = database.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            String id;
            String sourceId;
            String destinationId;
            String ticketIds;
            Date arrivalDate;
            int capacity;
            while (rs.next()) {
                id = rs.getString("id");
                sourceId = rs.getString("sourceId");
                destinationId = rs.getString("destinationId");
                arrivalDate = rs.getDate("arrivalDate");
                capacity = rs.getInt("capacity");
                ticketIds = rs.getString("tickets");
                searches.add(new Flight(id, sourceId, destinationId, arrivalDate, capacity, ticketIds));
            }
            ps.close();
            return searches;
        } catch (SQLException | FlightCapacityException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method updates a flight record in the database.
     *
     * @param id     The ID of the flight to update.
     * @param flight The updated Flight object.
     * @return True if the flight was successfully updated, otherwise false.
     */
    public boolean update(String id, Flight flight) {
        boolean wasUpdated = false;
        try {
            if (database.doesNotExist("Flight", id)) {
                String query = "UPDATE Flight SET sourceId=?, destinationId=?, "
                        + "arrivalDate=?, capacity=?, tickets=? WHERE id=?";
                PreparedStatement ps = database.getConnection().prepareStatement(query);
                ps.setString(1, flight.getSourceId());
                ps.setString(2, flight.getDestinationId());
                ps.setDate(3, (Date) flight.getDate());
                ps.setInt(4, flight.getCapacity());
                ps.setString(5, flight.getPriorityTickets());
                ps.setString(6, id);
                int rowsUpdated = ps.executeUpdate();
                ps.close();

                if (rowsUpdated > 0) {
                    wasUpdated = true;
                }
            } else {
                create(flight);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wasUpdated;
    }

    /**
     * This method retrieves a flight record from the database by its ID.
     *
     * @param id The ID of the flight to retrieve.
     * @return The Flight object representing the flight record, or null if not found.
     */
    public Flight search(String id) {
        String query = "SELECT * FROM Flight WHERE id = ?";
        try {
            PreparedStatement ps = database.getConnection().prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String sourceId = rs.getString("sourceId");
                String destinationId = rs.getString("destinationId");
                Date arrivalDate = rs.getDate("arrivalDate");
                int capacity = rs.getInt("capacity");
                String ticketIds = rs.getString("tickets");
                Flight flight = null;
                try {
                    flight = new Flight(id, sourceId, destinationId, arrivalDate, capacity, ticketIds);
                } catch (FlightCapacityException e) {
                    throw new RuntimeException(e);
                }

                ps.close();
                return flight;
            } else {
                ps.close();
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method deletes a flight record from the database by its ID.
     *
     * @param id The ID of the flight to delete.
     * @return True if the flight was successfully deleted, otherwise false.
     */
    public boolean delete(String id) {
        return database.delete("Flight", id);
    }
}

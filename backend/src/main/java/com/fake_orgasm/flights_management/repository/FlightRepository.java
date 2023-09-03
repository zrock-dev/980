package com.fake_orgasm.flights_management.repository;

import com.fake_orgasm.flights_management.exceptions.FlightCapacityException;
import com.fake_orgasm.flights_management.models.Flight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightRepository {

    private FlightDatabase database;

    public FlightRepository() {
        database = FlightDatabase.getInstance();
    }

    public void createTable() {
        try {
            Statement statement = database.getConnection().createStatement();
            String query = "CREATE TABLE IF NOT EXISTS Flight" +
                    "(id VARCHAR(250) PRIMARY KEY," +
                    "sourceId VARCHAR(250)," +
                    "destinationId VARCHAR(250)," +
                    "arrivalDate  DATE," +
                    "capacity INTEGER," +
                    "tickets VARCHAR(10000)," +
                    "FOREIGN KEY (sourceId) REFERENCES Airport(id)," +
                    "FOREIGN KEY (destinationId) REFERENCES Airport(id));";
            statement.executeUpdate(query);
            database.createIndexById(statement, "Flight");
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean create(Flight flight) {
        boolean wasSaved = false;
        if (database.doesNotExist("Flight", flight.getId())) return wasSaved;
        try {
            String query = "INSERT INTO Flight (id, sourceId, destinationId, " +
                    "arrivalDate, capacity, tickets) " +
                    "values (?, ?, ?, ?, ?, ?)";
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

    public boolean create(List<Flight> flights) {
        boolean wereCreated = false;

        if (flights == null || flights.isEmpty()) {
            return wereCreated;
        }

        try {
            String query = "INSERT INTO Flight" +
                    " (id, sourceId, destinationId, arrivalDate, capacity, tickets) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
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

    public ArrayList<Flight> findAll() {
        String query = "SELECT * FROM Flight;";
        try {
            ArrayList<Flight> searches = new ArrayList<>();
            PreparedStatement ps = database.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            String id, sourceId, destinationId, ticketIds;
            Date arrivalDate;
            int capacity;
            String[] ticketsArray;
            while (rs.next()) {
                id = rs.getString("id");
                sourceId = rs.getString("sourceId");
                destinationId = rs.getString("destinationId");
                arrivalDate = rs.getDate("arrivalDate");
                capacity = rs.getInt("capacity");
                ticketIds = rs.getString("tickets");
                searches.add(new Flight(id, sourceId, destinationId,
                        arrivalDate, capacity, ticketIds));
            }
            ps.close();
            return searches;
        } catch (SQLException | FlightCapacityException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(String id, Flight flight) {
        boolean wasUpdated = false;
        try {
            if (database.doesNotExist("Flight", id)) {
                String query = "UPDATE Flight SET sourceId=?, destinationId=?, " +
                        "arrivalDate=?, capacity=?, tickets=? WHERE id=?";
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
                    flight = new Flight(id, sourceId, destinationId,
                            arrivalDate, capacity, ticketIds);
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


    public boolean delete(String id) {
        return database.delete("Flight", id);
    }
}
package com.fake_orgasm.flights_management.repository;

import com.fake_orgasm.flights_management.exceptions.FlightCapacityException;
import com.fake_orgasm.flights_management.models.Airport;
import com.fake_orgasm.flights_management.models.Flight;
import com.fake_orgasm.flights_management.models.FlightJoined;

import java.sql.*;
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
            String query = "CREATE TABLE IF NOT EXISTS Flight" +
                    "(id VARCHAR(250) PRIMARY KEY," +
                    "sourceId VARCHAR(250)," +
                    "destinationId VARCHAR(250)," +
                    "arrivalDate  DATE," +
                    "capacity INTEGER," +
                    "tickets VARCHAR(10000)," +
                    "lastTicket VARCHAR(100)," +
                    "FOREIGN KEY (sourceId) REFERENCES Airport(id)," +
                    "FOREIGN KEY (destinationId) REFERENCES Airport(id));";
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
        if (!exists(flight.getId())) return wasSaved;
        try {
            String query = "INSERT INTO Flight (id, sourceId, destinationId, " +
                    "arrivalDate, capacity, tickets, lastTicket) " +
                    "values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = database.getConnection().prepareStatement(query);
            ps.setString(1, flight.getId());
            ps.setString(2, flight.getSourceId());
            ps.setString(3, flight.getDestinationId());
            ps.setDate(4, (Date) flight.getDate());
            ps.setInt(5, flight.getCapacity());
            ps.setString(6, flight.getPriorityTickets());
            ps.setString(7, flight.getLastTicket());
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
            String query = "INSERT INTO Flight" +
                    " (id, sourceId, destinationId, arrivalDate, capacity, tickets, lastTicket) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            Connection connection = database.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            for (Flight flight : flights) {
                if (!exists(flight.getId())) {
                    ps.setString(1, flight.getId());
                    ps.setString(2, flight.getSourceId());
                    ps.setString(3, flight.getDestinationId());
                    ps.setDate(4, new Date(flight.getDate().getTime()));
                    ps.setInt(5, flight.getCapacity());
                    ps.setString(6, flight.getPriorityTickets());
                    ps.setString(7, flight.getLastTicket());
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
            String id, sourceId, destinationId, ticketIds, lastTicket;
            Date arrivalDate;
            int capacity;
            while (rs.next()) {
                id = rs.getString("id");
                sourceId = rs.getString("sourceId");
                destinationId = rs.getString("destinationId");
                arrivalDate = rs.getDate("arrivalDate");
                capacity = rs.getInt("capacity");
                ticketIds = rs.getString("tickets");
                lastTicket = rs.getString("lastTicket");
                searches.add(new Flight(id, sourceId, destinationId,
                        arrivalDate, capacity, ticketIds, lastTicket));
            }
            ps.close();
            return searches;
        } catch (SQLException | FlightCapacityException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method retrieves a list of detailed information about all flights,
     * including source and destination airports.
     * <p>
     * This method fetches detailed information about all available flights,
     * including their source and destination airports, arrival dates,
     * capacities, and associated ticket IDs.
     *
     * @return A list of FlightJoined objects containing detailed
     * flight information for all available flights.
     */
    public List<FlightJoined> findAllFlightsJoined() {
        List<FlightJoined> flights = new ArrayList<>();
        String query = "SELECT Flight.*, Airport.airportName AS sourceAirportName, "
                + "Airport.country AS sourceCountry, Airport.stateName AS sourceState, "
                + "DestAirport.airportName AS destAirportName, DestAirport.country AS destCountry, "
                + "DestAirport.stateName AS destState "
                + "FROM Flight "
                + "INNER JOIN Airport ON Flight.sourceId = Airport.id "
                + "INNER JOIN Airport AS DestAirport ON Flight.destinationId = DestAirport.id;";

        try {
            PreparedStatement ps = database.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            String flightId, sourceAirportId, destAirportId, ticketIds,
                    sourceAirportName, sourceCountry, sourceState,
                    destAirportName, destCountry, destState;
            int capacity;
            java.util.Date date;
            Airport source, destination;
            while (rs.next()) {
                flightId = rs.getString("id");
                sourceAirportId = rs.getString("sourceId");
                destAirportId = rs.getString("destinationId");
                date = rs.getDate("arrivalDate");
                capacity = rs.getInt("capacity");
                ticketIds = rs.getString("tickets");
                sourceAirportName = rs.getString("sourceAirportName");
                sourceCountry = rs.getString("sourceCountry");
                sourceState = rs.getString("sourceState");
                destAirportName = rs.getString("destAirportName");
                destCountry = rs.getString("destCountry");
                destState = rs.getString("destState");

                source = new Airport(sourceAirportId, sourceAirportName,
                        sourceCountry, sourceState);
                destination = new Airport(destAirportId, destAirportName,
                        destCountry, destState);

                flights.add(
                        new FlightJoined(flightId, source, destination, date,
                                capacity, ticketIds)
                );
            }

            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flights;
    }

    /**
     * This method retrieves detailed information about a specific flight,
     * including source and destination airports.
     * <p>
     * This method fetches detailed information about a flight including
     * its source and destination airports, based on the provided flight ID.
     *
     * @param flightId The unique identifier of the flight to retrieve.
     * @return A FlightJoined object containing detailed flight information,
     * including associated source and destination airports, arrival date,
     * capacity, and ticket IDs. Returns null if no matching flight is found.
     */
    public FlightJoined findFlightJoined(String flightId) {
        String query = "SELECT Flight.*, Airport.airportName AS sourceAirportName, "
                + "Airport.country AS sourceCountry, Airport.stateName AS sourceState, "
                + "DestAirport.airportName AS destAirportName, DestAirport.country AS destCountry, "
                + "DestAirport.stateName AS destState "
                + "FROM Flight "
                + "INNER JOIN Airport ON Flight.sourceId = Airport.id "
                + "INNER JOIN Airport AS DestAirport ON Flight.destinationId = DestAirport.id "
                + "WHERE Flight.id = ?;";

        try {
            PreparedStatement ps = database.getConnection().prepareStatement(query);
            ps.setString(1, flightId);

            ResultSet rs = ps.executeQuery();

            String sourceAirportName, sourceCountry, sourceState, destAirportName,
                    destCountry, destState, ticketIds;
            java.util.Date date;
            Airport source, destination;
            int capacity;

            if (rs.next()) {
                sourceAirportName = rs.getString("sourceAirportName");
                sourceCountry = rs.getString("sourceCountry");
                sourceState = rs.getString("sourceState");
                destAirportName = rs.getString("destAirportName");
                destCountry = rs.getString("destCountry");
                destState = rs.getString("destState");

                date = rs.getDate("arrivalDate");
                capacity = rs.getInt("capacity");
                ticketIds = rs.getString("tickets");

                source = new Airport("", sourceAirportName, sourceCountry, sourceState);
                destination = new Airport("", destAirportName, destCountry, destState);
                return new FlightJoined(flightId, source, destination, date, capacity, ticketIds);
            }

            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
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
            if (exists(id)) {
                String lastTicket = flight.getLastTicket();
                System.out.println("-".repeat(100));
                System.out.println(lastTicket);
                System.out.println("-".repeat(100));
                String query = "UPDATE Flight SET sourceId=?, destinationId=?, " +
                        "arrivalDate=?, capacity=?, tickets=?, lastTicket=? WHERE id=?";
                PreparedStatement ps = database.getConnection().prepareStatement(query);
                ps.setString(1, flight.getSourceId());
                ps.setString(2, flight.getDestinationId());
                ps.setDate(3, (Date) flight.getDate());
                ps.setInt(4, flight.getCapacity());
                ps.setString(5, flight.getPriorityTickets());
                ps.setString(6, lastTicket);
                ps.setString(7, id);
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
                String lastTicket = rs.getString("lastTicket");
                Flight flight = null;
                try {
                    flight = new Flight(id, sourceId, destinationId,
                            arrivalDate, capacity, ticketIds, lastTicket);
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

    /**
     * Deletes all records from the "Flight" database table.
     * <p>
     * This method delegates the task of deleting all records from the "Flight"
     * database table to the corresponding method in the associated "database" object.
     *
     * @return true if all records in the "Flight" table were successfully deleted, false otherwise.
     */
    public boolean deleteAll() {
        return database.deleteAll("Flight");
    }


    /**
     * This method verify if a data exists on the airport table using their id.
     *
     * @param id airport data id.
     * @return boolean to know if exist the aiport.
     */
    public boolean exists(String id) {
        return database.exists("Flight", id);
    }
}
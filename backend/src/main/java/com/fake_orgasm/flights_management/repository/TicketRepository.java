package com.fake_orgasm.flights_management.repository;

import com.fake_orgasm.flights_management.models.Category;
import com.fake_orgasm.flights_management.models.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * This class has the responsibility of managing ticket data in the database.
 * It provides methods for creating, retrieving, updating, and deleting ticket records.
 */
public class TicketRepository {

    private FlightDatabase database;

    /**
     * This method constructs a TicketRepository and initializes it with a database connection.
     */
    public TicketRepository() {
        database = FlightDatabase.getInstance();
    }

    /**
     * This method creates a Ticket table in the database if it does not already exist.
     */
    public void createTable() {
        try {
            Statement statement = database.getConnection().createStatement();
            String query = "CREATE TABLE IF NOT EXISTS Ticket"
                    + "(id VARCHAR(250) PRIMARY KEY,"
                    + "arrivalNumber VARCHAR(250),"
                    + "priority VARCHAR(250),"
                    + "userId  VARCHAR(250),"
                    + "flightId VARCHAR(250),"
                    + "FOREIGN KEY (flightId) REFERENCES Flight(id)"
                    + ");";
            statement.executeUpdate(query);
            database.createIndexById(statement, "Ticket");
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method creates a ticket record in the database.
     *
     * @param ticket The Ticket object to be created.
     * @return True if the ticket was successfully created, otherwise false.
     */
    public boolean create(Ticket ticket) {
        boolean wasSaved = false;
        if (database.doesNotExist("Ticket", ticket.getId())) {
            return wasSaved;
        }
        try {
            String query = "INSERT INTO Ticket (id, arrivalNumber, priority, "
                    + "userId, flightId) "
                    + "values (?, ?, ?, ?, ?)";
            PreparedStatement ps = database.getConnection().prepareStatement(query);
            ps.setString(1, ticket.getId());
            ps.setInt(2, ticket.getNumber());
            ps.setString(3, ticket.getPriority().getType());
            ps.setInt(4, ticket.getUserId());
            ps.setString(5, ticket.getFlightId());
            ps.execute();
            ps.close();
            wasSaved = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wasSaved;
    }

    /**
     * This method creates multiple ticket records in the database.
     *
     * @param tickets A list of Ticket objects to be created.
     * @return True if all tickets were successfully created, otherwise false.
     */
    public boolean create(ArrayList<Ticket> tickets) {
        boolean wereCreated = false;

        if (tickets == null || tickets.isEmpty()) {
            return wereCreated;
        }

        try {
            String query = "INSERT INTO Ticket "
                    + "(id, arrivalNumber, priority, userId, flightId) "
                    + "VALUES (?, ?, ?, ?, ?)";
            Connection connection = database.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            for (Ticket ticket : tickets) {
                ps.setString(1, ticket.getId());
                ps.setInt(2, ticket.getNumber());
                ps.setString(3, ticket.getPriority().getType());
                ps.setInt(4, ticket.getUserId());
                ps.setString(5, ticket.getFlightId());
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
     * This method retrieves a list of all tickets stored in the database.
     *
     * @return A list of Ticket objects representing all tickets in the database.
     */
    public ArrayList<Ticket> findAll() {
        String query = "SELECT * FROM Ticket;";
        try {
            ArrayList<Ticket> tickets = new ArrayList<>();
            PreparedStatement ps = database.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            String id;
            String priorityType;
            String flightId;
            int arrivalNumber;
            int userId;
            Category category;

            while (rs.next()) {
                id = rs.getString("id");
                arrivalNumber = rs.getInt("arrivalNumber");
                priorityType = rs.getString("priority");
                userId = rs.getInt("userId");
                flightId = rs.getString("flightId");
                category = Category.getCategory(priorityType);

                tickets.add(new Ticket(id, arrivalNumber, category, userId, flightId));
            }
            ps.close();
            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method retrieves a ticket record from the database by its ID.
     *
     * @param id The ID of the ticket to retrieve.
     * @return The Ticket object representing the ticket record, or null if not found.
     */
    public Ticket search(String id) {
        String query = "SELECT * FROM Ticket WHERE id = ?";
        try {
            PreparedStatement ps = database.getConnection().prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int ticketNumber = rs.getInt("arrivalNumber");
                String priorityType = rs.getString("priority");
                int userId = rs.getInt("userId");
                String flightId = rs.getString("flightId");
                Category category = Category.getCategory(priorityType);
                Ticket ticket = new Ticket(id, ticketNumber, category, userId, flightId);

                ps.close();
                return ticket;
            } else {
                ps.close();
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method retrieves a list of ticket records from the database by their IDs.
     *
     * @param ticketIds An array of ticket IDs to retrieve.
     * @return A list of Ticket objects representing the retrieved tickets.
     */
    public ArrayList<Ticket> search(String[] ticketIds) {
        if (ticketIds == null || ticketIds.length == 0) {
            return new ArrayList<>();
        }

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Ticket WHERE id IN (");
        for (int i = 0; i < ticketIds.length; i++) {
            queryBuilder.append("?");
            if (i < ticketIds.length - 1) {
                queryBuilder.append(",");
            }
        }
        queryBuilder.append(")");

        try {
            Connection connection = database.getConnection();
            PreparedStatement ps = connection.prepareStatement(queryBuilder.toString());

            for (int i = 0; i < ticketIds.length; i++) {
                ps.setString(i + 1, ticketIds[i]);
            }

            ResultSet rs = ps.executeQuery();
            ArrayList<Ticket> tickets = new ArrayList<>();

            String id;
            String priorityType;
            String flightId;
            int arrivalNumber;
            int userId;
            Category category;

            while (rs.next()) {
                id = rs.getString("id");
                arrivalNumber = rs.getInt("arrivalNumber");
                priorityType = rs.getString("priority");
                userId = rs.getInt("userId");
                flightId = rs.getString("flightId");
                category = Category.getCategory(priorityType);
                tickets.add(new Ticket(id, arrivalNumber, category, userId, flightId));
            }

            ps.close();
            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method updates a ticket record in the database.
     *
     * @param id     The ID of the ticket to update.
     * @param ticket The Ticket object with updated information.
     * @return True if the ticket was successfully updated, otherwise false.
     */
    public boolean update(String id, Ticket ticket) {
        boolean wasUpdated = false;
        try {
            if (!database.doesNotExist("Ticket", id)) {
                String query = "UPDATE Ticket SET arrivalNumber=?, priority=?, " + "userId=?, flightId=? WHERE id=?";
                PreparedStatement ps = database.getConnection().prepareStatement(query);
                ps.setInt(1, ticket.getNumber());
                ps.setString(2, ticket.getPriority().getType());
                ps.setInt(3, ticket.getUserId());
                ps.setString(4, ticket.getFlightId());
                ps.setString(5, id);
                int rowsUpdated = ps.executeUpdate();
                ps.close();

                if (rowsUpdated > 0) {
                    wasUpdated = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wasUpdated;
    }

    /**
     * This method deletes a ticket record from the database.
     *
     * @param id The ID of the ticket to delete.
     * @return True if the ticket was successfully deleted, otherwise false.
     */
    public boolean delete(String id) {
        return database.delete("Flight", id);
    }
}

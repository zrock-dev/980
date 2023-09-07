package com.fake_orgasm.flights_management.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class has the responsibility of managing the database connection and providing
 * utility methods for database operations related to flight management.
 */
public class FlightDatabase {

    private static FlightDatabase flightDatabase;
    private Connection connection;

    /**
     * This method retrieves a singleton instance of the FlightDatabase.
     *
     * @return The singleton instance of the FlightDatabase.
     */
    public static FlightDatabase getInstance() {
        if (flightDatabase == null) {
            flightDatabase = new FlightDatabase();
        }

        return flightDatabase;
    }

    /**
     * This method retrieves a database connection. If the connection does not exist, it is created.
     *
     * @return The database connection.
     */
    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                try {
                    connection = DriverManager.getConnection("jdbc:sqlite:flight_management.db");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    /**
     * This method creates an index on the ID field of a table.
     *
     * @param statement The database statement.
     * @param tableName The name of the table on which to create the index.
     */
    public void createIndexById(Statement statement, String tableName) {
        try {
            String indexName = "idx_" + tableName.toLowerCase() + "_id";
            String createIndex = "CREATE INDEX IF NOT EXISTS " + indexName + " ON " + tableName + " (id);";
            statement.executeUpdate(createIndex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method checks if a record with a specific ID exists in a table.
     *
     * @param tableName The name of the table to search.
     * @param id        The ID to search for.
     * @return True if the record exists, otherwise false.
     */
    public boolean exists(String tableName, String id) {
        boolean exists;
        try {
            String query = "SELECT COUNT(*) FROM " + tableName + " WHERE id=?";
            PreparedStatement ps = getConnection().prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            exists = rs.next() && rs.getInt(1) > 0;
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exists;
    }

    /**
     * This method deletes a record with a specific ID from a table.
     *
     * @param tableName The name of the table from which to delete the record.
     * @param id        The ID of the record to delete.
     * @return True if the record was successfully deleted, otherwise false.
     */
    public boolean delete(String tableName, String id) {
        boolean wasDeleted = false;
        try {
            String query = "DELETE FROM " + tableName + " WHERE id = ?";
            PreparedStatement ps = getConnection().prepareStatement(query);
            ps.setString(1, id);
            ps.execute();
            ps.close();
            wasDeleted = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return wasDeleted;
    }

    /**
     * This method deletes all records from the specified database table.
     * <p>
     * This method attempts to delete all records from the specified database table
     * using a SQL "TRUNCATE TABLE" query.
     *
     * @param tableName The name of the database table from which all records will be deleted.
     * @return true if all records were successfully deleted, false otherwise.
     */
    public boolean deleteAll(String tableName) {
        boolean wasDeleted = false;
        try {
            String query = "DELETE FROM " + tableName;
            Statement statement = getConnection().createStatement();
            statement.executeUpdate(query);
            statement.close();
            wasDeleted = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return wasDeleted;
    }

    /**
     * This method executes a batch of SQL statements and checks if they were successfully executed.
     *
     * @param ps The prepared statement with a batch of SQL statements.
     * @return True if all statements were successfully executed, otherwise false.
     */
    public boolean wereCreated(PreparedStatement ps) {
        boolean wereCreated = false;
        try {
            int[] batchResult = ps.executeBatch();
            for (int result : batchResult) {
                if (result != PreparedStatement.SUCCESS_NO_INFO && result != PreparedStatement.EXECUTE_FAILED) {
                    wereCreated = true;
                } else {
                    wereCreated = false;
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return wereCreated;
    }
}

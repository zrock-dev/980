package com.fake_orgasm.flights_management.repository;

import java.sql.*;

public class FlightDatabase {

    private static FlightDatabase flightDatabase;
    private Connection connection;

    public static FlightDatabase getInstance() {
        if (flightDatabase == null) {
            flightDatabase = new FlightDatabase();
        }

        return flightDatabase;
    }

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

    public void createIndexById(Statement statement, String tableName) {
        try {
            String indexName = "idx_" + tableName.toLowerCase() + "_id";
            String createIndex = "CREATE INDEX IF NOT EXISTS " + indexName +
                    " ON " + tableName + " (id);";
            statement.executeUpdate(createIndex);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean doesNotExist(String tableName, String id) {
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

    public boolean delete(String tableName, String id) {
        boolean wasDeleted = false;
        try {
            String query = "DELETE FROM " + tableName + " WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ps.execute();
            ps.close();
            wasDeleted = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return wasDeleted;
    }

    public boolean wereCreated(PreparedStatement ps) {
        boolean wereCreated = false;
        try {
            int[] batchResult = ps.executeBatch();
            for (int result : batchResult) {
                if (result != PreparedStatement.SUCCESS_NO_INFO
                        && result != PreparedStatement.EXECUTE_FAILED) {
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
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
}
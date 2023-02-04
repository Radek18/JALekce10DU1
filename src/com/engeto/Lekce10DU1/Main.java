package com.engeto.Lekce10DU1;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flowershop", "radek", "flowershop123")) {
            Statement statement = connection.createStatement();

            insertNewFlower(statement, "Bledule", "Bílá", true);
            insertNewFlower(statement, "Kopretina", "Bílá", false);

            updateDescription(statement, "bledule", "Pozor na cibulku - obsahuje největší koncentraci jedu!");

            deleteNoPoisonousFlowers(statement);

            printAllFlowers(statement);

        } catch (SQLException e) {
            System.err.println("Chyba při komunikaci s databází: " + e.getMessage());
        }

    }

    private static void insertNewFlower(Statement statement, String name, String colour, boolean isPoisonous) throws SQLException {
        statement.executeUpdate("INSERT INTO flower (name, colour, isPoisonous) VALUES ('" + name + "', '" + colour + "', " + isPoisonous + ")");
    }

    private static void updateDescription(Statement statement, String name, String description) throws SQLException {
        statement.executeUpdate("UPDATE flower SET description = '" + description + "'WHERE name = '" + name + "'");
    }

    private static void deleteNoPoisonousFlowers(Statement statement) throws SQLException {
        statement.executeUpdate("DELETE FROM flower WHERE isPoisonous = 0");
    }

    private static void printAllFlowers(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT name FROM flower");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
        }
    }

}
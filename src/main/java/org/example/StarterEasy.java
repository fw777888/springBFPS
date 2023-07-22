package org.example;

import java.sql.*;

public class StarterEasy {
    public static void main(String[] args) {
        String createTable = """
                CREATE TABLE IF NOT EXISTS person  (
                    username VARCHAR(30),
                    password VARCHAR(30)
                )
                """;

        String saveUser = """
                INSERT INTO person(username, password) VALUES (?, ?)
                """;

        String getUser = """
                SELECT username, password 
                FROM 
                    person
                WHERE 
                    username = ?;
                """;

        String DB_USERNAME = "postgres";
        String DB_PASSWORD = "root";
        String DB_URL = "jdbc:postgresql://localhost:4444/postgres";

        String username = "Max";
        String password = "Password";

        try (var connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            final var statement = connection.createStatement();
            statement.execute(createTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (var connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            final var preparedStatement = connection.prepareStatement(saveUser);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (var connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            final var preparedStatement = connection.prepareStatement(getUser);
            preparedStatement.setString(1, "Max");

            final var resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final var nameNew = resultSet.getString("username");
                final var passwordNew = resultSet.getString("password");

                System.out.println("name: " + nameNew + " password: " + passwordNew);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

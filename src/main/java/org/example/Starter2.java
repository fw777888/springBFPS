package org.example;

import java.sql.*;

public class Starter2 {
    public static void main(String[] args) {
        String createTable = """
                CREATE TABLE IF NOT EXISTS person_table(username VARCHAR(30), password VARCHAR(30)) 
                """;
        String deleteTable = """
                DROP TABLE IF EXISTS person_table
                """;
        String findByUserName = """
                SELECT username, password FROM person_table
                WHERE username = ?
                """;

        String updateUserSql = """
                UPDATE person_table  SET username = ?, password = ?
                WHERE username = ?
                """;
        String saveUserSql = """
                INSERT INTO person_table (username, password) VALUES (?, ?)
                """;
        String deleteUserSql = """
                DELETE FROM person_table WHERE username = ?
                """;
        String username = "username111";
        String password = "password111";

        String urlDb = "jdbc:postgresql://localhost:2222/postgres";
        String dbName = "postgres";
        String dbPassword = "root";

        try (Connection connection = DriverManager.getConnection(urlDb, dbName, dbPassword)) {
            Statement statement = connection.createStatement();
            statement.execute(createTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = DriverManager.getConnection(urlDb, dbName, dbPassword)) {
            PreparedStatement statement = connection.prepareStatement(saveUserSql);
            statement.setString(1, "Alex");
            statement.setString(2, "alexpassword");
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = DriverManager.getConnection(urlDb, dbName, dbPassword)) {
            PreparedStatement statement = connection.prepareStatement(updateUserSql);
            statement.setString(1, "NewUser");
            statement.setString(2, "NewPassword");
            statement.setString(3, "Alex" );
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = DriverManager.getConnection(urlDb, dbName, dbPassword)) {
            PreparedStatement statement = connection.prepareStatement(findByUserName);
            statement.setString(1, "NewUser");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
                System.out.println(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = DriverManager.getConnection(urlDb, dbName, dbPassword)) {
            PreparedStatement statement = connection.prepareStatement(deleteUserSql);
            statement.setString(1, "NewUser");
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

package org.example.easy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
@PropertySource("classpath:application.properties")
public class PersonDao {

    private final String CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS person_table(username VARCHAR(30), password VARCHAR(30)) 
            """;

    private final String DELETE_TABLE = """
            DROP TABLE IF EXISTS person_table
            """;

    private final String FIND_BY_USERNAME = """
            SELECT username, password FROM person_table
            WHERE username = ?
            """;

    private final String UPDATE_USER_SQL = """
            UPDATE person_table  SET username = ?, password = ?
            WHERE username = ?
            """;

    private final String SAVE_USER_SQL = """
            INSERT INTO person_table (username, password) VALUES (?, ?)
            """;

    private final String DELETE_USER_SQL = """
            DELETE FROM person_table WHERE username = ?
            """;

    @Value("${db.url}")
    private String DB_URL;

    @Value("${db.username}")
    private String DB_NAME;

    @Value("${db.password}")
    private String DB_PASSWORD;

    public void createTable() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.execute(CREATE_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropTable() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.execute(DELETE_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(String username, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SAVE_USER_SQL);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String newUsername, String newPassword, String username) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL);
            statement.setString(1, newUsername);
            statement.setString(2, newPassword);
            statement.setString(3, username);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void findByUsername(String username) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_USERNAME);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
                System.out.println(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String username) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL);
            statement.setString(1, username);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

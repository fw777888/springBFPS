package org.example.easy2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.sql.*;
@Component
@PropertySource("classpath:application.properties")
public class CarDao {
    private final String CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS car_table(model VARCHAR(30), color VARCHAR(30)) 
            """;

    private final String DELETE_TABLE = """
            DROP TABLE IF EXISTS car_table
            """;

    private final String FIND_BY_MODEL = """
            SELECT model, color FROM car_table
            WHERE model = ?
            """;

    private final String UPDATE_CAR_SQL = """
            UPDATE car_table  SET model = ?, color = ?
            WHERE model = ?
            """;

    private final String SAVE_MODEL_SQL = """
            INSERT INTO car_table (model, color) VALUES (?, ?)
            """;

    private final String DELETE_CAR_SQL = """
            DELETE FROM car_table WHERE model = ?
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
    public void save(String model, String color) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD)) {
            System.out.println();
            PreparedStatement statement = connection.prepareStatement(SAVE_MODEL_SQL);
            statement.setString(1, model);
            statement.setString(2, color);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            System.out.println();
    }
    public void update(String newModel, String newColor, String model) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_CAR_SQL);
            statement.setString(1, newModel);
            statement.setString(2, newColor);
            statement.setString(3, model);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void findByModel(String model) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_MODEL);
            statement.setString(1, model);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("model"));
                System.out.println(resultSet.getString("color"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String model) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(DELETE_CAR_SQL);
            statement.setString(1, model);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

package org.example.easyDzFile1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.*;

@Component
@PropertySource("classpath:application.properties")

public class BikeDao {
    private final String CRATE_TABLE_BIKE = """
            CREATE TABLE IF NOT EXISTS table_bike(
                    model VARCHAR(30), 
                    power smallint, 
                    isElectric BOOLEAN, 
                    price money)
            """;
    private final String SAVE_NEW_BIKE_INTO_TABLE = """
                INSERT INTO table_bike(model, power, iselectric, price) VALUES (?, ?, ?, ?)
                """;
    private final String UPDATE_BIKE_FROM_TABLE = """
                    UPDATE table_bike SET price=? WHERE  model=?
                """;

    private final String FIND_BY_ID = """
                SELECT model, power, iselectric, price  FROM table_bike WHERE model=?
                """;

    private final String DELETE_BIKE_FROM_TABLE = """
                DELETE FROM table_bike WHERE model=?
                """;
    private final String DROP_BIKE_TABLE = """
                DROP TABLE IF EXISTS table_bike 
                """;

    @Value("jdbc:postgresql://localhost:2222/postgres")
    private String DB_URL;
    @Value("postgres")
    private String DB_NAME;
    @Value("root")
    private String DB_PASSWORD;

    public void createTableBike() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD)) {
            System.out.println("Creating a table bike");
            Statement statement = connection.createStatement();
            statement.execute(CRATE_TABLE_BIKE);
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public void saveNewBikeIntoTable(String model, short power, boolean iselectric, BigDecimal price) {
        try (var connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD)) {
            System.out.println("Creating New Bike ");
            var preparedStatement = connection.prepareStatement(SAVE_NEW_BIKE_INTO_TABLE);
            preparedStatement.setString(1, model);
            preparedStatement.setShort(2, power);
            preparedStatement.setBoolean(3, iselectric);
            preparedStatement.setBigDecimal(4, price);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            System.out.println("Bike is saved");
    }

        public void updateBikeFromTable(BigDecimal price, String model) {
            try (var connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD)) {
                System.out.println("Updating a price");
                var statement = connection.prepareStatement(UPDATE_BIKE_FROM_TABLE);
                statement.setBigDecimal(1, price);
                statement.setString(2, model);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public void findById(String model) {
            try (var connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD)) {
                System.out.println("Find by id");
                var statement = connection.prepareStatement(FIND_BY_ID);
                statement.setString(1, model);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("model"));
                    System.out.println(resultSet.getString("power"));
                    System.out.println(resultSet.getString("isElectric"));
                    System.out.println(resultSet.getString("price"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public void deleteBikeFromTable(String model) {
            try (var connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD)) {
                System.out.println("Deleting from table");
                var statement = connection.prepareStatement(DELETE_BIKE_FROM_TABLE);
                statement.setString(1, model);
                statement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Bike deleted");
        }

        public void dropBikeTable() {
            try (var connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD)) {
                System.out.println("drop the table");
                var statement = connection.createStatement();
                statement.executeUpdate(DROP_BIKE_TABLE);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Table bike deleted");
        }
}

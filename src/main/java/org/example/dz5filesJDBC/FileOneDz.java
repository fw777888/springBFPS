package org.example.dz5filesJDBC;

import java.math.BigDecimal;
import java.sql.*;

public class FileOneDz {
    public static void main(String[] args) {
        String urlDb = "jdbc:postgresql://localhost:2222/postgres";
        String dbName = "postgres";
        String dbPassword = "root";
//        String model = "Bike";
//        short power = 100;
//        boolean isElectric = false;
//        BigDecimal price = BigDecimal.valueOf(88888);

        String createTableBike = """
                CREATE TABLE IF NOT EXISTS table_bike(
                    model VARCHAR(30), 
                    power smallint, 
                    isElectric BOOLEAN, 
                    price money)
                """;
        String saveNewBikeIntoTable = """
                INSERT INTO table_bike(model, power, iselectric, price) VALUES (?, ?, ?, ?)
                """;
        String updateBikeFromTable = """
                    UPDATE table_bike SET price=? WHERE  model=?
                """;

        String findById = """
                SELECT model, power, iselectric, price  FROM table_bike WHERE model=?
                """;

        String deleteBikeFromTable = """
                DELETE FROM table_bike WHERE model=?
                """;
        String dropBikeTable = """
                DROP TABLE IF EXISTS table_bike 
                """;

        try (Connection connection = DriverManager.getConnection(urlDb, dbName, dbPassword)) {
            System.out.println("Creating a table bike");
        Statement statement = connection.createStatement();
        statement.execute(createTableBike);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (var connection = DriverManager.getConnection(urlDb, dbName, dbPassword)) {
            System.out.println("Creating New Bike ");
            final var preparedStatement = connection.prepareStatement(saveNewBikeIntoTable);
            preparedStatement.setString(1, "Kawasaki");
            preparedStatement.setShort(2, (short) 180);
            preparedStatement.setBoolean(3, false);
            preparedStatement.setBigDecimal(4, BigDecimal.valueOf(1_500_000.00));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (var connection = DriverManager.getConnection(urlDb, dbName, dbPassword)) {
            System.out.println("Updating a price");
            var statement = connection.prepareStatement(updateBikeFromTable);
            statement.setBigDecimal(1, BigDecimal.valueOf(1700000.00));
            statement.setString(2, "Kawasaki");
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (var connection = DriverManager.getConnection(urlDb, dbName, dbPassword)) {
            System.out.println("Find by id");
            var statement = connection.prepareStatement(findById);
            statement.setString(1, "Kawasaki");
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

        try (var connection = DriverManager.getConnection(urlDb, dbName, dbPassword)) {
            System.out.println("Deleting from table");
            var statement = connection.prepareStatement(deleteBikeFromTable);
            statement.setString(1,"Kawasaki");
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (var connection = DriverManager.getConnection(urlDb, dbName, dbPassword)) {
            System.out.println("drop the table");
            var statement = connection.createStatement();
            statement.executeUpdate(dropBikeTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

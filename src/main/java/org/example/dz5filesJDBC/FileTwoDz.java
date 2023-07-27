package org.example.dz5filesJDBC;

import java.sql.*;

public class FileTwoDz {
        public static void main(String[] args) {
            String urlDb = "jdbc:postgresql://localhost:2222/postgres";
            String dbName = "postgres";
            String dbPassword = "root";

            String createTableLibrary = """
                    CREATE TABLE IF NOT EXISTS table_library(
                    id SERIAL PRIMARY KEY ,
                    author VARCHAR(30),
                    book_name VARCHAR(30),
                    cost INTEGER)
                    """;

            String saveNewBook = """
                    INSERT INTO table_library(author, book_name, cost) VALUES (?, ?, ?)
                    """;
            String updateBooksCost = """
                    UPDATE table_library SET cost=? WHERE book_name = ?
                    """;
            String findByAuthor = """
                    SELECT id, author, book_name, cost FROM table_library WHERE author=?
                    """;
            String deleteBook = """
                    DELETE FROM table_library WHERE author=?
                    """;
            String deleteTableLibrary = """
                    DROP TABLE IF EXISTS table_library
                    """;

            try (var connection = DriverManager.getConnection(urlDb, dbName, dbPassword)) {
                System.out.println("Creating a table Library");
                var statement = connection.createStatement();
                statement.execute(createTableLibrary);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("table Library is created");

            try (var connection = DriverManager.getConnection(urlDb, dbName, dbPassword)) {
                System.out.println("Saving a new book");
                var statement = connection.prepareStatement(saveNewBook);
                statement.setString(1, "Роберт Маккаммон");
                statement.setString(2, "Всадник авангарда");
                statement.setInt(3, 1500);
                statement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Book is saved");

            try (var connection = DriverManager.getConnection(urlDb, dbName, dbPassword)) {
                System.out.println("Updating a cost");
                var statement = connection.prepareStatement(updateBooksCost);
                statement.setInt(1, 1400);
                statement.setString(2, "Всадник авангарда");
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Cost updated");

            try (var connection = DriverManager.getConnection(urlDb, dbName, dbPassword)) {
                System.out.println("Find a book by author");
                var statement = connection.prepareStatement(findByAuthor);
                statement.setString(1, "Роберт Маккаммон");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("id"));
                    System.out.println(resultSet.getString("author"));
                    System.out.println(resultSet.getString("book_name"));
                    System.out.println(resultSet.getString("cost"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try (var connection = DriverManager.getConnection(urlDb, dbName, dbPassword)) {
                System.out.println("Delete a book from library");
                var statement = connection.prepareStatement(deleteBook);
                statement.setString(1, "Роберт Маккаммон");
                statement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("book is deleted");

            try (var connection = DriverManager.getConnection(urlDb, dbName, dbPassword)) {
                System.out.println("Drop the library");
                var statement = connection.createStatement();
                statement.executeUpdate(deleteTableLibrary);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }


}

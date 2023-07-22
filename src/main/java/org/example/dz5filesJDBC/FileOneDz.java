package org.example.dz5filesJDBC;

public class FileOneDz {
    public static void main(String[] args) {
        String urlDb = "jdbc:postgresql://localhost:8888/postgres";
        String dbName = "postgres";
        String dbPassword = "root";

        String createTableBike = """
                CREATE TABLE IF NOT EXISTS table_bike(model VARCHAR(30), power smallint, isElectric BOOLEAN, prise money)
                """;
    }
}

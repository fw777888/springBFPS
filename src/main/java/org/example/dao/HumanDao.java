package org.example.dao;

import org.example.model.Human;
import org.example.util.ConnectionManager;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component
public class HumanDao implements Dao<Human, Long> {

    private final ConnectionManager connectionManager;

    private final String CREATE_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS human(
                id BIGSERIAL PRIMARY KEY,
                firstname VARCHAR(30),
                lastname VARCHAR(40),
                age INT
            );
            """;

    private final String DROP_TABLE_SQL = """
            DROP TABLE IF EXISTS human;
            """;

    private final String SAVE_SQL = """
            INSERT INTO 
                human (
                firstname,
                lastname, 
                age)
                VALUES (
                    ?,
                    ?,
                    ?
                );
            """;

    private final String FIND_BY_ID_SQL = """
            SELECT (
                id, 
                firstname, 
                lastname, 
                age)
            FROM 
                human
            WHERE 
                id = ?
            """;

    private final String UPDATE_SQL = """
            UPDATE human SET
                firstname = ?,
                lastname = ?,
                age = ?
            WHERE id = ?
            """;

    private final String FIND_ALL = """
            SELECT (
                id,
                firstname,
                lastname,
                age)
            FROM human
            """;

    public HumanDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void createTable() {
        try (var connection = connectionManager.get();
             final var preparedStatement = connection.prepareStatement(CREATE_TABLE_SQL)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void DeleteTable() {
        try (var connection = connectionManager.get();
            final var preparedStatement = connection.prepareStatement(DROP_TABLE_SQL)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Human findById(Long id) {
//        try(var connection = connectionManager.get();
//            final var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
//            preparedStatement.setLong(1, id);
//            var resultSet = preparedStatement.executeQuery();
//            Human human = null;
//
//            if (resultSet.next()) {
//                human = buildHuman(resultSet);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } return Human
    return null;
    }

    @Override
    public List<Human> findAll() {
        return null;
    }

    @Override
    public Human save(Human entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Human update(Human entity) {
        return null;
    }
}

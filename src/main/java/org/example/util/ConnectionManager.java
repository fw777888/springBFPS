package org.example.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ConnectionManager {

    private String DB_USERNAME;
    private String DB_PASSWORD;
    private String DB_URL;
    private String DB_POOL;
    private final static int DEFAULT_POOL_SIZE = 5;
    private static BlockingQueue<Connection> pool; // [1,2,3,4,5]
    private static List<Connection> sourceConnection;

    public ConnectionManager(String DB_USERNAME, String DB_PASSWORD, String DB_URL, String DB_POOL) {
        this.DB_USERNAME = DB_USERNAME;
        this.DB_PASSWORD = DB_PASSWORD;
        this.DB_URL = DB_URL;
        this.DB_POOL = DB_POOL;
    }

    static {
        loadDriver();
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void initConnectionPool() {
        int poolSize = DB_POOL == null ? DEFAULT_POOL_SIZE
                : Integer.parseInt(DB_POOL);

        pool = new ArrayBlockingQueue<>(poolSize);
        sourceConnection = new ArrayList<>(poolSize);

        for (int i = 0; i < poolSize; i++) {
            Connection connection = open();
            // Proxy(connection).close
            var proxyConnection = (Connection)
                    Proxy.newProxyInstance(ConnectionManager.class.getClassLoader(), new Class[]{Connection.class},
                            (proxy, method, args) -> method.getName().equals("close")
                                    ? pool.add((Connection) proxy)
                                    : method.invoke(connection, args));
            pool.add(proxyConnection);
            sourceConnection.add(connection);
        }
    }

    private Connection open() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection get() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void closePool() {
        for (Connection connection : sourceConnection) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

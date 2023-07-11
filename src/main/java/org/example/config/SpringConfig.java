package org.example.config;

import org.example.util.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.awt.print.Book;

@Configuration
@ComponentScan(basePackages = "org.example")
@PropertySource("classpath:application.properties")
public class SpringConfig {

    @Value("${db.username}")
    private String DB_USERNAME;
    @Value("${db.password}")
    private String DB_PASSWORD;
    @Value("${db.url}")
    private String DB_URL;
    @Value("${db.pool}")
    private String DB_POOL;

    @Bean(initMethod = "initConnectionPool")
    ConnectionManager connectionManager() {
        return new ConnectionManager(DB_USERNAME, DB_PASSWORD, DB_URL, DB_POOL);
    }
}

package org.example;

import org.example.config.SpringConfig;
import org.example.dao.HumanDao;
import org.example.util.ConnectionManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;

public class Starter {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        final var utilConnection = context.getBean("connectionManager", ConnectionManager.class);

        final var humanDao = context.getBean("humanDao", HumanDao.class);
        humanDao.createTable();
    }
}

package org.example.easy2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StarterEasy2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigEasy2.class);
        final var carDao = context.getBean("carDao", org.example.easy2.CarDao.class);
        carDao.createTable();
        carDao.save("Wv","White");
        carDao.save("UAZ", "Green");
        carDao.findByModel("Wv");
        carDao.update("Audi","Black", "Wv");
        carDao.delete("UAZ");

    }
}

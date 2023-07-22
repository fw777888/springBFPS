package org.example.easy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StarterVersionTwo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigTwo.class);
        final var personDao = context.getBean("personDao", PersonDao.class);
//        PersonDao personDao = new PersonDao();
//        personDao.createTable();
//        personDao.save("Alex", "alexTwo");
        personDao.findByUsername("Alex");
    }
}

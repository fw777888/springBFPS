package org.example.easyDzFile1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class StartertEasyDzFile1 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringEasyDz1Config.class);
        final var bikeDao = context.getBean("bikeDao", org.example.easyDzFile1.BikeDao.class);
        bikeDao.createTableBike();
        bikeDao.saveNewBikeIntoTable("Kawasaki", (short)200, false, BigDecimal.valueOf(1700000));
        bikeDao.saveNewBikeIntoTable("Honda", (short)210, false, BigDecimal.valueOf(1800000));
        bikeDao.saveNewBikeIntoTable("Honda", (short)210, false, BigDecimal.valueOf(1800000));
        bikeDao.updateBikeFromTable(BigDecimal.valueOf(1850000), "Kawasaki");
        bikeDao.findById("Kawasaki");
        bikeDao.deleteBikeFromTable("Honda");
        bikeDao.dropBikeTable();


    }
}

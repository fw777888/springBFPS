package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Laptop {
    String model;
    int ram;

    public Laptop(@Value(value = "Asus")String model, @Value(value = "16") int ram) {
        this.model = model;
        this.ram = ram;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "model='" + model + '\'' +
                ", ram=" + ram +
                '}';
    }
}

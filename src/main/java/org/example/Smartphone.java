package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Smartphone {
    String model;

    public Smartphone(@Value(value = "Honor") String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Smartphone{" +
                "model='" + model + '\'' +
                '}';
    }
}

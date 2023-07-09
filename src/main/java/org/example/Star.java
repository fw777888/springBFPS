package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Star {
    String name;
    String constellation;
    String spectralType;
    int distanceLightYears;

    public Star(@Value(value = "Betelgeuse") String name,
                @Value(value = "Orion") String constellation,
                @Value(value = "M1-2") String spectralType,
                @Value(value = "600") int distanceLightYears) {
        this.name = name;
        this.constellation = constellation;
        this.spectralType = spectralType;
        this.distanceLightYears = distanceLightYears;
    }

    @Override
    public String toString() {
        return "Star{" +
                "name='" + name + '\'' +
                ", constellation='" + constellation + '\'' +
                ", spectralType='" + spectralType + '\'' +
                ", distanceLightYears=" + distanceLightYears +
                '}';
    }
}

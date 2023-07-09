package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Book {
    String author;
    String name;
    String genre;
    int year;

    public Book(@Value(value = "Robert Martin") String author,
                @Value(value = "Clean Architecture") String name,
                @Value(value = "Computer science") String genre,
                @Value(value = "2017") int year) {
        this.author = author;
        this.name = name;
        this.genre = genre;
        this.year = year;
    }

    @Override
    public String
    toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                '}';
    }
}

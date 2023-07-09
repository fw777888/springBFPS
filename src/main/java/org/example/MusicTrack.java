package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MusicTrack {
    String author;
    String trackName;
    String genre;
    int year;

    public MusicTrack(@Value(value = "Ovnimoon") String author,
                      @Value(value = "Galactic mantra") String trackName,
                      @Value(value = "psytrance") String genre,
                      @Value(value = "2009") int year) {
        this.author = author;
        this.trackName = trackName;
        this.genre = genre;
        this.year = year;
    }

    @Override
    public String toString() {
        return "MusicTrack{" +
                "author='" + author + '\'' +
                ", trackName='" + trackName + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                '}';
    }
}

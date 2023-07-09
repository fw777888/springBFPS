package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sound.midi.Track;

public class Main {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springConfig.xml");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        var human = context.getBean("human", Human.class);
        var man = context.getBean("man",Man.class);
        var cat = context.getBean("cat", Cat.class);
        var smartphone = context.getBean("smartphone", Smartphone.class);
        var laptop = context.getBean("laptop", Laptop.class);
        var book = context.getBean("book", Book.class);
        var musicTrack = context.getBean("musicTrack", MusicTrack.class);
        var star = context.getBean("star", Star.class);
        System.out.println(human);
        System.out.println(man);
        System.out.println(cat);
        System.out.println(smartphone);
        System.out.println(laptop);
        System.out.println(book);
        System.out.println(musicTrack);
        System.out.println(star);
    }
}
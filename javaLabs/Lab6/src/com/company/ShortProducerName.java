package com.company;

public class ShortProducerName extends Exception {
    String name;
    ShortProducerName(String name){
        super("Name *" + name +"* is shorter than 5 characters");
    }
}

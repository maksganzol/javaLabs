package com.company;

public class ShortProductName extends Exception {
    String name;
    ShortProductName(String name){
        super("Name *" + name +"* is shorter than 3 characters");
    }
}

package com.company;

public class ArrayOwerflow extends Exception {
    ArrayOwerflow(int size){
        super("Array is full (max index: " + (size - 1) + ")");
    }

}

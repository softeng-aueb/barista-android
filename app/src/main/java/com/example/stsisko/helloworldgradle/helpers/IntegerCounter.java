package com.example.stsisko.helloworldgradle.helpers;

public class IntegerCounter {

    private static int counter = 0;

    public static int getNextTick(){
        counter ++;
        return counter;
    }

    public static void resetCounter(){
        counter = -1;
    }
}

package com.example.baristademoapp.helpers;

public class ThreadSleepingTime {
    public static int sleepingTime = 500;

    public static void setSlowTimer(){
        sleepingTime = 1000;
    }

    public static void setNormalTimer(){
        sleepingTime = 500;
    }

    public static void setFastTimer(){
        sleepingTime = 200;
    }


}

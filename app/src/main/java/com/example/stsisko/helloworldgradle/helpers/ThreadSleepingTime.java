package com.example.stsisko.helloworldgradle.helpers;

public class ThreadSleepingTime {
    public static int proccessSpeed = proccessSpeed = ProccessSpeedModes.NORMAL;;

    public static void setSlowTimer(){
        proccessSpeed = ProccessSpeedModes.SLOW;
    }

    public static void setNormalTimer(){
        proccessSpeed = ProccessSpeedModes.NORMAL;
    }

    public static void setFastTimer(){
        proccessSpeed = ProccessSpeedModes.FAST;
    }


}

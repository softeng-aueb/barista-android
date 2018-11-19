#!/bin/bash
DEVICE=$2
if [ -z "$2" ]
  then
    DEVICE="emulator-5554"
fi

echo "pidcat on $DEVICE"

./pidcat.py -c -s $DEVICE  package com.example.stsisko.helloworldgradle -i "dalvikvm|MonitoringInstrumentation|LifecycleMonitor|OpenGLRenderer|TestExecutor|MultiDex|DynamiteModule|ESP_TRACE|UiControllerImpl|EGL_emulation|HardwareRenderer|art|Glide$2"   --clear

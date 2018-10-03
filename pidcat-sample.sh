#!/bin/bash

./pidcat.py -c -s emulator-5554  package gr.aueb.android.barista.sample -i "dalvikvm|MonitoringInstrumentation|LifecycleMonitor|OpenGLRenderer|TestExecutor|MultiDex|DynamiteModule|ESP_TRACE|UiControllerImpl|EGL_emulation|HardwareRenderer|art"   --clear

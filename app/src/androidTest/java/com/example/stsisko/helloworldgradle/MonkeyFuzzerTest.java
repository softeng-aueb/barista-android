package com.example.stsisko.helloworldgradle;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import gr.aueb.android.barista.core.annotations.Data;
import gr.aueb.android.barista.core.annotations.Monkey;
import gr.aueb.android.barista.core.annotations.enumarations.NetworkAdapterStateType;
import gr.aueb.android.barista.core.inline.Barista;

@RunWith(AndroidJUnit4.class)
public class MonkeyFuzzerTest {

    @Test
    //@Monkey(seed = 1234, count = 1000, throttle = 100, apk_name = BuildConfig.APPLICATION_ID)
    //@Data(NetworkAdapterStateType.DISABLED)
    public void testMonkey() {
        Barista.setDataState(NetworkAdapterStateType.DISABLED);
        System.out.println("HAHA");
    }
}

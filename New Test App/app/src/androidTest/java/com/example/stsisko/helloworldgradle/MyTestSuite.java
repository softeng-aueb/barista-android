package com.example.stsisko.helloworldgradle;

import com.example.stsisko.helloworldgradle.CustomInstrumentedTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CustomInstrumentedTest.class
})
public class MyTestSuite {
}

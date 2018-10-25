package com.example.stsisko.helloworldgradle;


import org.junit.runner.RunWith;

import gr.aueb.android.barista.core.MyTestRunner;
import gr.aueb.android.barista.core.annotations.SaySomething;

@RunWith(MyTestRunner.class)
public class CustomInstrumentedTest {

    @SaySomething
    int field1;

    @SaySomething
    String filed2;

    @SaySomething
    public void test1(){

    }

    @SaySomething
    public void test2(){

    }

    @SaySomething
    public void test4(){

    }

    @SaySomething(param1="test3",param2 = "test3.2")
    public void test3(){

    }

}

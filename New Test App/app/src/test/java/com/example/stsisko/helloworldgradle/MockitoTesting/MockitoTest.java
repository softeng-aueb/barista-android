package com.example.stsisko.helloworldgradle.MockitoTesting;

import android.content.Context;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.junit.Assert.*;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoTest {


    @Test
    public void testQuery(){

        FakeDatabase db = mock(FakeDatabase.class);
        when(db.query("test-query")).thenReturn("Fake DB results");

        assertEquals(db.query("test-query"),"Fake DB results");

    }

    @Test
    public void TestManyReturnValues(){
        Iterator<String>  i = mock(Iterator.class);
        when(i.next()).thenReturn("Mockito").thenReturn("Botka").thenReturn("Chipouro");
        String result = i.next() + " "+ i.next() +" "+i.next();
        assertEquals("Mockito Botka Chipouro",result);
    }

    @Test
    public void TestValueBasedOnGivenInput(){
        Comparable<String>  c = mock(Comparable.class);
        when(c.compareTo("Mockito")).thenReturn(1);
        when(c.compareTo("Android")).thenReturn(2);
        assertEquals(1,c.compareTo("Mockito"));
        assertEquals(2,c.compareTo("Android"));

    }

    @Test
    public void TestValueBasedOnAnyInput(){
        Context c = mock(Context.class);
        String result = c.getPackageName();

    }


}

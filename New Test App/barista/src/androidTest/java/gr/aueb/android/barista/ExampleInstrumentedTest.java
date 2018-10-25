package gr.aueb.android.barista;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import gr.aueb.android.barista.core.http_client.BaristaHttpClient;


import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("gr.aueb.android.barista.test", appContext.getPackageName());
    }

    @Test
    public void testRestClient(){

        BaristaHttpClient client = new BaristaHttpClient(null);
        System.out.println("@@@@ CALLING SERVICE");
        String message = client.getStatus();
        assertNotNull(message);
        assertEquals("Hello World from Jersey Servlet Container",message);
        System.out.println("REST RESPONSE: "+ message);

    }

    @Test
    public void testRestClient2(){

        BaristaHttpClient client = new BaristaHttpClient(null);
        System.out.println("@@@@ CALLING SERVICE");
        String message = client.getStatus2();
        assertNotNull(message);
        assertEquals("Hello World from Jersey Servlet Container",message);
        System.out.println("REST RESPONSE: "+ message);

    }
}

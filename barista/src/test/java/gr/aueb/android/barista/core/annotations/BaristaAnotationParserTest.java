package gr.aueb.android.barista.core.annotations;

import android.os.Environment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import gr.aueb.android.barista.core.model.CommandDTO;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BaristaAnotationParserTest {

    @Test
    public void getParsedCommands() {

        Annotation geoFixAnnotation = null ;
        Annotation screenSizeAnnotation = null;

        Method[] allMethods = DummyAnnotatedClass.class.getMethods();
        for(Method m : allMethods){

            if(m.getName().equals("m1")){
                geoFixAnnotation = m.getAnnotation(GeoFix.class);
            }
            else if(m.getName().equals("m2")){
                screenSizeAnnotation = m.getAnnotation(ScreenSize.class);
            }

        }

        assertNotNull(geoFixAnnotation);
        assertNotNull(screenSizeAnnotation);


        Description desc = mock(Description.class);
        when(desc.getAnnotation(GeoFix.class)).thenReturn((GeoFix) geoFixAnnotation);
        when(desc.getAnnotation(ScreenSize.class)).thenReturn((ScreenSize) screenSizeAnnotation);


        Annotation r1 = desc.getAnnotation(GeoFix.class);
        assertNotNull(r1);
        assertThat(((GeoFix) r1).lat(),is(equalTo(new Double(0))));
        assertThat(((GeoFix) r1).longt(),is(equalTo(new Double(2))));

        Annotation r2 = desc.getAnnotation(ScreenSize.class);
        assertNotNull(r2);
        assertThat(((ScreenSize) r2).height(),is(equalTo(600)));
        assertThat(((ScreenSize) r2).width(),is(equalTo(500)));



        List<CommandDTO> commands =  BaristaAnotationParser.getParsedCommands(desc,null);
        assertThat(commands.size(),is(equalTo(2)));
        assertThat(commands.get(0).toString(), is(equalTo("Geofix Comand [2.0,0.0]")));
        assertThat(commands.get(1).toString(), is(equalTo("Set Size Comand [ w:600, h:500]")));



    }

//    @Test
//    public void testJMock(){
//        new MockUp<DefaultBaristaConfigurationReader>(){
//
//            @Mock
//            public String getEmulatorSessionToken(){
//                return "1";
//            }
//        };
//
//        assertThat(DefaultBaristaConfigurationReader.getEmulatorSessionToken(),is(equalTo("1")));
//    }
}
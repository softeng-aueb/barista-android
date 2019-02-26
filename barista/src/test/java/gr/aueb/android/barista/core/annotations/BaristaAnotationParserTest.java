package gr.aueb.android.barista.core.annotations;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import gr.aueb.android.barista.core.model.CommandDTO;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BaristaAnotationParserTest {
    private static final String GEOFIX_ASSERT_STR = "Geofix Comand ["+ConstantValues.lat+","+ConstantValues.longt+"]";
    private static final String SCREENSIZE_ASSERT_STR = "Set Size Comand [ w:"+ConstantValues.width+", h:"+ConstantValues.height+"]";

    private Annotation geoFixAnnotation;
    private Annotation wmSizeAnnotation;

    @Before
    public void initializeAnnotatedMethods(){
        // use the DummyAnnotatedClass to construct a legitimate GeoFix Annotation instance
        try {
           Method geoFixAnnotatedMethod = DummyAnnotatedClass.class.getMethod("m1");
           geoFixAnnotation = geoFixAnnotatedMethod.getAnnotation(GeoFix.class);

            // use the DummyAnnotatedClass to construct a legitimate GeoFix Annotation instance to use as mocked result
            Method ScreenSizeAnnotatedMethod = DummyAnnotatedClass.class.getMethod("m2");
            wmSizeAnnotation = ScreenSizeAnnotatedMethod.getAnnotation(ScreenSize.class);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void getParsedAnnotations() {

        // Create Barista Annotation instances based on DummyAnnotatedClass anotated methods
        Annotation geoFixAnnotation = null ;
        Annotation screenSizeAnnotation = null;

        Method[] allMethods = DummyAnnotatedClass.class.getMethods();

        for(Method m : allMethods){
            //
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
        assertThat(((GeoFix) r1).lat(),is(equalTo(ConstantValues.lat)));
        assertThat(((GeoFix) r1).longt(),is(equalTo(ConstantValues.longt)));

        Annotation r2 = desc.getAnnotation(ScreenSize.class);
        assertNotNull(r2);
        assertThat(((ScreenSize) r2).height(),is(equalTo(ConstantValues.height)));
        assertThat(((ScreenSize) r2).width(),is(equalTo(ConstantValues.width)));



        List<CommandDTO> commands =  BaristaAnotationParser.getParsedCommands(desc,null);
        assertThat(commands.size(),is(equalTo(2)));
        assertThat(commands.get(0).toString(), is(equalTo(GEOFIX_ASSERT_STR)));
        assertThat(commands.get(1).toString(), is(equalTo(SCREENSIZE_ASSERT_STR)));

    }

    @Test
    public void testGeoFixAnnotation() {

        // mock a Description object to return the GeoFix Annotation
        Description desc = mock(Description.class);
        when(desc.getAnnotation(GeoFix.class)).thenReturn((GeoFix) geoFixAnnotation);

        List<CommandDTO> commands =  BaristaAnotationParser.getParsedCommands(desc,null);
        assertThat(commands.size(),is(equalTo(1)));
        CommandDTO parsedCommand = commands.get(0);
        assertThat(parsedCommand.toString(),is(equalTo(GEOFIX_ASSERT_STR)));

    }

    @Test
    public void testWmSizeAnnotation(){

        // mock a Description object to return the ScreenSize Annotation
        Description desc = mock(Description.class);
        when(desc.getAnnotation(ScreenSize.class)).thenReturn((ScreenSize) wmSizeAnnotation);

        List<CommandDTO> commands =  BaristaAnotationParser.getParsedCommands(desc,null);
        assertThat(commands.size(),is(equalTo(1)));
        CommandDTO parsedCommand = commands.get(0);
        assertThat(parsedCommand.toString(),is(equalTo(SCREENSIZE_ASSERT_STR)));

    }

    public void testSizeAndGeofixAnnotation(){


        ArrayList<Annotation> annotationList = new ArrayList<>();
        annotationList.add(this.geoFixAnnotation);
        annotationList.add(this.wmSizeAnnotation);

        Description desc = mock(Description.class);
        when(desc.getAnnotations()).thenReturn(annotationList);

        List<CommandDTO> commands =  BaristaAnotationParser.getParsedCommands(desc,null);



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
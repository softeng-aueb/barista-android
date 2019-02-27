package gr.aueb.android.barista.core.annotations;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import gr.aueb.android.barista.core.model.BatteryLevelDTO;
import gr.aueb.android.barista.core.model.CommandDTO;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BaristaAnnotationParserTest {

    private final String GEOFIX_ASSERT_STR = CommandDTODataHelper.geoFixCommand.toString();
    private final String SCREENSIZE_ASSERT_STR = CommandDTODataHelper.sizeCommand.toString();
    private final String PERMISSION_ASSERT_STR = CommandDTODataHelper.permissionCommand.toString();
    private final String DENSITY_ASSER_STR = CommandDTODataHelper.densityCommand.toString();
    private final String BATTERY_LEVEL_ASSERT_STR = CommandDTODataHelper.batteryLevelCommand.toString();
    private final String BATTERY_PLUGED_ASSERT_STR = CommandDTODataHelper.batteryChargeCommand.toString();

    //single descriptions
    private Description geoFixDescription;
    private Description wmSizeDescription;
    private Description permissionDescription;
    private Description densityDescription;
    private Description batteryDescription;
    //combo descriptions
    private Description geoFixAndWmSizeDescription;


    @Before
    public void initializeAnnotatedMethods(){
        // use the DummyAnnotatedClass to construct a legitimate GeoFix Annotation instance
        try {
            Method geoFixAnnotatedMethod = DummyAnnotatedClass.class.getMethod("m1");
            Annotation geoFixAnnotation = geoFixAnnotatedMethod.getAnnotation(GeoFix.class);

            // use the DummyAnnotatedClass to construct a legitimate GeoFix Annotation instance to use as mocked result
            Method screenSizeAnnotatedMethod = DummyAnnotatedClass.class.getMethod("m2");
            Annotation wmSizeAnnotation = screenSizeAnnotatedMethod.getAnnotation(ScreenSize.class);

            // use the DummyAnnotatedClass to construct a legitimate Permission Annotation instance to use as mocked result
            Method permissionAnnotatedMethod = DummyAnnotatedClass.class.getMethod("m3");
            Annotation permisisonAnnotation = permissionAnnotatedMethod.getAnnotation(Permission.class);

            // use the DummyAnnotatedClass to construct a legitimate Density Annotation instance to use as mocked result
            Method densityAnnotatedMethod = DummyAnnotatedClass.class.getMethod("m4");
            Annotation densityAnnotation = densityAnnotatedMethod.getAnnotation(Density.class);

            //Battery Description mocking
            Method batteryAnnotatedMethod = DummyAnnotatedClass.class.getMethod("m5");
            Annotation batteryAnnotation = batteryAnnotatedMethod.getAnnotation(BatteryOptions.class);

            geoFixDescription = mock(Description.class);
            wmSizeDescription = mock(Description.class);
            geoFixAndWmSizeDescription = mock(Description.class);
            permissionDescription = mock(Description.class);
            densityDescription = mock(Description.class);
            batteryDescription = mock(Description.class);

            //single mocks
            when(geoFixDescription.getAnnotation(GeoFix.class)).thenReturn((GeoFix) geoFixAnnotation);
            when(wmSizeDescription.getAnnotation(ScreenSize.class)).thenReturn((ScreenSize) wmSizeAnnotation);
            when(permissionDescription.getAnnotation(Permission.class)).thenReturn((Permission) permisisonAnnotation);
            when(densityDescription.getAnnotation(Density.class)).thenReturn((Density) densityAnnotation);
            when(batteryDescription.getAnnotation(BatteryOptions.class)).thenReturn((BatteryOptions) batteryAnnotation);

            //group mock
            when(geoFixAndWmSizeDescription.getAnnotation(GeoFix.class)).thenReturn((GeoFix) geoFixAnnotation);
            when(geoFixAndWmSizeDescription.getAnnotation(ScreenSize.class)).thenReturn((ScreenSize) wmSizeAnnotation);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }


    /*@Test
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



        List<CommandDTO> commands =  BaristaAnnotationParser.getParsedCommands(desc);
        assertThat(commands.size(),is(equalTo(2)));
        assertThat(commands.get(0).toString(), is(equalTo(GEOFIX_ASSERT_STR)));
        assertThat(commands.get(1).toString(), is(equalTo(SCREENSIZE_ASSERT_STR)));

    }
    */

    @Test
    public void testGeoFixAnnotation() {

        List<CommandDTO> commands =  BaristaAnnotationParser.getParsedCommands(geoFixDescription);
        assertThat(commands.size(),is(equalTo(1)));
        CommandDTO parsedCommand = commands.get(0);
        assertThat(parsedCommand.toString(),is(equalTo(GEOFIX_ASSERT_STR)));

    }

    @Test
    public void testWmSizeAnnotation(){

        List<CommandDTO> commands =  BaristaAnnotationParser.getParsedCommands(wmSizeDescription);
        assertThat(commands.size(),is(equalTo(1)));
        CommandDTO parsedCommand = commands.get(0);
        assertThat(parsedCommand.toString(),is(equalTo(SCREENSIZE_ASSERT_STR)));

    }

    @Test
    public void testSizeAndGeofixAnnotation(){

        List<CommandDTO> commands =  BaristaAnnotationParser.getParsedCommands(geoFixAndWmSizeDescription);
        assertThat(commands.size(),is(equalTo(2)));
    }

    @Test
    public void testPermissionAndGeofixAnnotation(){

        List<CommandDTO> commands =  BaristaAnnotationParser.getParsedCommands(permissionDescription);
        assertThat(commands.size(),is(equalTo(1)));
        CommandDTO parsedCommand = commands.get(0);
        assertThat(parsedCommand.toString(),is(equalTo(PERMISSION_ASSERT_STR)));
    }

    @Test
    public void testDensityAnnotation(){

        List<CommandDTO> commands =  BaristaAnnotationParser.getParsedCommands(densityDescription);
        assertThat(commands.size(),is(equalTo(1)));
        CommandDTO parsedCommand = commands.get(0);
        assertThat(parsedCommand.toString(),is(equalTo(DENSITY_ASSER_STR)));
    }

    @Test
    public void testBatteryAnnotation(){
        List<CommandDTO> commands =  BaristaAnnotationParser.getParsedCommands(batteryDescription);
        assertThat(commands.size(),is(equalTo(2)));
        CommandDTO parsedCommand = commands.get(0);
        assertThat(parsedCommand.toString(),is(equalTo(BATTERY_LEVEL_ASSERT_STR)));
        parsedCommand = commands.get(1);
        assertThat(parsedCommand.toString(),is(equalTo(BATTERY_PLUGED_ASSERT_STR)));

    }


}
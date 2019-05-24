package gr.aueb.android.barista.core.annotations;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;


import static org.hamcrest.CoreMatchers.notNullValue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import gr.aueb.android.barista.core.model.BatteryChargeDTO;
import gr.aueb.android.barista.core.model.BatteryLevelDTO;
import gr.aueb.android.barista.core.model.CommandDTO;
import gr.aueb.android.barista.core.model.PmRevokeDTO;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BaristaAnnotationParserTest {

    private final String GEOFIX_ASSERT_STR = CommandDTODataHelper.geoFixCommand.toString();
    private final String SCREENSIZE_ASSERT_STR = CommandDTODataHelper.sizeCommand.toString();
    private final String PERMISSION_ASSERT_STR = CommandDTODataHelper.permissionCommand.toString();
    private final String REVOKE_PERMISSION_ASSERT_STR = CommandDTODataHelper.revokeCommand.toString();
    private final String DENSITY_ASSER_STR = CommandDTODataHelper.densityCommand.toString();
    private final String BATTERY_LEVEL_ASSERT_STR = CommandDTODataHelper.batteryLevelCommand.toString();
    private final String BATTERY_PLUGED_ASSERT_STR = CommandDTODataHelper.batteryChargeCommand.toString();
    private final String WIFI_ASSERT_STR = CommandDTODataHelper.wifiCommand.toString();
    private final String DATA_ASSERT_STR = CommandDTODataHelper.dataCommand.toString();
    private final String ORIENTATION_ASSERT_STR = CommandDTODataHelper.orientationComnnand.toString();

    //single descriptions
    private Description geoFixDescription;
    private Description wmSizeDescription;
    private Description permissionDescription;
    private Description densityDescription;
    private Description batteryDescription;
    private Description wifiDescription;
    private Description dataDescription;
    private Description orientationDescription;

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

            //wifi Description  mocking
            Method wifiAnnotatedMethod = DummyAnnotatedClass.class.getMethod("m6");
            Annotation wifiAnnotation = wifiAnnotatedMethod.getAnnotation(Wifi.class);

            //data Description  mocking
            Method dataAnnotatedMethod = DummyAnnotatedClass.class.getMethod("m7");
            Annotation dataAnnotation = dataAnnotatedMethod.getAnnotation(Data.class);

            //orientation Description mocking
            Method orientationAnnotatedMethod = DummyAnnotatedClass.class.getMethod("m8");
            Annotation orientationAnnotation = orientationAnnotatedMethod.getAnnotation(Orientation.class);

            geoFixDescription = mock(Description.class);
            wmSizeDescription = mock(Description.class);
            geoFixAndWmSizeDescription = mock(Description.class);
            permissionDescription = mock(Description.class);
            densityDescription = mock(Description.class);
            batteryDescription = mock(Description.class);
            wifiDescription = mock(Description.class);
            dataDescription = mock(Description.class);
            orientationDescription = mock(Description.class);

            //single mocks
            when(geoFixDescription.getAnnotation(GeoFix.class)).thenReturn((GeoFix) geoFixAnnotation);
            when(wmSizeDescription.getAnnotation(ScreenSize.class)).thenReturn((ScreenSize) wmSizeAnnotation);
            when(permissionDescription.getAnnotation(Permission.class)).thenReturn((Permission) permisisonAnnotation);
            when(densityDescription.getAnnotation(Density.class)).thenReturn((Density) densityAnnotation);
            when(batteryDescription.getAnnotation(BatteryOptions.class)).thenReturn((BatteryOptions) batteryAnnotation);
            when(wifiDescription.getAnnotation(Wifi.class)).thenReturn((Wifi) wifiAnnotation);
            when(dataDescription.getAnnotation(Data.class)).thenReturn((Data) dataAnnotation);
            when(orientationDescription.getAnnotation(Orientation.class)).thenReturn((Orientation) orientationAnnotation);


            //group mock
            when(geoFixAndWmSizeDescription.getAnnotation(GeoFix.class)).thenReturn((GeoFix) geoFixAnnotation);
            when(geoFixAndWmSizeDescription.getAnnotation(ScreenSize.class)).thenReturn((ScreenSize) wmSizeAnnotation);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }


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
        assertThat(parsedCommand.getResetCommand(), is(notNullValue()));

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

        //assert reverse command
        CommandDTO revokeCommand = parsedCommand.getResetCommand();
        assertThat(revokeCommand,is(notNullValue()));
        assertThat(((PmRevokeDTO)revokeCommand).toString(),is(equalTo(REVOKE_PERMISSION_ASSERT_STR)));
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

        // assert reverse command for level
        CommandDTO resetLevel =  commands.get(0).getResetCommand();
        assertThat(resetLevel, is(notNullValue()));
        assertThat(((BatteryLevelDTO)resetLevel).getLevel(),is(equalTo(100)));
        assertThat(resetLevel.getResetCommand(), equalTo(null)); // reverse command of reverse should not exist

        // assert reverse command for charging
        CommandDTO resetCharging =  commands.get(1).getResetCommand();
        assertThat(resetCharging, is(notNullValue()));
        assertThat(((BatteryChargeDTO)resetCharging).isPlugged(),is(equalTo(true)));
        assertThat(resetCharging.getResetCommand(), equalTo(null)); // reverse command of reverse should not exist


    }

    @Test
    public void testWifiAnnotation(){
        List<CommandDTO> commands =  BaristaAnnotationParser.getParsedCommands(wifiDescription);
        assertThat(commands.size(),is(equalTo(1)));
        CommandDTO parsedCommand = commands.get(0);
        assertThat(parsedCommand.toString(),is(equalTo(WIFI_ASSERT_STR)));
        assertThat(parsedCommand.getResetCommand(), is(notNullValue()));
    }

    @Test
    public void testDataAnnotation(){
        List<CommandDTO> commands =  BaristaAnnotationParser.getParsedCommands(dataDescription);
        assertThat(commands.size(),is(equalTo(1)));
        CommandDTO parsedCommand = commands.get(0);
        assertThat(parsedCommand.toString(),is(equalTo(DATA_ASSERT_STR)));
        assertThat(parsedCommand.getResetCommand(), is(notNullValue()));
    }

    @Test
    public void testOrienttionAnnotations(){

        List<CommandDTO> commands =  BaristaAnnotationParser.getParsedCommands(orientationDescription);
        assertThat(commands.size(),is(equalTo(1)));
        CommandDTO parsedCommand = commands.get(0);
        assertThat(parsedCommand.toString(),is(equalTo(ORIENTATION_ASSERT_STR)));
        assertThat(parsedCommand.getResetCommand(), is(notNullValue()));

    }


}
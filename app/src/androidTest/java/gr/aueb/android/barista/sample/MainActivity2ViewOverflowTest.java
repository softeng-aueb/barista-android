package gr.aueb.android.barista.sample;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import gr.aueb.android.barista.annotation.Configuration;
import gr.aueb.android.barista.annotation.Configurations;
import gr.aueb.android.barista.annotation.Device;
import gr.aueb.android.barista.annotation.Devices;
import gr.aueb.android.barista.framework.ActivityInstrumentationTestCase;

import static gr.aueb.android.barista.assertion.MyAssertions.assertEqualHeight;
import static gr.aueb.android.barista.assertion.MyAssertions.assertEqualWidth;
import static gr.aueb.android.barista.assertion.MyAssertions.assertMultiLineTextFit;
import static gr.aueb.android.barista.assertion.MyAssertions.assertOverLapLayout;
import static gr.aueb.android.barista.assertion.MyAssertions.assertTextHeight;


/**
 * Created by Miltiadis Konsolakis on 9/2/16.
 */

@Devices( devices = {
        @Device(model = Device.Model.NEXUS_S, orientation = Configuration.Orientation.PORTRAIT),
        @Device(model = Device.Model.NEXUS_S, orientation = Configuration.Orientation.LANDSCAPE),
        @Device(model = Device.Model.SAMSUNG_GALAXY_S6, orientation = Configuration.Orientation.PORTRAIT),
        @Device(model = Device.Model.SAMSUNG_GALAXY_S6, orientation = Configuration.Orientation.LANDSCAPE),
})
public class MainActivity2ViewOverflowTest extends ActivityInstrumentationTestCase<MainActivity1> {
    private Activity activity;

    private TextView textView;
    private Button button1, button2, button3, button4, button5, button6, button7, button8;

    public MainActivity2ViewOverflowTest() {
        super(MainActivity1.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        activity = getActivity();

        textView = (TextView) activity.findViewById(R.id.textView);

        button1 = (Button) activity.findViewById(R.id.button1);
        button2 = (Button) activity.findViewById(R.id.button2);
        button3 = (Button) activity.findViewById(R.id.button3);
        button4 = (Button) activity.findViewById(R.id.button4);
        button5 = (Button) activity.findViewById(R.id.button5);
        button6 = (Button) activity.findViewById(R.id.button6);
        button7 = (Button) activity.findViewById(R.id.button7);
        button8 = (Button) activity.findViewById(R.id.button8);
    }

    /*@Devices( devices = {
            @Device(model = Device.Model.SAMSUNG_GALAXY_NOTE_4, orientation = Configuration.Orientation.PORTRAIT),
            @Device(model = Device.Model.SAMSUNG_GALAXY_NOTE_4, orientation = Configuration.Orientation.LANDSCAPE)
    })*/
    public void testViewOverFlow() throws Exception {
        //Functions.saveScreenShot(activity, ConfigurationHelper.configToShortString(MyAndroidTestRunner.currentConfiguration));

        assertTextHeight(textView, 10, 20);
    }

    /*public void testViewOverFlow1() throws Exception {
        ArrayList<View> views = new ArrayList<View>();
        views.add(button);
        views.add(button2);
        views.add(button3);
        views.add(button4);

        if(ConfigurationHelper.isCurrentOrientation(activity, Configuration.Orientation.LANDSCAPE)) {
            assertEqualWidth(views);
            assertEqualHeight(views);
        }
    }*/

}

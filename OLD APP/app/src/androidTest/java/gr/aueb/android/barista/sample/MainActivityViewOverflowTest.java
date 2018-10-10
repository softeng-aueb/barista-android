package gr.aueb.android.barista.sample;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import static gr.aueb.android.barista.assertion.MyAssertions.assertOverlapChildren;
import static gr.aueb.android.barista.assertion.MyAssertions.assertTextFit;
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
public class MainActivityViewOverflowTest extends ActivityInstrumentationTestCase<MainActivity> {
    private Activity activity;

    private ImageView imageView;
    private TextView textViewLarge, textViewSmall, textViewDensity, textView;
    private Button button, button1, button2, button3, button4, button7, button8;

    public MainActivityViewOverflowTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        activity = getActivity();

        imageView = (ImageView) activity.findViewById(R.id.imageView);

        textViewLarge = (TextView) activity.findViewById(R.id.textViewLarge);
        textViewSmall = (TextView) activity.findViewById(R.id.textViewSmall);
        textViewDensity = (TextView) activity.findViewById(R.id.textViewDensity);

        button = (Button) activity.findViewById(R.id.button);
        button2 = (Button) activity.findViewById(R.id.button2);
        button3 = (Button) activity.findViewById(R.id.button3);
        button4 = (Button) activity.findViewById(R.id.button4);
    }

    /*@Devices( devices = {
            @Device(model = Device.Model.SAMSUNG_GALAXY_NOTE_4, orientation = Configuration.Orientation.PORTRAIT),
            @Device(model = Device.Model.SAMSUNG_GALAXY_NOTE_4, orientation = Configuration.Orientation.LANDSCAPE)
    })*/
    public void testViewOverFlow() throws Exception {
        ArrayList<View> views = new ArrayList<View>();
        views.add(button);
        views.add(button2);
        views.add(button3);
        views.add(button4);

        //assertEqualWidth(views);
        //assertEqualHeight(views);

        assertMultiLineTextFit(button);
        assertMultiLineTextFit(button2);
        assertMultiLineTextFit(button3);
        assertMultiLineTextFit(button4);
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



    public void testCase1() throws Exception {
        ArrayList<View> views = new ArrayList<View>();

        views.add(button);
        views.add(button2);
        views.add(button3);
        views.add(button4);

        assertEqualHeight(views);
    }

    public void testCase2() throws Exception {
        ArrayList<View> views = new ArrayList<View>();

        views.add(button7);
        views.add(button8);

        assertOverlapChildren(views);
    }


    public void testCase3() throws Exception {

        assertTextFit(textView);
    }


}

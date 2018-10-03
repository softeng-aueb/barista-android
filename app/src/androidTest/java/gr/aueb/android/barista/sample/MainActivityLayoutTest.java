package gr.aueb.android.barista.sample;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import gr.aueb.android.barista.annotation.Configuration;
import gr.aueb.android.barista.annotation.Configurations;
import gr.aueb.android.barista.annotation.Device;
import gr.aueb.android.barista.annotation.Devices;
import gr.aueb.android.barista.framework.ActivityInstrumentationTestCase;

import static gr.aueb.android.barista.assertion.MyAssertions.assertEqualHeight;
import static gr.aueb.android.barista.assertion.MyAssertions.assertEqualWidth;
import static gr.aueb.android.barista.assertion.MyAssertions.assertEqualsDp;
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

        @Device(model = Device.Model.NEXUS_5, orientation = Configuration.Orientation.PORTRAIT),
        @Device(model = Device.Model.NEXUS_5, orientation = Configuration.Orientation.LANDSCAPE),

        @Device(model = Device.Model.SAMSUNG_GALAXY_S6, orientation = Configuration.Orientation.PORTRAIT),
        @Device(model = Device.Model.SAMSUNG_GALAXY_S6, orientation = Configuration.Orientation.LANDSCAPE),

        @Device(model = Device.Model.SAMSUNG_GALAXY_TAB_10, orientation = Configuration.Orientation.PORTRAIT),
        @Device(model = Device.Model.SAMSUNG_GALAXY_TAB_10, orientation = Configuration.Orientation.LANDSCAPE),
})
public class MainActivityLayoutTest extends ActivityInstrumentationTestCase<MainActivity1> {
    private Activity activity;

    private TextView textView;
    private ImageView imageView;
    private HorizontalScrollView horizontalScrollView;
    private ScrollView scrollView;
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13;

    public MainActivityLayoutTest() {
        super(MainActivity1.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        activity = getActivity();

        // Section 1
        button1 = (Button) activity.findViewById(R.id.button1);
        button2 = (Button) activity.findViewById(R.id.button2);
        button3 = (Button) activity.findViewById(R.id.button3);
        button4 = (Button) activity.findViewById(R.id.button4);

        // Section 2
        button5 = (Button) activity.findViewById(R.id.button5);
        button6 = (Button) activity.findViewById(R.id.button6);

        // Section 3
        button7 = (Button) activity.findViewById(R.id.button7);
        button8 = (Button) activity.findViewById(R.id.button8);

        // Section 4
        textView = (TextView) activity.findViewById(R.id.textView);

        // Section 5 - ScrollView
        scrollView = (ScrollView) activity.findViewById(R.id.scrollView);

        // Section 5.1
        imageView = (ImageView) activity.findViewById(R.id.imageView); // height: 100dp, width:100dp

        // Section 5.2
        button9 = (Button) activity.findViewById(R.id.button9); //height: 35dp, width: 300dp

        // Section 5.3 - HorizontalScrollView
        horizontalScrollView = (HorizontalScrollView) activity.findViewById(R.id.horizontalScrollView); //height: 50dp

        // Section 5.3.1
        button10 = (Button) activity.findViewById(R.id.button10);
        button11 = (Button) activity.findViewById(R.id.button11);
        button12 = (Button) activity.findViewById(R.id.button12);
        button13 = (Button) activity.findViewById(R.id.button13);
    }

    /**
     * LinearLayout
     * @throws Exception
     */
    @Device(model = Device.Model.NEXUS_5, orientation = Configuration.Orientation.PORTRAIT)
    public void testSection_1() throws Exception {
        ArrayList<View> views = new ArrayList<View>();
        views.add(button1);
        views.add(button2);
        views.add(button3);
        views.add(button4);

        assertEqualWidth(views);
        assertEqualHeight(views);
    }

    /**
     * Relative Layout - button 5 leftTo parent, button 6 rightTo parent
     * @throws Exception
     */
    @Device(model = Device.Model.NEXUS_S, orientation = Configuration.Orientation.PORTRAIT)
    public void testSection_2() throws Exception {
        ArrayList<View> views = new ArrayList<View>();
        views.add(button5);
        views.add(button6);

        assertOverlapChildren(views);
    }

    /**
     * LinearLayout - Same width
     *
     * static width seperator 100dp
     * width button7 and button8 = (parentLayoutWidth-100)/2
     * @throws Exception
     */
    public void testSection_3() throws Exception {
        ArrayList<View> views = new ArrayList<View>();
        views.add(button7);
        views.add(button8);

        assertEqualWidth(views);

        assertTextFit(button7);
        assertTextFit(button8);
    }

    /**
     * TextView - Multiline text
     * @throws Exception
     */
    public void testSection_4() throws Exception {
        assertMultiLineTextFit(textView);
    }

    /**
     * ScrollView
     * @throws Exception
     */
    public void testSection_5() throws Exception {
        assertEqualsDp(imageView.getMeasuredWidth(), 100, imageView);
        assertEqualsDp(button9.getMeasuredHeight(), 40, button9);
        assertEqualsDp(horizontalScrollView.getMeasuredHeight(), 50, horizontalScrollView);
    }

    /**
     * Button fix width
     * @throws Exception
     */
    public void testSection_5_2() throws Exception {
        assertTextFit(button9);
    }

    /**
     * Parent HorizontalScrollView
     * @throws Exception
     */
    public void testSection_5_3_1() throws Exception {
        ArrayList<View> views = new ArrayList<View>();
        views.add(button10);
        views.add(button11);
        views.add(button12);
        views.add(button13);

        assertEqualWidth(views);
        assertEqualHeight(views);
    }

}

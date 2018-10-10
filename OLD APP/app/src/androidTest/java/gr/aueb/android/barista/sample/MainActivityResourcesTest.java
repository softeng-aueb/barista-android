package gr.aueb.android.barista.sample;

import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import gr.aueb.android.barista.annotation.Configuration;
import gr.aueb.android.barista.annotation.Configurations;
import gr.aueb.android.barista.annotation.Device;
import gr.aueb.android.barista.annotation.Devices;
import gr.aueb.android.barista.framework.ActivityInstrumentationTestCase;
import gr.aueb.android.barista.helper.Compatibility;
import gr.aueb.android.barista.helper.ConfigurationHelper;

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
        @Device(model = Device.Model.SAMSUNG_GALAXY_S6, orientation = Configuration.Orientation.LANDSCAPE)
})
public class MainActivityResourcesTest extends ActivityInstrumentationTestCase<MainActivity> {
    private Activity activity;

    private ImageView imageView;
    private TextView textViewLarge, textViewSmall, textViewDensity;

    public MainActivityResourcesTest() {
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
    }

    public void testResources() throws Exception {
        // Check drawable
        assertEquals(Compatibility.getCompatibilityDrawable(activity, R.drawable.image).getConstantState(), imageView.getDrawable().getConstantState()); //idio me to  assertEquals(textViewLarge.getText().toString(), a);

        assertEquals(textViewLarge.getText().toString(), activity.getString(R.string.text_large));
        assertEquals(textViewSmall.getText().toString(), activity.getString(R.string.text_small));

        // Check texts only for Nexus S device
        if(ConfigurationHelper.isCurrentDeviceModel(activity, Device.Model.NEXUS_S)) {
            Log.d("AUEB_DEBUG", "NEXUS_S");

            assertEquals(textViewDensity.getText().toString(), "240");

            if(ConfigurationHelper.isCurrentOrientation(activity, Configuration.Orientation.PORTRAIT)) {
                assertEquals(textViewLarge.getText().toString(), "large hdpi");
                assertNotSame(textViewLarge.getText().toString(), "large mdpi");
            } else {
                assertEquals(textViewLarge.getText().toString(), "w480dp-hdpi");
            }
        }

        // Check texts only for Samsung Galaxy S6 device
        if(ConfigurationHelper.isCurrentDeviceModel(activity, Device.Model.SAMSUNG_GALAXY_S6)) {
            Log.d("AUEB_DEBUG", "SAMSUNG_GALAXY_S6");

            assertEquals(textViewDensity.getText().toString(), "640");
        }

    }

}


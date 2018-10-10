package gr.aueb.android.barista.sample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by Miltiadis Konsolakis on 17/11/15.
 */
public class MainActivity1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        Log.d("AUEB_BEDUG", "MainActivity1<onCreate> called!");

        ((TextView) findViewById(R.id.textView)).setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus dignissim aliquet leo, sit amet laoreet eros. Fusce ipsum ex, dictum eget maximus eget, aliquet et lorem. ");

        ((TextView) findViewById(R.id.textView)).setBackgroundColor(Color.RED);



        /*int[] availableSize = Functions.getAvailableSize(((TextView) findViewById(R.id.textView)));
        int height = Functions.getTextHeightWithAvailableWidth(((TextView) findViewById(R.id.textView)), availableSize[0]);

        Log.d("AUEB_DEBUG", "availableWidth: " + availableSize[0] + " availableHeight:" + availableSize[1] + " textWidth: " + availableSize[0] + " textHeight: " + height);

        float minHeight = availableSize[1] - ((availableSize[1]*10)/100);
        float maxHeight = availableSize[1] - ((availableSize[1]*20)/100);

        Log.d("AUEB_DEBUG", "minHeight: " + minHeight + " maxHeight: " + maxHeight);*/
    }

}

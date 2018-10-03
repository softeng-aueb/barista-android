package gr.aueb.android.barista.sample;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.Collection;

import gr.aueb.android.barista.helper.ProcessHelper;

/**
 * Created by Miltiadis Konsolakis on 17/11/15.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("AUEB_BEDUG", "MainActivity<onCreate> called!");

        // Dummy Views
        LinearLayout viewbase = (LinearLayout) findViewById(R.id.base);
        for (int i = 0; i < 500; i++) {
            View view = new View(this);
            view.setBackgroundColor(Color.RED);
            viewbase.addView(view, new LinearLayout.LayoutParams(300, 100));
        }

        // Fill textView with current density
        ((TextView) findViewById(R.id.textViewDensity)).setText("" + getResources().getDisplayMetrics().densityDpi);

        try {
            Process process = Runtime.getRuntime().exec("wm size 800x1280");
            process.waitFor();

            String resError = ProcessHelper.StreamToString(process.getErrorStream());
            if (!resError.isEmpty()) {
                Log.e("Command22", "ProcessError: \n" + resError);
            }

            String commandOutput = ProcessHelper.StreamToString(process.getInputStream());
            if (!commandOutput.isEmpty()) {
                Log.e("Command22", "ProcessOutput: \n" + commandOutput);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

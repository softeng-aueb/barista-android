package gr.aueb.android.barista.helper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by Miltiadis Konsolakis on 8/2/16.
 */
public class Compatibility {

    @SuppressWarnings({"deprecation", "unchecked", "rawtypes"})
    @SuppressLint("NewApi")
    public static Drawable getCompatibilityDrawable(Activity activity, int id) {
        if(Build.VERSION.SDK_INT < 21) { return activity.getResources().getDrawable(id); }
        else { return activity.getResources().getDrawable(id, activity.getTheme()); }
    }

    public static int currentOrientation(Activity activity) {
        int currentOrientation = activity.getRequestedOrientation();
        if(currentOrientation == ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) { currentOrientation = activity.getResources().getConfiguration().orientation; }

        return currentOrientation;
    }

    @SuppressWarnings({"deprecation", "unchecked", "rawtypes"})
    @SuppressLint("NewApi")
    public static void removeGlobalLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener listener) {
        try {
            if(Build.VERSION.SDK_INT<16) {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
            } else { view.getViewTreeObserver().removeOnGlobalLayoutListener(listener); }
        } catch (Exception e) { Log.d("AUEB_TAB", e.getMessage()); }
    }

}

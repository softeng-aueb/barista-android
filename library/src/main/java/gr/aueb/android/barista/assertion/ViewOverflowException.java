package gr.aueb.android.barista.assertion;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import gr.aueb.android.barista.framework.AndroidTestRunner;
import gr.aueb.android.barista.helper.ConfigurationHelper;
import gr.aueb.android.barista.helper.Functions;


/**
 * Created by Miltiadis Konsolakis on 9/2/16.
 */
public class ViewOverflowException extends RuntimeException {
    private String detailMessage;

    public ViewOverflowException(String detailMessage, View view) {
        super(detailMessage);

        try {
            String viewName = view.getResources().getResourceName(view.getId());

            // Generate detail message
            this.detailMessage = detailMessage + "\nView: " + viewName + "\n" + ConfigurationHelper.configToString(AndroidTestRunner.currentConfiguration);

            viewName = viewName.replace(view.getContext().getPackageName(), "").replace(":", "").replace("/", "_");
            // Store Screenshot
            String extraInfo = ConfigurationHelper.configToShortString(AndroidTestRunner.currentConfiguration);
            String name = "e_" + extraInfo + "_" + viewName + "_" + detailMessage.replace(" ", "_") + "_"+ Functions.getTimestamp("HH_mm_ss");
            Functions.saveScreenShot((Activity) view.getContext(), name);
        } catch (Exception e) { Log.d("AUEB_DEBUG", e.toString()); }
    }

    @Override
    public String getMessage() {
        return this.detailMessage;
    }
}

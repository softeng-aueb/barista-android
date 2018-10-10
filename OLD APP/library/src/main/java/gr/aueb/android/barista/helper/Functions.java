package gr.aueb.android.barista.helper;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Miltiadis Konsolakis on 15/2/16.
 */
public class Functions {

    public static int[] getAvailableSize(TextView textView) {
        int[] size = new int[2];

        // text padding
        int paddingLeftRight = (textView.getPaddingLeft()+textView.getPaddingRight());
        int paddingTopBottom = (textView.getPaddingTop()+textView.getPaddingBottom());

        //drawble padding
        int drawablePaddingLeftRight = 0;
        int drawablePaddingTopBottom = 0;
        Drawable drawables[] = textView.getCompoundDrawables();

        if(drawables != null) {
            // Left right
            if (drawables[0] != null) {
                drawablePaddingLeftRight += drawables[0].getIntrinsicWidth() + textView.getCompoundDrawablePadding();
            }
            if (drawables[2] != null) {
                drawablePaddingLeftRight += drawables[2].getIntrinsicWidth() + textView.getCompoundDrawablePadding();
            }

            //Top bottom
            if (drawables[1] != null) {
                drawablePaddingTopBottom += drawables[1].getIntrinsicHeight() + textView.getCompoundDrawablePadding();
            }
            if (drawables[3] != null) {
                drawablePaddingTopBottom += drawables[3].getIntrinsicHeight() + textView.getCompoundDrawablePadding();
            }
        }

        size[0] = textView.getMeasuredWidth()-(drawablePaddingLeftRight+paddingLeftRight);
        size[1] = textView.getMeasuredHeight()-(drawablePaddingTopBottom+paddingTopBottom);

        return size;
    }

    public static int[] getAvailableSize(ViewGroup viewGroup) {
        int[] size = new int[2];

        // text padding
        int paddingLeftRight = (viewGroup.getPaddingLeft()+viewGroup.getPaddingRight());
        int paddingTopBottom = (viewGroup.getPaddingTop()+viewGroup.getPaddingBottom());

        size[0] = viewGroup.getMeasuredWidth()-(paddingLeftRight);
        size[1] = viewGroup.getMeasuredHeight()-(paddingTopBottom);

        return size;
    }

    public static int getTextHeightWithAvailableWidth(TextView textView, int width) {
        final StaticLayout layout = new StaticLayout(textView.getText().toString(), textView.getPaint(), width, Layout.Alignment.ALIGN_NORMAL, textView.getLineSpacingMultiplier(), textView.getLineSpacingExtra(), true);
        layout.draw(new Canvas());
        return layout.getHeight();
    }

    public static Bitmap takeScreenShot(Activity activity) {
        try {
            View view = activity.getWindow().getDecorView();
            view.setDrawingCacheEnabled(true);
            view.buildDrawingCache();
            Bitmap b1 = view.getDrawingCache();
            Bitmap b = Bitmap.createBitmap(b1, 0, 0, activity.getWindow().getDecorView().getMeasuredWidth(), activity.getWindow().getDecorView().getMeasuredHeight());
            view.destroyDrawingCache();
            return b;
        } catch (Exception e) { }
        return null;
    }

    public static boolean saveScreenShot(Activity activity, String name) {
        Bitmap b1 = Functions.takeScreenShot(activity);

        if(b1 != null) {
            FileOutputStream fileOutputStream = null;
            try {
                // Store /data/data/{PACKAGE_NAME}/files
                String path = activity.getFileStreamPath("").toString();

                File file = new File(path + "/" + name+".png");
                fileOutputStream = new FileOutputStream(file);
                b1.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            } catch (Exception e) {
                Log.d("AUEB_DEBUG", e.toString());

                return false;
            } finally {
                if (fileOutputStream != null) {  try { fileOutputStream.close(); } catch (IOException e) { } }
            }
        }

        return true;
    }

    public static boolean writeStringToFile(final File file, final String data, final String encoding) {
        boolean everythingOK=true;
        OutputStreamWriter outputStreamWriter=null;
        try {
            outputStreamWriter = encoding==null ? new OutputStreamWriter(new FileOutputStream(file)) : new OutputStreamWriter(new FileOutputStream(file), encoding);
            outputStreamWriter.write(data);
        }
        catch (Exception e) {
            everythingOK=false;
        }
        finally { if(outputStreamWriter!=null) try { outputStreamWriter.close(); } catch(Exception e1) {} }
        return everythingOK;
    }

    public static String getTimestamp(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    /**
     * Convert Dp to Pixels
     *
     * @param dp
     * @param context
     * @return
     */
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);

        return px;
    }

    /**
     * Convert Pixels to Dp
     *
     * @param px
     * @param context
     * @return
     */
    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);

        return dp;
    }

}

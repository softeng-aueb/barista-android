package gr.aueb.android.barista.assertion;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import junit.framework.Assert;

import java.util.ArrayList;

import gr.aueb.android.barista.helper.Functions;

/**
 * Created by Miltiadis Konsolakis on 8/2/16.
 */
public class MyAssertions extends Assert {

    /**
     *
     * @param a
     * @param b
     * @param view
     */
    public static void assertEqualsInt(int a, int b, final View view) {
        if(a != b) {
            throw new ViewOverflowException("assertEqualInt:" + a + " - " + b, view);
        }
    }

    /**
     * elegxoume an to measure value einai eisai me dp (proipotheti metatropi ton dp se px)
     *
     * @param measureValue
     * @param dp
     * @param view
     */
    public static void assertEqualsDp(int measureValue, int dp, final View view) {
        int b = (int) Functions.convertDpToPixel(dp, view.getContext());

        if (Math.abs(measureValue-b) > 1) {
            throw new ViewOverflowException("assertEqualsDp:" + measureValue + " - " + b, view);
        }
    }

    /**
     * Elexoume ena dio view pou to ena einai mesa sto allo emfanizete kai katalamvani ton diathesimo xoro se ena posostieo kato kai pano orio
     *
     * @param viewGroup
     * @param viewGroup1
     * @param minHeightPercentageRelativeToViewHeight
     * @param maxHeightPercentageRelativeToViewHeight
     */
    public static void assertViewHeight(ViewGroup viewGroup, ViewGroup viewGroup1, float minHeightPercentageRelativeToViewHeight, float maxHeightPercentageRelativeToViewHeight) {
        int[] availableSizeView1 = Functions.getAvailableSize(viewGroup);
        int[] availableSizeView2 = Functions.getAvailableSize(viewGroup1);

        Log.d("AUEB_DEBUG", "availableView1Width: " + availableSizeView1[0] + " availableView1Height:" + availableSizeView1[1] + " availableView1Width: " + availableSizeView2[0] + " availableView2Height: " + availableSizeView2[1]);

        double height1 = ((float) availableSizeView1[1]/ (float) availableSizeView2[1]) * 100;

        if(height1 < minHeightPercentageRelativeToViewHeight || height1 > maxHeightPercentageRelativeToViewHeight) {
            throw new ViewOverflowException("assertViewHeight", viewGroup1);
        }
    }

    /**
     * Elexoume ean to text pou vriskete se ena view emfanizete kai katalamvani ton diathesimo xoro se ena posostieo kato kai pano orio
     *
     * @param textView
     * @param minHeightPercentageRelativeToViewHeight
     * @param maxHeightPercentageRelativeToViewHeight
     */
    public static void assertTextHeight(TextView textView, float minHeightPercentageRelativeToViewHeight, float maxHeightPercentageRelativeToViewHeight) {
        int[] availableSize = Functions.getAvailableSize(textView);
        int textHeight = Functions.getTextHeightWithAvailableWidth(textView, availableSize[0]);

        double height = ((float) textHeight/ (float) availableSize[1]) * 100;

        Log.d("AUEB_DEBUG", "availableWidth: " + availableSize[0] + " availableHeight:" + availableSize[1] + " textWidth: " + availableSize[0] + " textHeight: " + textHeight + " height: " + height);

        if(height < minHeightPercentageRelativeToViewHeight || height > maxHeightPercentageRelativeToViewHeight) {
            throw new ViewOverflowException("assertTextHeight", textView);
        }
    }

    /**
     * elegxoume ta views tis listas me vasi to position on screen ean pefti to ena pano sto allo, xrisimopoihte gia RelativeLayout
     *
     * @param views
     */
    public static void assertOverlapChildren(final ArrayList<View> views) {
        for (int i=0; i<views.size(); i++) {
            View view = views.get(i);

            final int[] pos = new int[2];
            view.getLocationOnScreen(pos);

            int[] viewRect = new int[4];
            viewRect[0] = pos[0]; // x
            viewRect[1] = pos[1]; // y
            viewRect[2] = pos[0]+view.getMeasuredWidth();
            viewRect[3] = pos[1]+view.getMeasuredHeight();

            for(int j=0; j<views.size(); j++) {
                View view1 = views.get(j);

                if(view != view1) {
                    final int[] pos1 = new int[2];
                    view1.getLocationOnScreen(pos1);

                    if(pos1[0]>viewRect[0] && pos1[0]<viewRect[2] && pos1[1]<=viewRect[3]) {
                        throw new ViewOverflowException("assertOverlapChildren", view1);
                    }
                }
            }

        }
    }

    /**
     * elegoume ta paidia toy layout ena pefti to ena pano sto allo, xrisimopoihte gia RelativeLayout
     *
     * @param viewGroup
     */
    public static void assertOverLapLayout(ViewGroup viewGroup) {
        if(viewGroup != null) {
            ArrayList<View> views = new ArrayList<View>();
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                views.add(viewGroup.getChildAt(i));
            }
            assertOverlapChildren(views);
        } else {
            throw new RuntimeException("assertOverLapLayout - viewGroup null");
        }
    }

    /**
     * Vriskoume to availableWidth tou view kai metrame kai to textHeight dinontas prokathorismena to width to view
     * etsi diapistonoume an xoraei to text sto view
     * to xrisimopoioume gia TextView kai Buttons pou exoume rithmisi gia parapano apo mia grammi to text
     *
     * @param textView
     */
    public static void assertMultiLineTextFit(final TextView textView) {
        //final Rect rect = new Rect();
        //textView.getPaint().getTextBounds(textView.getText().toString(), 0, textView.getText().length(), rect);

        int[] availableSize = Functions.getAvailableSize(textView);
        int height = Functions.getTextHeightWithAvailableWidth(textView, availableSize[0]);

        Log.d("AUEB_DEBUG", "availableWidth: " + availableSize[0] + " availableHeight:" + availableSize[1] + " textWidth: " + availableSize[0] + " textHeight: " + height);

        if(height > availableSize[1]) {
            throw new ViewOverflowException("assertMultiLineTextFit", textView);
        }
    }

    /**
     * Vriskoume to availableWidth tou view kai metrame kai to textSize etsi diapistonoume an xoraei to text sto view
     * to xrisimopoioume gia TextView kai Buttons pou exoume rithmisi gia mia grammi to text
     *
     * @param textView
     */
    public static void assertSingleLineTextFit(TextView textView) {
        int[] availableSize = Functions.getAvailableSize(textView);
        float textWidth = textView.getPaint().measureText(textView.getText().toString());

        Log.d("AUEB_DEBUG", "availableWidth: "+availableSize[0] + " textWidth:"+textWidth);

        if(textWidth > availableSize[0]) {
            throw new ViewOverflowException("assertTextFit", textView);
        }
    }

    public static void assertTextFit(TextView textView) {
        if(textView.getMaxLines() == 1) { assertSingleLineTextFit(textView); }
        else { assertMultiLineTextFit(textView); }
    }

    /**
     * Elegxoume an ola ta views meta sto lista exoume to idio measure width
     * Ean exoume LinearLayout tote elegxoume me tin diafora tou width na einai > 1
     *
     * @param views
     */

    public static void assertEqualWidth(final ArrayList<View> views) {
        if(views.size() > 0) {
            boolean useRoundedValues = false;

            // Check parent layout
            ViewGroup viewParent = (ViewGroup) views.get(0).getParent();
            if(viewParent != null) {
                if(viewParent instanceof LinearLayout) {
                    for (int i=0; i<views.size(); i++) {
                        if(((LinearLayout.LayoutParams) views.get(i).getLayoutParams()).weight > 0) {
                            useRoundedValues = true;
                            break;
                        }
                    }
                }

            }

            int width = views.get(0).getMeasuredWidth();

            Log.d("AUEB_DEBUG", "width0: " + width);

            for (int i=0; i<views.size(); i++) {
                Log.d("AUEB_DEBUG", "width0: " + width + "width"+i+": "+views.get(i).getMeasuredWidth());

                if(useRoundedValues) {
                    if (Math.abs(width-views.get(i).getMeasuredWidth()) > 1) {
                        throw new ViewOverflowException("assertEqualWidth", views.get(i));
                    }
                } else {
                    if (width != views.get(i).getMeasuredWidth()) {
                        throw new ViewOverflowException("assertEqualWidth", views.get(i));
                    }
                }
            }
        }
    }

    /**
     * Elegxoume an ola ta views meta sto lista exoume to idio measure height
     * Ean exoume LinearLayout tote elegxoume me tin diafora tou height na einai > 1
     *
     * @param views
     */

    public static void assertEqualHeight(ArrayList<View> views) {
        if(views.size() > 0) {
            boolean useRoundedValues = false;

            // Check parent layout
            ViewGroup viewParent = (ViewGroup) views.get(0).getParent();
            if(viewParent != null) {
                if(viewParent instanceof LinearLayout) {
                    for (int i=0; i<views.size(); i++) {
                        if(((LinearLayout.LayoutParams) views.get(i).getLayoutParams()).weight > 0) {
                            useRoundedValues = true;
                            break;
                        }
                    }
                }

            }

            int height = views.get(0).getMeasuredHeight();

            Log.d("AUEB_DEBUG", "height0: " + height);

            for (int i=0; i<views.size(); i++) {
                Log.d("AUEB_DEBUG", "height: " + height + "height"+i+": "+views.get(i).getMeasuredHeight());

                if(useRoundedValues) {
                    if (Math.abs(height-views.get(i).getMeasuredHeight()) > 1) {
                        throw new ViewOverflowException("assertEqualWidth", views.get(i));
                    }
                } else {
                    if (height != views.get(i).getMeasuredHeight()) {
                        throw new ViewOverflowException("assertEqualHeight", views.get(i));
                    }
                }
            }
        }
    }

}

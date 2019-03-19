package com.example.baristademoapp;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class ColorMatcher implements Matcher<View> {

    private final int matchColor;

    public ColorMatcher(int matchColor) {
        this.matchColor = matchColor;
    }

    @Override
    public boolean matches(Object item) {
        Context context = ((View)item).getContext();
        int c2 = context.getColor(this.matchColor);
        int c1 = ((ColorDrawable)((View)item).getBackground()).getColor();
        return c1 == c2;
    }

    @Override
    public void describeMismatch(Object item, Description mismatchDescription) {
            Log.i("Color MIsmatch",mismatchDescription.toString());
    }

    @Override
    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {

    }

    @Override
    public void describeTo(Description description) {
        description.appendText("with text color: ");
    }
}

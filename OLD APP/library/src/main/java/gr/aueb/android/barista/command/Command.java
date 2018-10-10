package gr.aueb.android.barista.command;

import android.content.Context;

/**
 * Created by Miltiadis Konsolakis on 15/12/2015.
 */

public interface Command {

    public enum Orientation { PORTRAIT, LANDSCAPE, RESET }

    public boolean setDensity(int density);
    public boolean setSize(int width, int height);
    public boolean setOrientation(Orientation orientation);

    public boolean isCurrentDensity(int density);
    public boolean isCurrentSize(int width, int height);

    public void setContext(Context context);

}

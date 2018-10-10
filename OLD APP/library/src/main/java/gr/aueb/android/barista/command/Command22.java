package gr.aueb.android.barista.command;

import android.content.Context;
import android.util.Log;

import gr.aueb.android.barista.helper.ProcessHelper;

/**
 * Created by Miltiadis Konsolakis on 12/12/2015.
 */
public class Command22 implements Command {
    private Context context;

    private final int ThreadSleep = 500;
    private final int MaxRetries = 20;

    /**
     * Sets device density must > 72 and 0 = reset
     *
     * @param density
     * @return true if applied successfully
     */
    @Override
    public boolean setDensity(int density) {
        if(density < 72 && density != 0) {
            Log.d("Command22", "Error: density must be >= 72");
            return false;
        }

        try {
            Runtime r = Runtime.getRuntime();
            String cmd = "wm density " + (density==0?"reset":density);
            Log.d("Command22", "COMMAND: " + cmd);
            Process p = r.exec(cmd);
            p.waitFor();

            String res = ProcessHelper.StreamToString(p.getInputStream());
            if(!res.isEmpty()) { Log.d("Command22", "Process: \n" + res); }

            String resError = ProcessHelper.StreamToString(p.getErrorStream());
            if(!resError.isEmpty()) { Log.e("Command22", "ProcessError: \n" + resError); }

            /*int retries = 5;
            while(retries > 0) {
                Thread.sleep(120);

                int dmDensity = context.getResources().getDisplayMetrics().densityDpi;
                Log.d("Command22", "Retry: " + retries + " density:" + dmDensity);
                if(density != 0) {
                    if(dmDensity == density) { return true; }
                }

                retries--;
            }

            if(density == 0) { return true; }*/

            int retries = MaxRetries;
            while(retries > 0) {
                int dmDensity = context.getResources().getDisplayMetrics().densityDpi;
                Log.d("Command22", "Retry: " + retries + " densityFromDM: " + dmDensity);
                Thread.sleep(ThreadSleep);

                //check if device is ok
                Runtime r1 = Runtime.getRuntime();
                Process p1 = r1.exec("wm density");
                p1.waitFor();

                String result = ProcessHelper.StreamToString(p1.getInputStream());
                Log.d("Command22", "Result: \n" + result + "\n\n");

                if(density == 0) {
                    if(result.contains("Physical density: ") && !result.contains("Override density")) { return true; }
                } else {
                    if(result.contains("Override density: " + density) && (dmDensity == density)) { return true; }
                }

                retries--;
            }
        } catch (Exception ex) {
            Log.d("Command22", ex.toString());
        }

        return false;
    }

    /**
     * Sets device width&height must width >= 200 and height >= 200 and widht & height 0 = reset
     *
     * @param width
     * @param height
     * @return true if applied successfully
     */
    @Override
    public boolean setSize(int width, int height) {
        try {
            Runtime r = Runtime.getRuntime();
            String cmd = "wm size " + ((width==0 && height==0) ? "reset" : (width + "x" + height));
            Log.d("Command22", "COMMAND: " + cmd);
            Process p = r.exec(cmd);
            p.waitFor();

            String res = ProcessHelper.StreamToString(p.getInputStream());
            if(!res.isEmpty()) { Log.d("Command22", "Process: \n" + res); }

            String resError = ProcessHelper.StreamToString(p.getErrorStream());
            if(!resError.isEmpty()) { Log.e("Command22", "ProcessError: \n" + resError); }

            int retries = MaxRetries;
            while(retries > 0) {
                int dmWidth = context.getResources().getDisplayMetrics().widthPixels;
                int dmHeight = context.getResources().getDisplayMetrics().heightPixels;
                Log.d("Command22", "Retry: " + retries + " dmWxH: " + dmWidth+"x"+dmHeight);
                Thread.sleep(ThreadSleep);

                //check if device is ok
                Runtime r1 = Runtime.getRuntime();
                Process p1 = r1.exec("wm size");
                p1.waitFor();

                String result = ProcessHelper.StreamToString(p1.getInputStream());
                Log.d("Command22", "Result: \n" + result + "\n\n");

                if(width == 0 && height == 0) {
                    if(result.contains("Physical size: ") && !result.contains("Override size")) { return true; }
                } else {
                    if(result.contains("Override size: " + width+"x"+height)) { return true; }
                }

                retries--;
            }
        } catch (Exception ex) {
            Log.d("Command22", ex.toString());
        }

        return false;
    }

    @Override
    public boolean setOrientation(Orientation orientation) {
        String cmd = "";
        if(orientation == Orientation.RESET) {
            cmd = "content insert --uri content://settings/system --bind name:s:accelerometer_rotation --bind value:i:0";
        } else if(orientation == Orientation.PORTRAIT) {
            cmd = "content insert --uri content://settings/system --bind name:s:user_rotation --bind value:i:0";
        } else if(orientation == Orientation.LANDSCAPE) {
            cmd = "content insert --uri content://settings/system --bind name:s:user_rotation --bind value:i:1";
        }

        try {
            Runtime r = Runtime.getRuntime();
            Log.d("Command22", "COMMAND: " + cmd);
            Process p = r.exec(cmd);
            p.waitFor();

            String res = ProcessHelper.StreamToString(p.getInputStream());
            if(!res.isEmpty()) { Log.d("Command22", "Process: \n" + res); }

            String resError = ProcessHelper.StreamToString(p.getErrorStream());
            if(!resError.isEmpty()) { Log.e("Command22", "ProcessError: \n" + resError); }

            /*int retries = MaxRetries;
            while(retries > 0) {
                int dmWidth = context.getResources().getDisplayMetrics().widthPixels;
                int dmHeight = context.getResources().getDisplayMetrics().heightPixels;
                System.out.println("Retry: " + retries + " dmWxH: " + dmWidth+"x"+dmHeight);
                Thread.sleep(ThreadSleep);

                //check if device is ok
                Runtime r1 = Runtime.getRuntime();
                Process p1 = r1.exec("wm size");
                p1.waitFor();

                String result = ProcessHelper.StreamToString(p1.getInputStream());
                System.out.println("Result: \n" + result + "\n\n");

                if(width == 0 && height == 0) {
                    if(result.contains("Physical size: ") && !result.contains("Override size")) { return true; }
                } else {
                    if(result.contains("Override size: " + width+"x"+height)) { return true; }
                }

                retries--;
            }*/
        } catch (Exception ex) {
            Log.d("Command22", ex.toString());
        }

        return false;
    }

    @Override
    public void setContext(Context context) { this.context = context; }

    public boolean isCurrentDensity(int density) {
        try {
            Runtime r1 = Runtime.getRuntime();
            Process p1 = r1.exec("wm density");
            p1.waitFor();

            String result = ProcessHelper.StreamToString(p1.getInputStream());
            //System.out.println("Result: \n" + result + "\n\n");

            if(density == 0) {
                if(result.contains("Physical density: ") && !result.contains("Override density")) { return true; }
            } else {
                if(result.contains("Override density: " + density)) { return true; }
            }
        } catch (Exception ex) {
            Log.d("Command22", ex.toString());
        }

        return false;
    }

    public boolean isCurrentSize(int width, int height) {
        try {
            //check if device is ok
            Runtime r1 = Runtime.getRuntime();
            Process p1 = r1.exec("wm size");
            p1.waitFor();

            String result = ProcessHelper.StreamToString(p1.getInputStream());
            Log.d("Command22", "Result: \n" + result + "\n\n");

            if(width == 0 && height == 0) {
                if(result.contains("Physical size: ") && !result.contains("Override size")) { return true; }
            } else {
                if(result.contains("Override size: " + width+"x"+height)) { return true; }
            }
        } catch (Exception ex) {
            Log.d("Command22", ex.toString());
        }

        return false;
    }
}

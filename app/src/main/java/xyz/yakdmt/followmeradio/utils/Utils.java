package xyz.yakdmt.followmeradio.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by yakdmt on 12/06/16.
 */
public class Utils {

    public static int getScreenWidth(Activity activity){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }
}

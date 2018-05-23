package com.gmpvpc.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class ActivityUtils {
    public static void launchActivity(Context context, Class activity) {
        Intent i = new Intent(context, activity);
        context.startActivity(i);
    }

    public static void launchActivityForResult(Activity context, Class activity, int requestCode) {
        Intent i = new Intent(context, activity);
        context.startActivityForResult(i, requestCode);
    }
}

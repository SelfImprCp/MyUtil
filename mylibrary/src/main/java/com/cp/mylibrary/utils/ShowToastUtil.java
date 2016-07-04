package com.cp.mylibrary.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cp.mylibrary.R;

/**
 * Created by Jerry on 2016/7/4.
 */
public class ShowToastUtil {

    private static String lastToast = "";
    private static long lastToastTime;

// ===============================显示Toast======================================//
    /**
     *
     * @param
     */

    public static void showToast(Context context,int message) {
        showToast(context,message, Toast.LENGTH_LONG, 0);
    }

    public static void showToast(Context context,String message) {
        showToast(context,message, Toast.LENGTH_LONG, 0, Gravity.BOTTOM);
    }

    public static void showToast(Context context,int message, int icon) {
        showToast(context,message, Toast.LENGTH_LONG, icon);
    }

    public static void showToast(Context context,String message, int icon) {
        showToast(context,message, Toast.LENGTH_LONG, icon, Gravity.BOTTOM);
    }

    public static void showToastShort(Context context,int message) {
        showToast(context,message, Toast.LENGTH_SHORT, 0);
    }

    public static void showToastShort(Context context,String message) {
        showToast(context,message, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM);
    }

    public static void showToastShort(Context context,int message, Object... args) {
        showToast(context,message, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM, args);
    }

    public static void showToast(Context context,int message, int duration, int icon) {
        showToast(context,message, duration, icon, Gravity.BOTTOM);
    }

    public static void showToast(Context context,int message, int duration, int icon,
                                 int gravity) {
        showToast(context,context.getString(message), duration, icon,
                gravity);
    }

    public static void showToast(Context context,int message, int duration, int icon,
                                 int gravity, Object... args) {
        showToast(context, context.getString(message, args), duration,
                icon, gravity);
    }

    public static void showToast(Context context,String message, int duration, int icon,
                                 int gravity) {
        if (message != null && !message.equalsIgnoreCase("")) {
            long time = System.currentTimeMillis();
            if (!message.equalsIgnoreCase(lastToast)
                    || Math.abs(time - lastToastTime) > 2000) {
                View view = LayoutInflater.from( context).inflate(
                        R.layout.view_toast, null);
                ((TextView) view.findViewById(R.id.title_tv)).setText(message);
                if (icon != 0) {
                    ((ImageView) view.findViewById(R.id.icon_iv))
                            .setImageResource(icon);
                    ((ImageView) view.findViewById(R.id.icon_iv))
                            .setVisibility(View.VISIBLE);
                }
                Toast toast = new Toast( context);
                toast.setView(view);
                if (gravity == Gravity.CENTER) {
                    toast.setGravity(gravity, 0, 0);
                } else {
                    toast.setGravity(gravity, 0, 35);
                }

                toast.setDuration(duration);
                toast.show();
                lastToast = message;
                lastToastTime = System.currentTimeMillis();
            }
        }
    }


}

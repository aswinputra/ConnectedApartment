package com.sepgroup4.connectedapartment;

import android.content.Context;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by kiman on 29/09/2016.
 */

public class Utilities {
    public static void displayToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static String getFormattedDate(int day, int month, int year) {
        String yearStr = String.format(Locale.UK, "%04d", year);
        String monthStr = String.format(Locale.UK, "%02d", month);
        String dayStr = String.format(Locale.UK, "%02d", day);
        return yearStr + "-" + monthStr + "-" + dayStr;
    }
}

package com.sepgroup4.connectedapartment;

import android.content.Context;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by kiman on 29/09/2016.
 */

public class Utilities {

    private final static DateFormat DOBFormatCheck = new SimpleDateFormat("yyyy-MM-dd");


    public static void displayToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static String getFormattedDate(int day, int month, int year) {
        String yearStr = String.format(Locale.UK, "%04d", year);
        String monthStr = String.format(Locale.UK, "%02d", month);
        String dayStr = String.format(Locale.UK, "%02d", day);
        return yearStr + "-" + monthStr + "-" + dayStr;
    }

    public static Calendar getCalendarByString(String date){
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(DOBFormatCheck.parse(date));
        } catch (ParseException e) {
            return null;
        }
        return calendar;
    }

    public static String getFormattedDate(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String yearStr = String.format(Locale.UK, "%04d", year);
        String monthStr = String.format(Locale.UK, "%02d", month);
        String dayStr = String.format(Locale.UK, "%02d", day);
        return yearStr + "/" + monthStr + "/" + dayStr;
    }
}

package com.sepgroup4.connectedapartment;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by kiman on 29/09/2016.
 */

public class Utilities {
    public static void displayToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

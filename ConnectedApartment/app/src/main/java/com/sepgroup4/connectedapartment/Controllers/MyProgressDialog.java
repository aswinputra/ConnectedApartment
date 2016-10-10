package com.sepgroup4.connectedapartment.Controllers;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by kiman on 10/10/2016.
 */

public class MyProgressDialog {

    private ProgressDialog mDialog;

    public MyProgressDialog(Context context) {
        mDialog = new ProgressDialog(context);
    }

    public void show(String loadingName) {
        mDialog.setIndeterminate(true);
        mDialog.setMessage(loadingName);
        mDialog.setCancelable(false);
        mDialog.show();
    }

    public void hide() {
        mDialog.dismiss();
    }
}

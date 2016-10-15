package com.sepgroup4.connectedapartment.Controllers;

import android.content.Context;
import android.content.Intent;

import com.sepgroup4.connectedapartment.Model.Constants;
import com.sepgroup4.connectedapartment.Model.LoginSession;
import com.sepgroup4.connectedapartment.Model.TenantDetailRequest;

/**
 * Created by kiman on 10/10/2016.
 */

public class MyActivityManager {
    public static Intent logout(Context context){
        LoginSession.userToken = Constants.NULL;
        return new Intent(context, LoginActivity.class);
    }

    public static Intent intentToChangePaswordActivity(Context context){
        return new Intent(context, ChangePasswordActivity.class);
    }

    public static Intent intentToBMDashBoard(Context context){
        return new Intent(context, BMDashboardActivity.class);
    }

    public static Intent intentToTenantDashBoard(Context context){
        return new Intent(context, TenantDashboardActivity.class);
    }
}

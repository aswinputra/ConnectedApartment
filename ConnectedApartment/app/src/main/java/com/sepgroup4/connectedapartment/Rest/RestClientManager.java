package com.sepgroup4.connectedapartment.Rest;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.sepgroup4.connectedapartment.R;
import com.sepgroup4.connectedapartment.Rest.Controller.BuildingManagerController;
import com.sepgroup4.connectedapartment.Rest.Controller.PersonController;

public class RestClientManager {
    private static RestClientManager ourInstance = null;

    public static synchronized RestClientManager getInstance(Context context) throws NetworkErrorException {
        if (isNetworkAvailable(context)) {
            if (ourInstance == null) {
                ourInstance = new RestClientManager();
            }
            return ourInstance;
        } else {
            throw new NetworkErrorException(context.getString(R.string.network_is_unavailable));
        }
    }

    private RestClientManager() {
    }

    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    private final RestClient mRestClient = new RestClient();

    private final PersonController mPersonController = new PersonController(mRestClient);
    private final BuildingManagerController mBuildingManagerController = new BuildingManagerController(mRestClient);

    public PersonController getPersonController() {
        return mPersonController;
    }

    public BuildingManagerController getBuildingManagerController() {
        return mBuildingManagerController;
    }

}

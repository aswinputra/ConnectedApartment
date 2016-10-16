package com.sepgroup4.connectedapartment.Controllers;

import android.accounts.NetworkErrorException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sepgroup4.connectedapartment.Model.FacilityResponse;
import com.sepgroup4.connectedapartment.Model.LoginSession;
import com.sepgroup4.connectedapartment.Model.RequestResponse;
import com.sepgroup4.connectedapartment.R;
import com.sepgroup4.connectedapartment.Rest.Handlers.RestResponseHandler;
import com.sepgroup4.connectedapartment.Rest.RestClientManager;
import com.sepgroup4.connectedapartment.Utilities;

/**
 * Created by aswinhartono on 16/10/16.
 */

public class ViewBookingActivity extends AppCompatActivity implements View.OnClickListener, RestResponseHandler {
    private RecyclerView mAdapterBooking;
    private FacilityList mFacilityList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_booking);

        mAdapterBooking = (RecyclerView)findViewById(R.id.activity_view_booking_adapter_container);
        checkBM();
    }

    private void checkBM() {
        if (LoginSession.bm) {
            try {
                RestClientManager.getInstance(this).getBuildingManagerController().getFacilities(this);
            } catch (NetworkErrorException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                RestClientManager.getInstance(this).getPersonController().getFacilities(this);
            } catch (NetworkErrorException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onResponseSuccess(RequestResponse requestResponse) {
        if (requestResponse instanceof FacilityResponse)
        {
            mFacilityList = new FacilityList((FacilityResponse) requestResponse);
        }
    }

    @Override
    public void onResponseFailure(String errorMessage) {
        Utilities.displayToast(this, errorMessage);
    }
}

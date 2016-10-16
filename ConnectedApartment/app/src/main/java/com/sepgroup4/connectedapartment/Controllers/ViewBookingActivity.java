package com.sepgroup4.connectedapartment.Controllers;

import android.accounts.NetworkErrorException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sepgroup4.connectedapartment.Model.Booking;
import com.sepgroup4.connectedapartment.Model.FacilityList;
import com.sepgroup4.connectedapartment.Model.FacilityResponse;
import com.sepgroup4.connectedapartment.Model.LoginSession;
import com.sepgroup4.connectedapartment.Model.BookingResponse;
import com.sepgroup4.connectedapartment.Model.RequestResponse;
import com.sepgroup4.connectedapartment.R;
import com.sepgroup4.connectedapartment.Rest.Handlers.RestResponseHandler;
import com.sepgroup4.connectedapartment.Rest.RestClientManager;
import com.sepgroup4.connectedapartment.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aswinhartono on 16/10/16.
 */

public class ViewBookingActivity extends AppCompatActivity implements RestResponseHandler {
    private RecyclerView mBookingRecyclerView;
    private FacilityList mFacilityList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_booking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_view_booking_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        checkBM();
    }

    private void checkBM() {
        if (LoginSession.bm) {
            try {
                RestClientManager.getInstance(this).getBuildingManagerController().getFacilities(this);
                RestClientManager.getInstance(this).getBuildingManagerController().getBookings(this);
            } catch (NetworkErrorException e) {
                Utilities.displayToast(this,e.getMessage());
            }
        }
        else {
            try {
                RestClientManager.getInstance(this).getPersonController().getFacilities(this);
                RestClientManager.getInstance(this).getPersonController().getBookings(this);
            } catch (NetworkErrorException e) {
                Utilities.displayToast(this,e.getMessage());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            if(LoginSession.bm){
                startActivity(MyActivityManager.intentToBMDashBoard(this));
            }else{
                startActivity(MyActivityManager.intentToTenantDashBoard(this));
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResponseSuccess(RequestResponse requestResponse) {
        if (requestResponse instanceof FacilityResponse)
        {
            mFacilityList = new FacilityList((FacilityResponse) requestResponse);

        }
        else if(requestResponse instanceof BookingResponse){
            BookingResponse response = (BookingResponse) requestResponse;
            setUpRecyclerView(response.getResult());
        }
    }

    private void setUpRecyclerView(List<Booking> bookings){
        mBookingRecyclerView = (RecyclerView)findViewById(R.id.activity_view_booking_recycler_view);
        BookingAdapter adapter = new BookingAdapter(bookings, mFacilityList, this);
        if (mBookingRecyclerView != null) {
            mBookingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mBookingRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onResponseFailure(String errorMessage) {
        Utilities.displayToast(this, errorMessage);
    }
}

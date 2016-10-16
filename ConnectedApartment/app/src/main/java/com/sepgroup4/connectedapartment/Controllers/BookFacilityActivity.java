package com.sepgroup4.connectedapartment.Controllers;

import android.accounts.NetworkErrorException;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.sepgroup4.connectedapartment.Model.Facility;
import com.sepgroup4.connectedapartment.Model.FacilityResponse;
import com.sepgroup4.connectedapartment.Model.LoginSession;
import com.sepgroup4.connectedapartment.Model.MakeBookingRequest;
import com.sepgroup4.connectedapartment.Model.RequestResponse;
import com.sepgroup4.connectedapartment.R;
import com.sepgroup4.connectedapartment.Rest.Handlers.RestResponseHandler;
import com.sepgroup4.connectedapartment.Rest.RestClientManager;
import com.sepgroup4.connectedapartment.Utilities;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aswinhartono on 15/10/16.
 */

public class BookFacilityActivity extends AppCompatActivity implements RestResponseHandler,View.OnClickListener {

    private Spinner facilitySpinner;
    private EditText facilityDay;
    private Button facilityBookingbtn;
    private Map<String, String> mFacilityMap;
    private DatePickerDialog mPickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_facility);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_book_facility_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFacilityMap = new HashMap<>();
        facilitySpinner = (Spinner)findViewById(R.id.choose_facility_spinner);
        facilityDay = (EditText)findViewById(R.id.activity_book_facility_day_edittext);
        facilityDay.setInputType(0x00000000);
        facilityBookingbtn = (Button)findViewById(R.id.book_facility_button);
        setUpCalendarPrompt();


        facilityBookingbtn.setOnClickListener(this);
        facilityDay.setOnClickListener(this);
        getFacilities();
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

    private void getFacilities()
    {
        if(LoginSession.bm)
        {
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
    public void onResponseSuccess(RequestResponse requestResponse) {
        if (requestResponse instanceof FacilityResponse)
        {
            populateFacilitySpinner((FacilityResponse) requestResponse);
        }
        else if (requestResponse!=null)
        {
            Utilities.displayToast(this,"Your booking is successfully made");
        }
    }

    private void populateFacilitySpinner(FacilityResponse requestResponse) {
        for(Facility facilities : requestResponse.getResult()){
            mFacilityMap.put(facilities.getId().toString(),facilities.getFacilityName());
        }
        String[] facilityName = new String[mFacilityMap.size()];
        int i = 0;
        for (Map.Entry<String, String> facility : mFacilityMap.entrySet()){
            facilityName[i] = facility.getValue();
            i++;
        }
        ArrayAdapter<String> facilityAdapter = new ArrayAdapter<>(this, R.layout.apartment_spinner_layout, facilityName);
        facilitySpinner.setAdapter(facilityAdapter);
    }

    private void setUpCalendarPrompt(){
        Calendar calendar = Calendar.getInstance();
        int startYear = calendar.get(Calendar.YEAR);
        int starthMonth = calendar.get(Calendar.MONTH);
        int startDate = calendar.get(Calendar.DAY_OF_MONTH);
        mPickerDialog = new DatePickerDialog(this, R.style.DatePickerTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = Utilities.getFormattedDate(dayOfMonth, month + 1, year);
                facilityDay.setText(date);
            }
        }, startYear, starthMonth, startDate);
    }

    @Override
    public void onResponseFailure(String errorMessage) {
        Utilities.displayToast(this,errorMessage);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.book_facility_button:
                if(LoginSession.bm){
                    bookBM();
                }
                else {
                    BookTenant();
                }
                //call the function
                break;
            case R.id.activity_book_facility_day_edittext:
                mPickerDialog.show();
                break;
        }
    }

    private void BookTenant() {
        String day = facilityDay.getText().toString();
        if (checkInputs(day))
        {
            String facilityName = facilitySpinner.getSelectedItem().toString();
            int facilityId = getFacilityId(facilityName);
            Calendar nextDay = Calendar.getInstance();
            nextDay = Utilities.getCalendarByString(day);
            nextDay.add(Calendar.DAY_OF_MONTH,1);
            MakeBookingRequest bookingRequest = new MakeBookingRequest(facilityId,day,Utilities.getFormattedDate(nextDay));
            try {
                RestClientManager.getInstance(this).getPersonController().createBooking(bookingRequest,this);
            } catch (NetworkErrorException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Utilities.displayToast(this, "Some fields are missing");
        }
    }

    private void bookBM() {
        String day = facilityDay.getText().toString();
        if (checkInputs(day))
        {
            String facilityName = facilitySpinner.getSelectedItem().toString();
            int facilityId = getFacilityId(facilityName);
            Calendar nextDay = Calendar.getInstance();
            nextDay = Utilities.getCalendarByString(day);
            nextDay.add(Calendar.DAY_OF_MONTH,1);
            MakeBookingRequest bookingRequest = new MakeBookingRequest(facilityId,day,Utilities.getFormattedDate(nextDay));
            try {
                RestClientManager.getInstance(this).getBuildingManagerController().createBooking(bookingRequest,this);
            } catch (NetworkErrorException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Utilities.displayToast(this, "Some fields are missing");
        }
    }

    private int getFacilityId(String facilityName) {
        String id = "";
        for (Map.Entry<String, String> facilityMap : mFacilityMap.entrySet()){
            if (facilityMap.getValue().equals(facilityName)){
                id = facilityMap.getKey();
            }
        }
        return Integer.parseInt(id);
    }

    private boolean checkInputs(String... inputs) {
        for (String string : inputs) {
            if (string.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}

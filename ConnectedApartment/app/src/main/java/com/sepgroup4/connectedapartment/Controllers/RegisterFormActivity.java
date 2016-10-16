package com.sepgroup4.connectedapartment.Controllers;

import android.accounts.NetworkErrorException;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.sepgroup4.connectedapartment.Model.Apartment;
import com.sepgroup4.connectedapartment.Model.ApartmentResponse;
import com.sepgroup4.connectedapartment.Model.RegisterRequest;
import com.sepgroup4.connectedapartment.Model.RegisterResponse;
import com.sepgroup4.connectedapartment.Model.RequestResponse;
import com.sepgroup4.connectedapartment.R;
import com.sepgroup4.connectedapartment.Rest.Handlers.RestResponseHandler;
import com.sepgroup4.connectedapartment.Rest.RestClientManager;
import com.sepgroup4.connectedapartment.Utilities;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegisterFormActivity extends AppCompatActivity implements RestResponseHandler, View.OnClickListener {

    private void register() {
        String tEmail = mTenantEmail.getText().toString();

        if (checkEmail(tEmail)) {
            String tFirstName = mTenantFirstName.getText().toString();
            String tLastName = mTenantLastName.getText().toString();
            String tDoB = mTenantDoB.getText().toString();
            String tContact = mTenantContactNumber.getText().toString();

            if (checkInputs(tFirstName, tLastName, tDoB, tContact)) {
                String apartmentName = mSpinner.getSelectedItem().toString();
                int apartmentId = getApartmentId(apartmentName);
                RegisterRequest registerRequest = new RegisterRequest(tEmail, tFirstName, tLastName, tDoB, tContact, apartmentId);
                try {
                    mMyProgressDialog.show("Registering...");
                    RestClientManager.getInstance(RegisterFormActivity.this).getPersonController().registerTenant(registerRequest, this);
                } catch (NetworkErrorException e) {
                    e.printStackTrace();
                }
            }else{
                Utilities.displayToast(this, "Some fields are missing");
            }
        } else {
            mEmailTIL.setError("Invalid email");
        }
    }

    private EditText mTenantFirstName;
    private EditText mTenantLastName;
    private EditText mTenantContactNumber;
    private EditText mTenantEmail;
    private EditText mTenantDoB;
    private Spinner mSpinner;
    private Map<String, String> mApartmentMap;
    private MyProgressDialog mMyProgressDialog;
    private TextInputLayout mEmailTIL;
    private DatePickerDialog mPickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_register_form_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpCalendarPrompt();
        mMyProgressDialog = new MyProgressDialog(this);
        mApartmentMap = new HashMap<>();

        mTenantFirstName = (EditText) findViewById(R.id.activity_register_form_tenant_first_name_edittext);
        mTenantLastName = (EditText) findViewById(R.id.activity_register_form_tenant_last_name_edittext);
        mTenantContactNumber = (EditText) findViewById(R.id.activity_register_form_tenant_contact_number_edittext);
        mTenantEmail = (EditText) findViewById(R.id.activity_register_form_tenant_email_edittext);
        mTenantDoB = (EditText) findViewById(R.id.activity_register_form_tenant_dob_edittext);
        mSpinner = (Spinner) findViewById(R.id.activity_register_form_tenant_apartment_number_spinner);
        mEmailTIL = (TextInputLayout) findViewById(R.id.activity_register_form_profile_email_text_input_layout);
        mTenantDoB.setInputType(0x00000000); //disable the keyboard for this field
        mTenantDoB.setOnClickListener(this);
        getApartments();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_create_account) {
            register();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private boolean checkEmail(String email) {
        return email.contains("@");
    }

    private boolean checkInputs(String... inputs) {
        for (String string : inputs) {
            if (string.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private void populateSpinnerItem(ApartmentResponse apartmentResponse) {
        for (Apartment apartment : apartmentResponse.getApartments()) {
            mApartmentMap.put(apartment.getStringId(), apartment.getApartmentName());
        }
        String[] apartmentNames = new String[mApartmentMap.size()];
        int i = 0;
        for (Map.Entry<String, String> apartment : mApartmentMap.entrySet()) {
            apartmentNames[i] = apartment.getValue();
            i++;
        }
        ArrayAdapter<String> apartmentNameAdapter = new ArrayAdapter<>(this, R.layout.apartment_spinner_layout, apartmentNames);
        mSpinner.setAdapter(apartmentNameAdapter);
    }

    private int getApartmentId(String name) throws NumberFormatException {
        String id = "";
        for (Map.Entry<String, String> apartment : mApartmentMap.entrySet()) {
            if (apartment.getValue().equals(name)) {
                id = apartment.getKey();
            }
        }
        return Integer.parseInt(id);
    }

    @Override
    public void onResponseSuccess(RequestResponse requestResponse) {
        if (requestResponse instanceof RegisterResponse) {
            mMyProgressDialog.hide();
            displayRegisterSuccess();
        } else if (requestResponse instanceof ApartmentResponse) {
            populateSpinnerItem((ApartmentResponse) requestResponse);
        }
    }

    @Override
    public void onResponseFailure(String errorMessage) {
        mMyProgressDialog.hide();
        Utilities.displayToast(this, errorMessage);
    }

    private void displayRegisterSuccess() {
        Utilities.displayToast(this, "You have been registered successfully");
        startActivity(MyActivityManager.intentToBMDashBoard(this));
    }

    private void getApartments() {
        try {
            RestClientManager.getInstance(this).getBuildingManagerController().getApartments(this);
        } catch (NetworkErrorException e) {
            Utilities.displayToast(this, e.getMessage());
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.activity_register_form_tenant_dob_edittext){
            mPickerDialog.show();
        }
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
                mTenantDoB.setText(date);
            }
        }, startYear, starthMonth, startDate);
    }
}

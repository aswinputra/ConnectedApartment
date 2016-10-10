package com.sepgroup4.connectedapartment;

import android.accounts.NetworkErrorException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sepgroup4.connectedapartment.Model.RegisterRequest;
import com.sepgroup4.connectedapartment.Model.RequestResponse;
import com.sepgroup4.connectedapartment.Rest.Handlers.AuthenticationHandler;
import com.sepgroup4.connectedapartment.Rest.Handlers.RestResponseHandler;
import com.sepgroup4.connectedapartment.Rest.RestClient;
import com.sepgroup4.connectedapartment.Rest.RestClientManager;

import java.util.HashMap;
import java.util.Map;

public class RegisterFormActivity extends AppCompatActivity implements RestResponseHandler {

    private EditText mTenantFirstName;
    private EditText mTenantLastName;
    private EditText mTenantContactNumber;
    private EditText mTenantEmail;
    private EditText mTenantDoB;
    private Spinner mSpinner;
    private Map<String, String> mApartmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_register_form_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApartmentMap = new HashMap<>();

        mTenantFirstName = (EditText) findViewById(R.id.activity_register_form_tenant_first_name_edittext);
        mTenantLastName = (EditText) findViewById(R.id.activity_register_form_tenant_last_name_edittext);
        mTenantContactNumber = (EditText) findViewById(R.id.activity_register_form_tenant_contact_number_edittext);
        mTenantEmail = (EditText) findViewById(R.id.activity_register_form_tenant_email_edittext);
        mTenantDoB = (EditText) findViewById(R.id.activity_register_form_tenant_dob_edittext);
        mSpinner = (Spinner) findViewById(R.id.activity_register_form_tenant_apartment_number_spinner);
        mTenantEmail.setText("Sophiesadas");
        mTenantFirstName.setText("Sophie");
        mTenantLastName.setText("Nsdaa");
        mTenantContactNumber.setText("0241412412");
        mTenantDoB.setText("1992-08-12");
        populateSpinnerItem();
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

            String tEmail = mTenantEmail.getText().toString();
            String tFirstName = mTenantFirstName.getText().toString();
            String tLastName = mTenantLastName.getText().toString();
            String tDoB = mTenantDoB.getText().toString();
            String tContact = mTenantContactNumber.getText().toString();
            String apartmentName = mSpinner.getSelectedItem().toString();
            int apartmentId = getApartmentId(apartmentName);
            RegisterRequest registerRequest = new RegisterRequest(tEmail, tFirstName, tLastName, tDoB, tContact, apartmentId);

            try {
                RestClientManager.getInstance(RegisterFormActivity.this).getPersonController().registerTenant(registerRequest, this);
            } catch (NetworkErrorException e) {
                e.printStackTrace();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populateSpinnerItem() {
        mApartmentMap.put("1", "101");
        mApartmentMap.put("2", "102");
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

        Utilities.displayToast(this, "Register Successful");
        Intent intent = new Intent(RegisterFormActivity.this, BMDashboardActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResponseFailure(String errorMessage) {
        Utilities.displayToast(this, errorMessage);
    }
}

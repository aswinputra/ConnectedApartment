package com.sepgroup4.connectedapartment.Controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.sepgroup4.connectedapartment.R;
import com.sepgroup4.connectedapartment.Utilities;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText mNewPasswordET;
    private EditText mConfirmPasswordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNewPasswordET = (EditText) findViewById(R.id.activity_reset_password_new_password_edittext);
        mConfirmPasswordET = (EditText) findViewById(R.id.activity_reset_password_confirm_password_edittext);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.change_password_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_confirm) {
            resetPassword();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void resetPassword() {
        String newPassword = mNewPasswordET.getText().toString();
        String confirmPassword = mConfirmPasswordET.getText().toString();
        if (checkInputs(newPassword, confirmPassword)) {
            //call reset
        } else {
            Utilities.displayToast(this, "Some fields are missing");
        }
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

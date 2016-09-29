package com.sepgroup4.connectedapartment.Rest.Controller;

import android.os.AsyncTask;

import com.sepgroup4.connectedapartment.Model.LoginRequest;
import com.sepgroup4.connectedapartment.Model.LoginResponse;
import com.sepgroup4.connectedapartment.Rest.Handlers.AuthenticationHandler;
import com.sepgroup4.connectedapartment.Rest.RestClient;

import java.io.IOException;

import retrofit2.Call;

/**
 * Created by kiman on 29/09/2016.
 */
public class PersonController {
    private RestClient mRestClient;

    public PersonController(RestClient mRestClient) {
        this.mRestClient = mRestClient;
    }

    public void authenticate(LoginRequest loginRequest, AuthenticationHandler handler){
        new AuthenticateTask(handler).execute(loginRequest);
    }

    private class AuthenticateTask extends AsyncTask<LoginRequest, Void, LoginResponse>{

        private AuthenticationHandler handler;

        public AuthenticateTask(AuthenticationHandler handler) {
            this.handler = handler;
        }

        @Override
        protected LoginResponse doInBackground(LoginRequest... loginRequests) {
            String grantType = loginRequests[0].getGrantType();
            String username = loginRequests[0].getUsername();
            String password = loginRequests[0].getPassword();
            Call<LoginResponse> call = mRestClient.getConnectedApartmentRestApi().authenticate(grantType, username, password);
            LoginResponse loginResponse = null;
            try {
                loginResponse = call.execute().body();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return loginResponse;
        }


        @Override
        protected void onPostExecute(LoginResponse loginResponse) {
            super.onPostExecute(loginResponse);
            if(loginResponse == null){
                handler.onAuthenticationFailure("Incorrect username or password");
            }
            else{
                handler.onAuthenticationSuccess(loginResponse);
            }
        }
    }
}

package com.sepgroup4.connectedapartment.Rest.Controller;

import android.os.AsyncTask;

import com.sepgroup4.connectedapartment.Model.LoginRequest;
import com.sepgroup4.connectedapartment.Model.LoginResponse;
import com.sepgroup4.connectedapartment.Model.LoginSession;
import com.sepgroup4.connectedapartment.Model.NewPassword;
import com.sepgroup4.connectedapartment.Model.RegisterRequest;
import com.sepgroup4.connectedapartment.Model.RegisterResponse;
import com.sepgroup4.connectedapartment.Model.RequestResponse;
import com.sepgroup4.connectedapartment.Rest.Handlers.AuthenticationHandler;
import com.sepgroup4.connectedapartment.Rest.Handlers.RestResponseHandler;
import com.sepgroup4.connectedapartment.Rest.RestClient;
import com.sepgroup4.connectedapartment.Model.UserInfoResponse;

import java.io.IOException;
import java.util.Calendar;

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

    public void getUserInfo(RestResponseHandler handler){
        new GetUserInfoTask(handler);
    }

    public void resetPassword(NewPassword newPassword, RestResponseHandler handler){
        new ResetPasswordTask(handler);
    }

    public void registerTenant(RegisterRequest registerRequest, RestResponseHandler handler){

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

    private class GetUserInfoTask extends AsyncTask<Void, Void, UserInfoResponse>{

        private RestResponseHandler handler;

        public GetUserInfoTask(RestResponseHandler handler) {
            this.handler = handler;
        }

        @Override
        protected UserInfoResponse doInBackground(Void... params) {
            Call<UserInfoResponse> call = mRestClient.getConnectedApartmentRestApi().getUserInfo(LoginSession.userToken);
            UserInfoResponse userInfoResponse = null;
            try {
                userInfoResponse = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return userInfoResponse;
        }

        @Override
        protected void onPostExecute(UserInfoResponse userInfoResponse) {
            if(userInfoResponse == null){
                handler.onResponseFailure("Could not get your information");
            }else {
                //Operation is done successfully on the server
                if (userInfoResponse.getSuccess()) {
                    handler.onResponseSuccess(userInfoResponse);
                } else {
                    handler.onResponseFailure("Operation could not be completed");
                }
            }
            super.onPostExecute(userInfoResponse);
        }
    }

    private class ResetPasswordTask extends AsyncTask<NewPassword, Void, RequestResponse>{

        private RestResponseHandler handler;

        public ResetPasswordTask(RestResponseHandler handler) {
            this.handler = handler;
        }

        @Override
        protected RequestResponse doInBackground(NewPassword... newPasswords) {

            Call<RequestResponse> call = mRestClient.getConnectedApartmentRestApi().resetPassword(newPasswords[0]);
            RequestResponse requestResponse = null;
            try {
                requestResponse = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return requestResponse;
        }

        @Override
        protected void onPostExecute(RequestResponse requestResponse) {
            if(requestResponse == null){
                handler.onResponseFailure("Something's wrong, please try again later");
            }else{
                if (requestResponse.getSuccess()){
                    handler.onResponseSuccess(requestResponse);
                }else{
                    handler.onResponseFailure("Could not change your password, please try again later");
                }
            }
            super.onPostExecute(requestResponse);
        }
    }

    private class RegisterTenant extends AsyncTask<RegisterRequest, Void, RegisterResponse>{

        private RestResponseHandler handler;

        public RegisterTenant(RestResponseHandler handler) {
            this.handler = handler;
        }

        @Override
        protected RegisterResponse doInBackground(RegisterRequest... registerRequests) {
            Call<RegisterResponse> call = mRestClient.getConnectedApartmentRestApi().registerTenant(registerRequests[0]);
            RegisterResponse registerResponse = null;
            try {
                registerResponse = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return registerResponse;
        }

        @Override
        protected void onPostExecute(RegisterResponse registerResponse) {
            if(registerResponse == null){
                handler.onResponseFailure("Something's wrong, please try again later");
            }else{
                if(registerResponse.getSuccess()){
                    handler.onResponseSuccess(registerResponse);
                }else{
                    handler.onResponseFailure("Could not complete registration");
                }
            }
            super.onPostExecute(registerResponse);
        }
    }
}

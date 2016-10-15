package com.sepgroup4.connectedapartment.Rest.Controller;

import android.app.DownloadManager;
import android.os.AsyncTask;
import android.util.Log;

import com.sepgroup4.connectedapartment.Model.BookingResponse;
import com.sepgroup4.connectedapartment.Model.Constants;
import com.sepgroup4.connectedapartment.Model.FacilityResponse;
import com.sepgroup4.connectedapartment.Model.MakeBookingRequest;
import com.sepgroup4.connectedapartment.Model.PasswordChange;
import com.sepgroup4.connectedapartment.Model.LoginRequest;
import com.sepgroup4.connectedapartment.Model.LoginResponse;
import com.sepgroup4.connectedapartment.Model.LoginSession;
import com.sepgroup4.connectedapartment.Model.RegisterRequest;
import com.sepgroup4.connectedapartment.Model.RegisterResponse;
import com.sepgroup4.connectedapartment.Model.RequestResponse;
import com.sepgroup4.connectedapartment.Model.ResetPasswordResponse;
import com.sepgroup4.connectedapartment.Model.SingleFacilityResponse;
import com.sepgroup4.connectedapartment.Model.TenantDetailRequest;
import com.sepgroup4.connectedapartment.Model.TenantInfoResponse;
import com.sepgroup4.connectedapartment.Rest.Handlers.AuthenticationHandler;
import com.sepgroup4.connectedapartment.Rest.Handlers.RestResponseHandler;
import com.sepgroup4.connectedapartment.Rest.RestClient;
import com.sepgroup4.connectedapartment.Model.UserInfoResponse;

import java.io.IOException;

import retrofit2.Call;


public class PersonController {
    private RestClient mRestClient;

    public void registerTenant(RegisterRequest registerRequest, RestResponseHandler handler) {
        new RegisterTenant(handler).execute(registerRequest);
    }

    private class RegisterTenant extends AsyncTask<RegisterRequest, Void, RegisterResponse> {

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
            if (registerResponse == null) {
                handler.onResponseFailure("This email has already been taken");
            } else {
                if (registerResponse.getSuccess()) {
                    handler.onResponseSuccess(registerResponse);
                } else {
                    handler.onResponseFailure("Could not complete registration");
                }
            }
            super.onPostExecute(registerResponse);
        }
    }

    public PersonController(RestClient mRestClient) {
        this.mRestClient = mRestClient;
    }

    public void authenticate(LoginRequest loginRequest, AuthenticationHandler handler) {
        new AuthenticateTask(handler).execute(loginRequest);
    }

    public void getUserInfo(RestResponseHandler handler) {
        new GetUserInfoTask(handler).execute();
    }

    public void resetPassword(String email, RestResponseHandler handler) {
        new ResetPasswordTask(handler).execute(email);
    }

    public void changePassword(PasswordChange passwordChange, RestResponseHandler handler) {
        new ChangePasswordTask(handler).execute(passwordChange);
    }

    public void getTenantInformation(RestResponseHandler handler){
        new GetTenantInformationTask(handler).execute();
    }

    public void updateTenantInformation(TenantDetailRequest tenantDetailRequest, RestResponseHandler handler) {
        new UpdateInformationTask(handler).execute(tenantDetailRequest);
    }

    public void createBooking(MakeBookingRequest request ,RestResponseHandler handler){
        new CreateBookingTask(handler).execute(request);
    }

    public void getBookings(RestResponseHandler handler){
        new GetBookingTask(handler).execute();
    }

    public void cancelBooking(String facilityId, String bookingId, RestResponseHandler handler){
        new CancelBookingTask(handler).execute(facilityId, bookingId);
    }

    public void getFacilities(RestResponseHandler handler){
        new GetFacilitiesTask(handler).execute();
    }

    public void getFacility(int facilityId, RestResponseHandler handler){
        new GetFacilityTask(handler).execute(facilityId);
    }

    private class AuthenticateTask extends AsyncTask<LoginRequest, Void, LoginResponse> {

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
            if (loginResponse == null) {
                handler.onAuthenticationFailure("Incorrect username or password");
            } else {
                handler.onAuthenticationSuccess(loginResponse);
            }
        }
    }

    private class GetUserInfoTask extends AsyncTask<Void, Void, UserInfoResponse> {

        private RestResponseHandler handler;

        public GetUserInfoTask(RestResponseHandler handler) {
            this.handler = handler;
        }

        @Override
        protected UserInfoResponse doInBackground(Void... params) {
            Log.i(Constants.LOG_TAG, LoginSession.userToken);
            Call<UserInfoResponse> call = mRestClient.getConnectedApartmentRestApi().getUserInfo();
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
            if (userInfoResponse == null) {
                handler.onResponseFailure("Could not get your information");
            } else {
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

    private class ResetPasswordTask extends AsyncTask<String, Void, RequestResponse> {

        private RestResponseHandler handler;

        public ResetPasswordTask(RestResponseHandler handler) {
            this.handler = handler;
        }

        @Override
        protected RequestResponse doInBackground(String... emails) {

            Call<ResetPasswordResponse> call = mRestClient.getConnectedApartmentRestApi().resetPassword(emails[0]);
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
            if (requestResponse == null) {
                handler.onResponseFailure("Something's wrong, please try again later");
            } else {
                if (requestResponse.getSuccess()) {
                    handler.onResponseSuccess(requestResponse);
                } else {
                    handler.onResponseFailure("Could not change your password, please try again later");
                }
            }
            super.onPostExecute(requestResponse);
        }
    }



    private class ChangePasswordTask extends AsyncTask<PasswordChange, Void, RequestResponse> {

        private RestResponseHandler handler;

        public ChangePasswordTask(RestResponseHandler handler) {
            this.handler = handler;
        }

        @Override
        protected RequestResponse doInBackground(PasswordChange... params) {
            Call<RequestResponse> call = mRestClient.getConnectedApartmentRestApi().changePassword(params[0]);
            RequestResponse response = null;
            try {
                response = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(RequestResponse requestResponse) {
            if (requestResponse == null) {
                handler.onResponseFailure("Something's wrong, please try again later");
            } else {
                if (requestResponse.getSuccess()) {
                    handler.onResponseSuccess(requestResponse);
                } else {
                    handler.onResponseFailure("Could not change your password");
                }
            }
            super.onPostExecute(requestResponse);
        }
    }

    private class GetTenantInformationTask extends AsyncTask<Void, Void, RequestResponse>{

        private RestResponseHandler handler;

        public GetTenantInformationTask(RestResponseHandler handler) {
            this.handler = handler;
        }

        @Override
        protected RequestResponse doInBackground(Void... params) {
            Call<TenantInfoResponse> call = mRestClient.getConnectedApartmentRestApi().getTenantInfo();
            TenantInfoResponse response = null;
            try {
                response = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(RequestResponse requestResponse) {
            if (requestResponse == null) {
                handler.onResponseFailure("Something's wrong, please try again later");
            } else {
                if (requestResponse.getSuccess()) {
                    handler.onResponseSuccess(requestResponse);
                } else {
                    handler.onResponseFailure("Could not get your detail");
                }
            }
            super.onPostExecute(requestResponse);
        }
    }

    private class UpdateInformationTask extends AsyncTask<TenantDetailRequest, Void, RequestResponse> {

        private RestResponseHandler handler;

        public UpdateInformationTask(RestResponseHandler handler) {
            this.handler = handler;
        }

        @Override
        protected RequestResponse doInBackground(TenantDetailRequest... params) {
            Call<RequestResponse> call = mRestClient.getConnectedApartmentRestApi().updateTenantInfo(params[0]);
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
            if (requestResponse == null) {
                handler.onResponseFailure("Something's wrong, please try again later");
            } else {
                if (requestResponse.getSuccess()) {
                    handler.onResponseSuccess(requestResponse);
                } else {
                    handler.onResponseFailure("Could not update your detail");
                }
            }
            super.onPostExecute(requestResponse);
        }
    }

    private class CreateBookingTask extends AsyncTask<MakeBookingRequest, Void, RequestResponse>{

        private RestResponseHandler handler;

        public CreateBookingTask(RestResponseHandler handler) {
            this.handler = handler;
        }

        @Override
        protected RequestResponse doInBackground(MakeBookingRequest... params) {
            Call<RequestResponse> call = mRestClient.getConnectedApartmentRestApi().createTenantBooking(params[0]);
            RequestResponse response = null;
            try {
                response = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(RequestResponse requestResponse) {
            if (requestResponse == null) {
                handler.onResponseFailure("Something's wrong, please try again later");
            } else {
                if (requestResponse.getSuccess()) {
                    handler.onResponseSuccess(requestResponse);
                } else {
                    handler.onResponseFailure("Could not make booking, please try again later");
                }
            }
            super.onPostExecute(requestResponse);
        }
    }

    private class GetBookingTask extends AsyncTask<Void, Void, RequestResponse>{

        private RestResponseHandler handler;

        public GetBookingTask(RestResponseHandler handler) {
            this.handler = handler;
        }

        @Override
        protected RequestResponse doInBackground(Void... params) {
            Call<BookingResponse> call = mRestClient.getConnectedApartmentRestApi().getTenantBookings();
            BookingResponse response = null;
            try {
                response = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(RequestResponse requestResponse) {
            if (requestResponse == null) {
                handler.onResponseFailure("Something's wrong, please try again later");
            } else {
                if (requestResponse.getSuccess()) {
                    handler.onResponseSuccess(requestResponse);
                } else {
                    handler.onResponseFailure("Could not get your booking, please try again later");
                }
            }
            super.onPostExecute(requestResponse);
        }
    }

    private class CancelBookingTask extends AsyncTask<String, Void, RequestResponse>{

        private RestResponseHandler handler;

        public CancelBookingTask(RestResponseHandler handler) {
            this.handler = handler;
        }

        @Override
        protected RequestResponse doInBackground(String... params) {
            Call<RequestResponse> call = mRestClient.getConnectedApartmentRestApi().cancelTenantBooking(params[0], params[1]);
            RequestResponse response = null;
            try {
                response = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(RequestResponse requestResponse) {
            if (requestResponse == null) {
                handler.onResponseFailure("Something's wrong, please try again later");
            } else {
                if (requestResponse.getSuccess()) {
                    handler.onResponseSuccess(requestResponse);
                } else {
                    handler.onResponseFailure("Could not cancel, please try again later");
                }
            }
            super.onPostExecute(requestResponse);
        }
    }

    private class GetFacilitiesTask extends AsyncTask<Void, Void, RequestResponse>{

        private RestResponseHandler handler;


        public GetFacilitiesTask(RestResponseHandler handler) {
            this.handler = handler;
        }

        @Override
        protected RequestResponse doInBackground(Void... params) {
            Call<FacilityResponse> call = mRestClient.getConnectedApartmentRestApi().getTenantFacilities();
            FacilityResponse response = null;
            try {
                response = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(RequestResponse requestResponse) {
            if (requestResponse == null) {
                handler.onResponseFailure("Something's wrong, please try again later");
            } else {
                if (requestResponse.getSuccess()) {
                    handler.onResponseSuccess(requestResponse);
                } else {
                    handler.onResponseFailure("Could not get facilities, please try again later");
                }
            }
            super.onPostExecute(requestResponse);
        }
    }

    private class GetFacilityTask extends AsyncTask<Integer, Void, RequestResponse>{

        private RestResponseHandler handler;

        public GetFacilityTask(RestResponseHandler handler) {
            this.handler = handler;
        }

        @Override
        protected SingleFacilityResponse doInBackground(Integer... params) {
            Call<SingleFacilityResponse> call = mRestClient.getConnectedApartmentRestApi().getTenantFacility(params[0]);
            SingleFacilityResponse response = null;
            try {
                response = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(RequestResponse requestResponse) {
            if (requestResponse == null) {
                handler.onResponseFailure("Something's wrong, please try again later");
            } else {
                if (requestResponse.getSuccess()) {
                    handler.onResponseSuccess(requestResponse);
                } else {
                    handler.onResponseFailure("Could not get facility, please try again later");
                }
            }
            super.onPostExecute(requestResponse);
        }
    }
}

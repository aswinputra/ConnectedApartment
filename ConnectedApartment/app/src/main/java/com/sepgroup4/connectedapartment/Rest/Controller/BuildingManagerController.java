package com.sepgroup4.connectedapartment.Rest.Controller;

import android.os.AsyncTask;

import com.sepgroup4.connectedapartment.Model.ApartmentResponse;
import com.sepgroup4.connectedapartment.Model.BookingResponse;
import com.sepgroup4.connectedapartment.Model.FacilityResponse;
import com.sepgroup4.connectedapartment.Model.MakeBookingRequest;
import com.sepgroup4.connectedapartment.Model.RequestResponse;
import com.sepgroup4.connectedapartment.Rest.Handlers.RestResponseHandler;
import com.sepgroup4.connectedapartment.Rest.RestClient;

import java.io.IOException;

import retrofit2.Call;

/**
 * Created by kiman on 10/10/2016.
 */

public class BuildingManagerController {
    private RestClient mRestClient;

    public BuildingManagerController(RestClient mRestClient) {
        this.mRestClient = mRestClient;
    }

    public void getApartments(RestResponseHandler handler){
        new GetApartmentTask(handler).execute();
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

    private class GetApartmentTask extends AsyncTask<Void, Void, ApartmentResponse>{

        private RestResponseHandler handler;

        public GetApartmentTask(RestResponseHandler handler) {
            this.handler = handler;
        }

        @Override
        protected ApartmentResponse doInBackground(Void... params) {
            Call<ApartmentResponse> call = mRestClient.getConnectedApartmentRestApi().getApartments();
            ApartmentResponse apartmentResponse = null;
            try {
                apartmentResponse = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return apartmentResponse;
        }

        @Override
        protected void onPostExecute(ApartmentResponse apartmentResponse) {
            if(apartmentResponse == null){
                handler.onResponseFailure("Something's wrong, please try again later");
            }else{
                if(apartmentResponse.getSuccess()){
                    handler.onResponseSuccess(apartmentResponse);
                }else{
                    handler.onResponseFailure("Could not perform this, try again later");
                }
            }
            super.onPostExecute(apartmentResponse);
        }
    }

    private class CreateBookingTask extends AsyncTask<MakeBookingRequest, Void, RequestResponse>{

        private RestResponseHandler handler;

        public CreateBookingTask(RestResponseHandler handler) {
            this.handler = handler;
        }

        @Override
        protected RequestResponse doInBackground(MakeBookingRequest... params) {
            Call<RequestResponse> call = mRestClient.getConnectedApartmentRestApi().createBMBooking(params[0]);
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
                handler.onResponseFailure("The facility is not available on that day");
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
            Call<BookingResponse> call = mRestClient.getConnectedApartmentRestApi().getBMBookings();
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
            Call<RequestResponse> call = mRestClient.getConnectedApartmentRestApi().cancelBMBooking(params[0], params[1]);
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
            Call<FacilityResponse> call = mRestClient.getConnectedApartmentRestApi().getBMFacilities();
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
}

package com.sepgroup4.connectedapartment.Rest.Controller;

import android.os.AsyncTask;

import com.sepgroup4.connectedapartment.Model.ApartmentResponse;
import com.sepgroup4.connectedapartment.Model.RequestResponse;
import com.sepgroup4.connectedapartment.Rest.Handlers.RestResponseHandler;
import com.sepgroup4.connectedapartment.Rest.RestClient;
import com.sepgroup4.connectedapartment.Utilities;

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
}

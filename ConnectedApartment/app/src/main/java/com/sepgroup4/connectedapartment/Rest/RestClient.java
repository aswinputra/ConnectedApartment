package com.sepgroup4.connectedapartment.Rest;

import com.sepgroup4.connectedapartment.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private ConnectedApartmentRestApi mConnectedApartmentRestApi;

    public RestClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header(Constants.APP_KEY, Constants.APP_KEY_VALUE)
                        .header(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_VALUE)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        mConnectedApartmentRestApi = retrofit.create(ConnectedApartmentRestApi.class);
    }

    public ConnectedApartmentRestApi getConnectedApartmentRestApi() {
        return mConnectedApartmentRestApi;
    }
}

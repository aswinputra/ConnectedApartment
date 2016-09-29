package com.sepgroup4.connectedapartment.Rest;

import com.sepgroup4.connectedapartment.Model.LoginRequest;
import com.sepgroup4.connectedapartment.Model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ConnectedApartmentRestApi {

    @FormUrlEncoded
    @POST("Token")
    Call<LoginResponse> authenticate(@Field("grant_type") String grantType, @Field("username") String username,
                                     @Field("password") String password);

//    @POST("facility/book")
//    Call<FacilityBookingResponse> bookFacility(@Body FacilityBookingRequest facilityBookingRequest);
//
//    @GET("facility/status")
//    Call<FacilityStatus> getFacilityStatus(@Body FacilityStatusRequest facilityStatusRequest);

//    @POST("api/Account/RegisterTenant")
//    Call<> registerTenant()

}





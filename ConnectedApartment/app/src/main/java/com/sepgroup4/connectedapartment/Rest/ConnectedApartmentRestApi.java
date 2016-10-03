package com.sepgroup4.connectedapartment.Rest;

import com.sepgroup4.connectedapartment.Model.LoginResponse;
import com.sepgroup4.connectedapartment.Model.NewPassword;
import com.sepgroup4.connectedapartment.Model.RegisterRequest;
import com.sepgroup4.connectedapartment.Model.RegisterResponse;
import com.sepgroup4.connectedapartment.Model.RequestResponse;
import com.sepgroup4.connectedapartment.Model.UserInfoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ConnectedApartmentRestApi {

    @FormUrlEncoded
    @POST("Token")
    Call<LoginResponse> authenticate(@Field("grant_type") String grantType, @Field("username") String username,
                                     @Field("password") String password);

    Call<UserInfoResponse> getUserInfo(@Query("Authorization") String userToken);

    Call<RequestResponse> resetPassword(@Body NewPassword newPassword);

    Call<RegisterResponse> registerTenant(@Body RegisterRequest registerRequest);

//    @POST("facility/book")
//    Call<FacilityBookingResponse> bookFacility(@Body FacilityBookingRequest facilityBookingRequest);
//
//    @GET("facility/status")
//    Call<FacilityStatus> getFacilityStatus(@Body FacilityStatusRequest facilityStatusRequest);

//    @POST("api/Account/RegisterTenant")
//    Call<> registerTenant()

}





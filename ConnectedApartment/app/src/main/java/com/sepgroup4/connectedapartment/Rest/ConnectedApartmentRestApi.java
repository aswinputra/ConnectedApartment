package com.sepgroup4.connectedapartment.Rest;

import com.sepgroup4.connectedapartment.Model.ApartmentResponse;
import com.sepgroup4.connectedapartment.Model.ChangePassword;
import com.sepgroup4.connectedapartment.Model.LoginResponse;
import com.sepgroup4.connectedapartment.Model.NewPassword;
import com.sepgroup4.connectedapartment.Model.RegisterRequest;
import com.sepgroup4.connectedapartment.Model.RegisterResponse;
import com.sepgroup4.connectedapartment.Model.RequestResponse;
import com.sepgroup4.connectedapartment.Model.ResetPasswordResponse;
import com.sepgroup4.connectedapartment.Model.UserInfoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ConnectedApartmentRestApi {

    @FormUrlEncoded
    @POST("Token")
    Call<LoginResponse> authenticate(@Field("grant_type") String grantType, @Field("username") String username,
                                     @Field("password") String password);

    @GET("api/Account/UserInfo")
    Call<UserInfoResponse> getUserInfo();

    @POST("api/Account/ResetPassword")
    Call<ResetPasswordResponse> resetPassword(@Query("email") String email);

    @POST("api/Account/RegisterTenant")
    Call<RegisterResponse> registerTenant(@Body RegisterRequest registerRequest);

    @GET("api/BuildingManager/FetchApartments")
    Call<ApartmentResponse> getApartments();
}





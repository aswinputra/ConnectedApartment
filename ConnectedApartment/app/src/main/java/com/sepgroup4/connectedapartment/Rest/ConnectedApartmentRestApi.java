package com.sepgroup4.connectedapartment.Rest;

import com.sepgroup4.connectedapartment.Model.ApartmentResponse;
import com.sepgroup4.connectedapartment.Model.BookingResponse;
import com.sepgroup4.connectedapartment.Model.FacilityResponse;
import com.sepgroup4.connectedapartment.Model.MakeBookingRequest;
import com.sepgroup4.connectedapartment.Model.PasswordChange;
import com.sepgroup4.connectedapartment.Model.LoginResponse;
import com.sepgroup4.connectedapartment.Model.RegisterRequest;
import com.sepgroup4.connectedapartment.Model.RegisterResponse;
import com.sepgroup4.connectedapartment.Model.RequestResponse;
import com.sepgroup4.connectedapartment.Model.ResetPasswordResponse;
import com.sepgroup4.connectedapartment.Model.TenantInfoResponse;
import com.sepgroup4.connectedapartment.Model.TenantDetailRequest;
import com.sepgroup4.connectedapartment.Model.UserInfoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ConnectedApartmentRestApi {

    @POST("api/Account/RegisterTenant")
    Call<RegisterResponse> registerTenant(@Body RegisterRequest registerRequest);

    @FormUrlEncoded
    @POST("Token")
    Call<LoginResponse> authenticate(@Field("grant_type") String grantType, @Field("username") String username,
                                     @Field("password") String password);

    @GET("api/Account/UserInfo")
    Call<UserInfoResponse> getUserInfo();

    @POST("api/Account/ResetPassword")
    Call<ResetPasswordResponse> resetPassword(@Query("email") String email);

    @GET("api/BuildingManager/FetchApartments")
    Call<ApartmentResponse> getApartments();

    @GET("api/Tenant/TenantInfo")
    Call<TenantInfoResponse> getTenantInfo();

    @PUT("api/Tenant/UpdateTenant")
    Call<RequestResponse> updateTenantInfo(@Body TenantDetailRequest tenantDetailRequest);

    @POST("api/Account/ChangePassword")
    Call<RequestResponse> changePassword(@Body PasswordChange passwordChange);

    @POST("api/BuildingManager/CreateBooking")
    Call<RequestResponse> createBMBooking(@Body MakeBookingRequest makeBookingRequest);

    @POST("api/Tenant/CreateBooking")
    Call<RequestResponse> createTenantBooking(@Body MakeBookingRequest makeBookingRequest);

    @GET("api/Tenant/FetchPersonBookings")
    Call<BookingResponse> getTenantBookings();

    @GET("api/BuildingManager/FetchPersonBookings")
    Call<BookingResponse> getBMBookings();

    @DELETE("api/Tenant/CancelBooking")
    Call<RequestResponse> cancelTenantBooking(@Query("FacilityId") String facilityId,
                                        @Query("BookingId") String bookingId);

    @DELETE("api/BuildingManager/CancelBooking")
    Call<RequestResponse> cancelBMBooking(@Query("FacilityId") String facilityId,
                                        @Query("BookingId") String bookingId);

    @GET("api/Tenant/FetchFacilities")
    Call<FacilityResponse> getTenantFacilities();

    @GET("api/BuildingManager/FetchFacilities")
    Call<FacilityResponse> getBMFacilities();

}





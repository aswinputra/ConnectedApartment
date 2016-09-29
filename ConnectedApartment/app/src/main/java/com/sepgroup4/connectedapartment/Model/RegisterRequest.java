package com.sepgroup4.connectedapartment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterRequest {

    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("DoB")
    @Expose
    private String doB;
    @SerializedName("Phone")
    @Expose
    private String phone;
    @SerializedName("ApartmentId")
    @Expose
    private Integer apartmentId;

}
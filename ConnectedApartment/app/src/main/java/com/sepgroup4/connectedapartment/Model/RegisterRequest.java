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

    public RegisterRequest(String email, String firstName, String lastName, String doB, String phone, Integer apartmentId) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.doB = doB;
        this.phone = phone;
        this.apartmentId = apartmentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDoB() {
        return doB;
    }

    public void setDoB(String doB) {
        this.doB = doB;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Integer apartmentId) {
        this.apartmentId = apartmentId;
    }
}
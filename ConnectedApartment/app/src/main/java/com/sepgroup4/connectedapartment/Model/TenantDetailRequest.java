package com.sepgroup4.connectedapartment.Model;

/**
 * Created by kiman on 14/10/2016.
 */

public class TenantDetailRequest {

    private String FirstName;
    private String LastName;
    private String DoB;
    private String Phone;

    public TenantDetailRequest(String firstName, String lastName, String doB, String phone) {
        FirstName = firstName;
        LastName = lastName;
        DoB = doB;
        Phone = phone;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getDoB() {
        return DoB;
    }

    public String getPhone() {
        return Phone;
    }
}

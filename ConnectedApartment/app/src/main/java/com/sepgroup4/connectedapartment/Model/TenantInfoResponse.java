package com.sepgroup4.connectedapartment.Model;

/**
 * Created by kiman on 14/10/2016.
 */

public class TenantInfoResponse extends RequestResponse {

    private TenantInfo Result;

    public TenantInfoResponse(Boolean isSuccess, String message, TenantInfo result) {
        super(isSuccess, message);
        Result = result;
    }

    public TenantInfo getResult() {
        return Result;
    }
}

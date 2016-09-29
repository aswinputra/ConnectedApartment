package com.sepgroup4.connectedapartment.Rest.Handlers;

import com.sepgroup4.connectedapartment.Model.RequestResponse;

/**
 * Created by kiman on 29/09/2016.
 */

public interface RestResponseHandler {
    void onResponseSuccess(RequestResponse requestResponse);
    void onResponseFailure(String errorMessage);
}

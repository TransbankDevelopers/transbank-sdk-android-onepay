package cl.ionix.tbk_ewallet_sdk_android.dto;

import cl.ionix.tbk_ewallet_sdk_android.http.ResponseCode;

/**
 * Created by psepulveda on 05/04/18.
 */

public class GenericResponse<T> {
    private ResponseCode responseCode;
    private String description;
    private T result;

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}

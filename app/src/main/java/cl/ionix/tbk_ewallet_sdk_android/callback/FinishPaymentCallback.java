package cl.ionix.tbk_ewallet_sdk_android.callback;

import cl.ionix.tbk_ewallet_sdk_android.dto.GetTransactionNumberResponse;
import cl.ionix.tbk_ewallet_sdk_android.http.ResponseCode;

/**
 * Created by psepulveda on 06/04/18.
 */

public interface FinishPaymentCallback {

    public void failure(ResponseCode responseCode, String message);
    public void success(GetTransactionNumberResponse data);
}

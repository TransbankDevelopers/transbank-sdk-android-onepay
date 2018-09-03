package cl.ionix.tbk_ewallet_sdk_android.http;

/**
 * Created by psepulveda on 06/04/18.
 */

public enum ResponseCode {
    OK,
    ERROR,
    INVALID_PLUGIN_VERSION,
    INVALID_APP_KEY,
    COMMERCE_NOT_FOUND,
    UNAVAILABLE_COMMERCE,
    SIGN_VALIDATION_ERROR,
    INVALID_TRANSACTION_SIGN,
    INVALID_TRANSACTION,
    INVALID_TRANSACTION_STATUS,
}

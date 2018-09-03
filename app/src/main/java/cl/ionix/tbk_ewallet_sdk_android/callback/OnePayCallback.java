package cl.ionix.tbk_ewallet_sdk_android.callback;


import java.io.Serializable;

import cl.ionix.tbk_ewallet_sdk_android.Error;

/**
 *
 * Created by psepulveda on 22/03/18.
 */

public interface OnePayCallback extends Serializable {

    void failure(Error error, String description);

}

package cl.ionix.tbk_ewallet_sdk_android.http;

import cl.ionix.tbk_ewallet_sdk_android.dto.GenericResponse;
import cl.ionix.tbk_ewallet_sdk_android.dto.GetTransactionNumberRequest;
import cl.ionix.tbk_ewallet_sdk_android.dto.GetTransactionNumberResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by psepulveda on 05/04/18.
 */

public interface EwalletPluginServices {

    EwalletPluginServices services = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build().create(EwalletPluginServices.class);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json; charset=utf-8",
            "User-Agent: OnePaySdk"
    })
    @POST(Constants.GET_TRANSACTION_NUMBER_PATH)
    Call<GenericResponse<GetTransactionNumberResponse>> getTransactionNumber(@Body GetTransactionNumberRequest request);

}

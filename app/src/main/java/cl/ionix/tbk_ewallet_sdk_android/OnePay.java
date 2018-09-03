package cl.ionix.tbk_ewallet_sdk_android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.util.List;

import cl.ionix.tbk_ewallet_sdk_android.callback.FinishPaymentCallback;
import cl.ionix.tbk_ewallet_sdk_android.callback.OnePayCallback;
import cl.ionix.tbk_ewallet_sdk_android.dto.GenericResponse;
import cl.ionix.tbk_ewallet_sdk_android.dto.GetTransactionNumberRequest;
import cl.ionix.tbk_ewallet_sdk_android.dto.GetTransactionNumberResponse;
import cl.ionix.tbk_ewallet_sdk_android.http.EwalletPluginServices;
import cl.ionix.tbk_ewallet_sdk_android.http.ResponseCode;
import retrofit2.Response;

/**
 *
 * Created by psepulveda on 22/03/18.
 *
 */

public class OnePay {

    private Context context;
    private static final String INTENT_ACTION = "cl.ionix.ewallet.APP_2_APP_ACTION";
    private static final String MARKET_URI = "market://details?id=cl.ionix.ewallet";
    private static final String ONE_PAY_SCHEME = "onepay:";

    public OnePay(Context context) {
        this.context = context;
    }

    public void initPayment(String occ, OnePayCallback callback) {
        if(occ == null || occ.length() == 0) {
            callback.failure(Error.INVALID_OCC, context.getString(R.string.one_pay_invalid_occ));
            return;
        }

        startActivity(occ, callback);
    }

    public void installOnePay() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_URI));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public boolean isOnePayInstalled() {
        Uri uri = Uri.parse(ONE_PAY_SCHEME);
        Intent intent = new Intent(INTENT_ACTION, uri);

        return isIntentSafe(intent);
    }

    public void finishPayment(final String occ, final String externalUniqueNumber, final Long issuedAt, final String sharedSecret, final String appKey, final String apiKey, final FinishPaymentCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    GetTransactionNumberRequest request = new GetTransactionNumberRequest();

                    request.setExternalUniqueNumber(externalUniqueNumber);
                    request.setIssuedAt(issuedAt);
                    request.setOcc(occ);
                    request.setSignature(Utils.generateHMACSHA256WithB64(request.toStringHasheable(), sharedSecret));
                    request.setApiKey(apiKey);
                    request.setAppKey(appKey);

                    Response<GenericResponse<GetTransactionNumberResponse>> response = EwalletPluginServices.services.getTransactionNumber(request).execute();

                    afterFinishPayment(response, callback);
                } catch(Exception e) {
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callback.failure(ResponseCode.ERROR, context.getString(R.string.one_pay_connection_error));
                        }
                    });
                }
            }
        }).start();
    }

    private void afterFinishPayment(final Response<GenericResponse<GetTransactionNumberResponse>> response, final FinishPaymentCallback callback) {
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(response != null && response.body() != null) {
                    GenericResponse<GetTransactionNumberResponse> responseData = response.body();

                    if(responseData.getResponseCode() != ResponseCode.OK) {
                        callback.failure(responseData.getResponseCode(), responseData.getDescription());
                    } else {
                        callback.success(responseData.getResult());
                    }
                } else {
                    callback.failure(ResponseCode.ERROR, context.getString(R.string.one_pay_connection_error));
                }
            }
        });
    }

    private void startActivity(String occ, OnePayCallback callback) {
        Uri uri = Uri.parse(ONE_PAY_SCHEME);
        Intent intent = new Intent(INTENT_ACTION, uri);

        intent.putExtra("occ", occ);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

        if(!isIntentSafe(intent)){
            callback.failure(Error.ONE_PAY_NOT_INSTALLED, context.getString(R.string.one_pay_not_installed));
            return;
        }

        context.startActivity(intent);
    }

    private boolean isIntentSafe(Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);

        return activities.size() > 0;
    }
}

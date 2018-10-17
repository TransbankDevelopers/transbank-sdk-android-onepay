package cl.ionix.tbk_ewallet_sdk_android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.util.List;

import cl.ionix.tbk_ewallet_sdk_android.callback.OnePayCallback;


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

package cl.ionix.tbk_ewallet_sdk_android;

import android.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by psepulveda on 06/04/18.
 */

public class Utils {

    protected static String generateHMACSHA256WithB64(String data, String secret) {
        try {

            SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");

            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(keySpec);
            byte[] rawHmac = mac.doFinal(data.getBytes());

            return Base64.encodeToString(rawHmac, Base64.NO_WRAP);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}

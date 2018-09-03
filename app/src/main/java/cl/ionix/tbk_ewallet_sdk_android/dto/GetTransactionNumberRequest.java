package cl.ionix.tbk_ewallet_sdk_android.dto;

/**
 * Created by psepulveda on 05/04/18.
 */

public class GetTransactionNumberRequest {

    private String occ;
    private Long issuedAt;
    private String externalUniqueNumber;
    private String signature;
    private String appKey;
    private String apiKey;

    public String toStringHasheable(){
        StringBuilder h = new StringBuilder();
        h.append(occ.length());
        h.append(occ);
        h.append(externalUniqueNumber.length());
        h.append(externalUniqueNumber);
        h.append(issuedAt.toString().length());
        h.append(issuedAt);

        return h.toString();
    }

    public String getOcc() {
        return occ;
    }

    public void setOcc(String occ) {
        this.occ = occ;
    }

    public Long getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Long issuedAt) {
        this.issuedAt = issuedAt;
    }

    public String getExternalUniqueNumber() {
        return externalUniqueNumber;
    }

    public void setExternalUniqueNumber(String externalUniqueNumber) {
        this.externalUniqueNumber = externalUniqueNumber;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}

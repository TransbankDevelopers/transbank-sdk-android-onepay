package cl.ionix.tbk_ewallet_sdk_android.dto;

/**
 * Created by psepulveda on 05/04/18.
 */

public class GetTransactionNumberResponse {

    private String occ;
    private String authorizationCode;
    private Long issuedAt;
    private String signature;
    private Long amount;
    private String transactionDesc;
    private Long installmentsAmount;
    private Integer installmentsNumber;
    private String buyOrder;

    public String getOcc() {
        return occ;
    }

    public void setOcc(String occ) {
        this.occ = occ;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public Long getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Long issuedAt) {
        this.issuedAt = issuedAt;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }

    public Long getInstallmentsAmount() {
        return installmentsAmount;
    }

    public void setInstallmentsAmount(Long installmentsAmount) {
        this.installmentsAmount = installmentsAmount;
    }

    public Integer getInstallmentsNumber() {
        return installmentsNumber;
    }

    public void setInstallmentsNumber(Integer installmentsNumber) {
        this.installmentsNumber = installmentsNumber;
    }

    public String getBuyOrder() {
        return buyOrder;
    }

    public void setBuyOrder(String buyOrder) {
        this.buyOrder = buyOrder;
    }
}

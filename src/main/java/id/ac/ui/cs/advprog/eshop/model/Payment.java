package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    Map<String, String> paymentData;
    String status;

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;

        if (method == null || (!method.equals("Voucher") && !method.equals("BankTransfer"))){
            throw new IllegalArgumentException();
        }

        this.method = method;
        if (this.method.equals("Voucher")){
            if (!paymentData.containsKey("voucherCode")) {
                throw new IllegalArgumentException();
            }

            if (paymentData.get("voucherCode").length() != 16){
                this.status = "REJECTED";
            }
            else if (!paymentData.get("voucherCode").startsWith("ESHOP")){
                this.status = "REJECTED";
            }
            else if (countNumericalChar(paymentData.get("voucherCode")) != 8){
                this.status = "REJECTED";
            }
            else {
                this.status = "SUCCESS";
            }
        }
        else {
            if (!paymentData.containsKey("bankName") || !paymentData.containsKey("referenceCode")){
                throw new IllegalArgumentException();
            }

            if (paymentData.get("bankName") == null || paymentData.get("bankName").equals("")){
                this.status = "REJECTED";
            }
            else if (paymentData.get("referenceCode") == null || paymentData.get("referenceCode").equals("")){
                this.status = "REJECTED";
            }
            else {
                this.status = "SUCCESS";
            }
        }
    }

    public void setStatus(String newStatus){
        if (newStatus == null || (!newStatus.equals("REJECTED") && !newStatus.equals("SUCCESS"))){
            throw new IllegalArgumentException();
        }
        this.status = newStatus;
    }

    private static int countNumericalChar(String voucherCode){
        int count = 0;
        for (int i = 0; i < voucherCode.length(); i++){
            if (voucherCode.charAt(i) >= 48 && voucherCode.charAt(i) <= 57){
                count++;
            }
        }
        return count;
    }
}

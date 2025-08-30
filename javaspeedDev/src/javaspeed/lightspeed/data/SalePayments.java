/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaspeed.lightspeed.data;

import org.json.JSONObject;

/**
 *
 * @author rfancher
 */
public class SalePayments extends LightspeedData {

    double payment;
    String registerId;
    String registerPaymentTypeId;
    String paymentDate;

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getRegisterPaymentTypeId() {
        return registerPaymentTypeId;
    }

    public void setRegisterPaymentTypeId(String registerPaymentTypeId) {
        this.registerPaymentTypeId = registerPaymentTypeId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String objectName() {
        return "SALE_PAYMENTS:";
    }

    @Override
    protected void loadAdditionalJSONData(JSONObject json) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void addAdditionalJSONData(JSONObject json) {
        json.put("register_id", this.getRegisterId());
        json.put("retailer_payment_type_id", CommonKeys.DEFAULT_REGISTER_PAYMENT_TYPE_ID);
        json.put("payment_date", this.getPaymentDate());
        json.put("amount", this.getPayment());
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaspeed.lightspeed.data;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author rfancher
 */
public class Sale extends LightspeedData {

    private Customer customer;
    private String source;
    private String sourceId;
    private String registerId;
    private String marketId;
    private String userId;
    private String userName;
    private String saleDate;
    private String createdDate;
    private String updatedDate;
    private String note;
    private String status;
    private String state;
    private String shortCode;
    private String invoiceNumber;
    private String accountsTransactioId;
    private String returnFor;
    private SaleItem[] registerSaleProducts;
    private SalePayments[] registerSalePayments;

    public Sale() {
        this.registerSalePayments = new SalePayments[0];
        this.registerSaleProducts = new SaleItem[0];
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer toSet) {
        this.customer = toSet;
    }

    public String getCustomerName() {
        return this.getCustomer().getName();
    }

    public void addSaleItem(SaleItem toAdd) {

        SaleItem[] toSet = new SaleItem[this.registerSaleProducts.length + 1];

        for (int index = 0; index < this.registerSaleProducts.length; index++) {
            toSet[index] = this.registerSaleProducts[index];
        }

        toSet[this.registerSaleProducts.length] = toAdd;
        this.registerSaleProducts = toSet;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getAccountsTransactioId() {
        return accountsTransactioId;
    }

    public void setAccountsTransactioId(String accountsTransactioId) {
        this.accountsTransactioId = accountsTransactioId;
    }

    public String getReturnFor() {
        return returnFor;
    }

    public void setReturnFor(String returnFor) {
        this.returnFor = returnFor;
    }

    public SaleItem[] getRegisterSaleProducts() {
        return registerSaleProducts;
    }

    public void setRegisterSaleProducts(SaleItem[] registerSaleProducts) {
        this.registerSaleProducts = registerSaleProducts;
    }

    public SalePayments[] getRegisterSalePayments() {
        return registerSalePayments;
    }

    public void setRegisterSalePayments(SalePayments[] registerSalePayments) {
        this.registerSalePayments = registerSalePayments;
    }

    @Override
    public String objectName() {
        return "SALE";
    }

    @Override
    protected void loadAdditionalJSONData(JSONObject json) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void addAdditionalJSONData(JSONObject json) {

        LightspeedDataParser.setJSONString(json, "register_id", this.getRegisterId());
        LightspeedDataParser.setJSONString(json, "user_id", this.getUserId());
        LightspeedDataParser.setJSONString(json, "customer_id", this.getCustomer().getId());
        LightspeedDataParser.setJSONString(json, "note", this.getNote());
        LightspeedDataParser.setJSONString(json, "invoice_number", this.getInvoiceNumber());
        LightspeedDataParser.setJSONInteger(json, "invoice_sequence", Integer.parseInt(this.getInvoiceNumber()));
        LightspeedDataParser.setJSONString(json, "sale_date", this.getSaleDate());
        LightspeedDataParser.setJSONString(json, "status", "CLOSED");

        JSONArray saleProducts = new JSONArray(this.registerSaleProducts.length);
        double totalCost = 0;
        for (int index = 0; index < this.registerSaleProducts.length; index++) {
            SaleItem item = this.registerSaleProducts[index];
            JSONObject data = item.toJSON();
            data.remove("id");
            saleProducts.put(index, data);
            totalCost += item.getPrice() * item.getQuantity();
        }
        json.put("register_sale_products", saleProducts);

        SalePayments payment = new SalePayments();

        payment.setPayment(totalCost);
        payment.setRegisterId(this.getRegisterId());
        payment.setPaymentDate(this.getSaleDate());

        JSONArray payments = new JSONArray(1);
        JSONObject paymentJson = payment.toJSON();
        paymentJson.remove("id");
        payments.put(0, paymentJson);
        json.put("register_sale_payments", payments);
    }

}

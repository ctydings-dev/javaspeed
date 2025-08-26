/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaspeed.lightspeed.data;

import org.json.JSONObject;

/**
 *
 * @author ctydi
 */
public class Customer extends PostalAddressedData {

    private String firstName;
    private String lastName;
    private String code;
    private String updated;
    private String deleted;
    private double balance;
    private String ytd;
    private String birthday;
    private String sex;
    private String custom1;
    private String custom2;
    private String custom3;
    private String custom4;
    private String note;
    private String sourceId;
    private String email;
    private String phone;
    private String mobile;
    private double loyaltyBalance;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCode() {
        return code;
    }

    public String getUpdated() {
        return updated;
    }

    public String getDeleted() {
        return deleted;
    }

    public double getBalance() {
        return balance;
    }

    public String getYtd() {
        return ytd;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getSex() {
        return sex;
    }

    public String getCustom1() {
        return custom1;
    }

    public String getCustom2() {
        return custom2;
    }

    public String getCustom3() {
        return custom3;
    }

    public String getCustom4() {
        return custom4;
    }

    public String getNote() {
        return note;
    }

    @Override
    public String objectName() {
        return "customer";
    }

    @Override
    protected void loadAdditionalJSONData(JSONObject json) {
        this.firstName = LightspeedDataParser.getJSONString(json, "first_name");
        this.code = LightspeedDataParser.getJSONString(json, "customer_code");
        this.firstName = LightspeedDataParser.getJSONString(json, "first_name");
        this.lastName = LightspeedDataParser.getJSONString(json, "last_name");
        this.sourceId = LightspeedDataParser.getJSONString(json, "source_unqiue_id");
        this.email = LightspeedDataParser.getJSONString(json, "email");
//        this.ytd = LightspeedDataParser.getJSONString(json, "year_to_date");
        this.balance = json.getDouble("balance");
//        this.loyaltyBalance = json.getDouble("loyalty_balance");
        this.sex = LightspeedDataParser.getJSONString(json, "gender");
        this.birthday = LightspeedDataParser.getJSONString(json, "birthday");
        this.phone = LightspeedDataParser.getJSONString(json, "phone");
        this.mobile = LightspeedDataParser.getJSONString(json, "mobile");

    }

    public String getSourceId() {
        return sourceId;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getMobile() {
        return mobile;
    }

    public double getLoyaltyBalance() {
        return loyaltyBalance;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setYtd(String ytd) {
        this.ytd = ytd;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setCustom1(String custom1) {
        this.custom1 = custom1;
    }

    public void setCustom2(String custom2) {
        this.custom2 = custom2;
    }

    public void setCustom3(String custom3) {
        this.custom3 = custom3;
    }

    public void setCustom4(String custom4) {
        this.custom4 = custom4;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setLoyaltyBalance(double loyaltyBalance) {
        this.loyaltyBalance = loyaltyBalance;
    }

    @Override
    protected void addAdditionalJSONData(JSONObject json) {

        LightspeedDataParser.setJSONString(json, "first_name", this.getFirstName());
        LightspeedDataParser.setJSONString(json, "customer_code", this.getCode());
        LightspeedDataParser.setJSONString(json, "last_name", this.getLastName());
        LightspeedDataParser.setJSONString(json, "source_unique_id", this.getSourceId());
        LightspeedDataParser.setJSONString(json, "email", this.getEmail());
        LightspeedDataParser.setJSONString(json, "gender", this.getSex());
        LightspeedDataParser.setJSONString(json, "phone", this.getPhone());
        LightspeedDataParser.setJSONString(json, "mobile", this.getMobile());
        LightspeedDataParser.setJSONString(json, "email", this.getEmail());

    }

}

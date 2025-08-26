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
public class Customer extends LightspeedData {

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
    private String physicalAddress1;
    private String physicalAddress2;
    private String physicalSuburb;
    private String physicalCity;
    private String physicalState;
    private String physicalCountry;
    private String physicalPostCode;
    private String postalAddress1;
    private String postalAddress2;
    private String postalSuburb;
    private String postalCity;
    private String postalState;
    private String postalCountry;
    private String postalPostCode;

    private static boolean SYNC_ADDRESSES;

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
        this.ytd = LightspeedDataParser.getJSONString(json, "year_to_date");
        this.balance = json.getDouble("balance");
        this.loyaltyBalance = json.getDouble("loyalty_balance");
        this.sex = LightspeedDataParser.getJSONString(json, "gender");
        this.birthday = LightspeedDataParser.getJSONString(json, "birthday");
        this.phone = LightspeedDataParser.getJSONString(json, "phone");
        this.mobile = LightspeedDataParser.getJSONString(json, "mobile");
        this.physicalAddress1 = LightspeedDataParser.getJSONString(json, "physical_address_1");
        this.physicalAddress2 = LightspeedDataParser.getJSONString(json, "physical_address_2");
        this.physicalSuburb = LightspeedDataParser.getJSONString(json, "physical_suburb");
        this.physicalCity = LightspeedDataParser.getJSONString(json, "physical_city");
        this.physicalState = LightspeedDataParser.getJSONString(json, "physical_state");
        this.physicalCountry = LightspeedDataParser.getJSONString(json, "physical_country_id");
        this.physicalPostCode = LightspeedDataParser.getJSONString(json, "physical_postcode");

        this.postalAddress1 = LightspeedDataParser.getJSONString(json, "postal_address_1");
        this.postalAddress2 = LightspeedDataParser.getJSONString(json, "postal_address_2");
        this.postalSuburb = LightspeedDataParser.getJSONString(json, "postal_suburb");
        this.postalCity = LightspeedDataParser.getJSONString(json, "postal_city");
        this.postalState = LightspeedDataParser.getJSONString(json, "postal_state");
        this.postalCountry = LightspeedDataParser.getJSONString(json, "postal_country_id");
        if (SYNC_ADDRESSES) {
            this.syncToPhysicalAddress();
        }

    }

    public void syncToPhysicalAddress() {
        this.postalAddress1 = this.physicalAddress1;
        this.postalAddress2 = this.physicalAddress2;
        this.postalSuburb = this.physicalSuburb;
        this.postalCity = this.physicalCity;
        this.postalState = this.physicalState;
        this.postalCountry = this.physicalState;
    }

    @Override
    protected void addAdditionalJSONData(JSONObject json) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

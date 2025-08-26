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

    private static boolean SYNC_ADDRESSES = true;

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
        this.postalPostCode = LightspeedDataParser.getJSONString(json, "postal_postcode");
        if (SYNC_ADDRESSES) {
            //    this.syncToPhysicalAddress();

            this.syncToPostalAddress();
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

    public void syncToPostalAddress() {
        this.physicalAddress1 = this.postalAddress1;
        this.physicalAddress2 = this.postalAddress2;
        this.physicalSuburb = this.postalSuburb;
        this.physicalCity = this.postalCity;
        this.physicalState = this.postalState;
        this.physicalCountry = this.postalCountry;
        this.physicalPostCode = this.postalPostCode;
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

    public String getPhysicalAddress1() {
        return physicalAddress1;
    }

    public String getPhysicalAddress2() {
        return physicalAddress2;
    }

    public String getPhysicalSuburb() {
        return physicalSuburb;
    }

    public String getPhysicalCity() {
        return physicalCity;
    }

    public String getPhysicalState() {
        return physicalState;
    }

    public String getPhysicalCountry() {
        return physicalCountry;
    }

    public String getPhysicalPostCode() {
        return physicalPostCode;
    }

    public String getPostalAddress1() {
        return postalAddress1;
    }

    public String getPostalAddress2() {
        return postalAddress2;
    }

    public String getPostalSuburb() {
        return postalSuburb;
    }

    public String getPostalCity() {
        return postalCity;
    }

    public String getPostalState() {
        return postalState;
    }

    public String getPostalCountry() {
        return postalCountry;
    }

    public String getPostalPostCode() {
        return postalPostCode;
    }

    public static boolean isSYNC_ADDRESSES() {
        return SYNC_ADDRESSES;
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

    public void setPhysicalAddress1(String physicalAddress1) {
        this.physicalAddress1 = physicalAddress1;
    }

    public void setPhysicalAddress2(String physicalAddress2) {
        this.physicalAddress2 = physicalAddress2;
    }

    public void setPhysicalSuburb(String physicalSuburb) {
        this.physicalSuburb = physicalSuburb;
    }

    public void setPhysicalCity(String physicalCity) {
        this.physicalCity = physicalCity;
    }

    public void setPhysicalState(String physicalState) {
        this.physicalState = physicalState;
    }

    public void setPhysicalCountry(String physicalCountry) {
        this.physicalCountry = physicalCountry;
    }

    public void setPhysicalPostCode(String physicalPostCode) {
        this.physicalPostCode = physicalPostCode;
    }

    public void setPostalAddress1(String postalAddress1) {
        this.postalAddress1 = postalAddress1;
    }

    public void setPostalAddress2(String postalAddress2) {
        this.postalAddress2 = postalAddress2;
    }

    public void setPostalSuburb(String postalSuburb) {
        this.postalSuburb = postalSuburb;
    }

    public void setPostalCity(String postalCity) {
        this.postalCity = postalCity;
    }

    public void setPostalState(String postalState) {
        this.postalState = postalState;
    }

    public void setPostalCountry(String postalCountry) {
        this.postalCountry = postalCountry;
    }

    public void setPostalPostCode(String postalPostCode) {
        this.postalPostCode = postalPostCode;
    }

    public static void setSYNC_ADDRESSES(boolean SYNC_ADDRESSES) {
        Customer.SYNC_ADDRESSES = SYNC_ADDRESSES;
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
        LightspeedDataParser.setJSONString(json, "physical_address_1", this.getPhysicalAddress1());
        LightspeedDataParser.setJSONString(json, "physical_address_2", this.getPhysicalAddress2());
        LightspeedDataParser.setJSONString(json, "physical_suburb", this.getPhysicalSuburb());
        LightspeedDataParser.setJSONString(json, "physcial_state", this.getPhysicalState());
        LightspeedDataParser.setJSONString(json, "physical_country_id", this.getPhysicalCountry());
        LightspeedDataParser.setJSONString(json, "physical_postcode", this.getPhysicalPostCode());

        LightspeedDataParser.setJSONString(json, "postal_address_1", this.getPostalAddress1());
        LightspeedDataParser.setJSONString(json, "ppstal_address_2", this.getPostalAddress2());
        LightspeedDataParser.setJSONString(json, "postal_suburb", this.getPostalSuburb());
        LightspeedDataParser.setJSONString(json, "postal_state", this.getPostalState());
        LightspeedDataParser.setJSONString(json, "postal_country_id", this.getPostalCountry());
        LightspeedDataParser.setJSONString(json, "postal_postcode", this.getPostalPostCode());

    }

}

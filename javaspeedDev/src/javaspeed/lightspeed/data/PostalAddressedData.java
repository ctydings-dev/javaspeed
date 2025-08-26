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
public abstract class PostalAddressedData extends AddressedData {

    private String postalAddress1;
    private String postalAddress2;
    private String postalSuburb;
    private String postalCity;
    private String postalState;
    private String postalCountry;
    private String postalPostCode;

    private static boolean SYNC_ADDRESSES = true;

    public void syncToPhysicalAddress() {
        this.postalAddress1 = this.getPhysicalAddress1();
        this.postalAddress2 = this.getPhysicalAddress2();
        this.postalSuburb = this.getPhysicalSuburb();
        this.postalCity = this.getPhysicalCity();
        this.postalState = this.getPhysicalState();
        this.postalCountry = this.getPhysicalState();
    }

    public void syncToPostalAddress() {
        this.setPhysicalAddress1(this.postalAddress1);
        this.setPhysicalAddress2(this.postalAddress2);
        this.setPhysicalSuburb(this.postalSuburb);
        this.setPhysicalCity(this.postalCity);
        this.setPhysicalState(this.postalState);
        this.setPhysicalCountry(this.postalCountry);
        this.setPhysicalPostCode(this.postalPostCode);
    }

    @Override
    public void loadJSONData(JSONObject json) {
        super.loadJSONData(json);
        this.postalAddress1 = LightspeedDataParser.getJSONString(json, "postal_address_1");
        this.postalAddress2 = LightspeedDataParser.getJSONString(json, "postal_address_2");
        this.postalSuburb = LightspeedDataParser.getJSONString(json, "postal_suburb");
        this.postalCity = LightspeedDataParser.getJSONString(json, "postal_city");
        this.postalState = LightspeedDataParser.getJSONString(json, "postal_state");
        this.postalCountry = LightspeedDataParser.getJSONString(json, "postal_country_id");
        this.postalPostCode = LightspeedDataParser.getJSONString(json, "postal_postcode");
        if (SYNC_ADDRESSES) {
            this.syncToPostalAddress();
        }

    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();

        LightspeedDataParser.setJSONString(json, "postal_address_1", this.getPostalAddress1());
        LightspeedDataParser.setJSONString(json, "ppstal_address_2", this.getPostalAddress2());
        LightspeedDataParser.setJSONString(json, "postal_suburb", this.getPostalSuburb());
        LightspeedDataParser.setJSONString(json, "postal_state", this.getPostalState());
        LightspeedDataParser.setJSONString(json, "postal_country_id", this.getPostalCountry());
        LightspeedDataParser.setJSONString(json, "postal_postcode", this.getPostalPostCode());
        return json;
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

}

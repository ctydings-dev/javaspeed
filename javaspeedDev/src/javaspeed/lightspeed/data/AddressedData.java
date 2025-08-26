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
public abstract class AddressedData extends LightspeedData {

    private String physicalAddress1;
    private String physicalAddress2;
    private String physicalSuburb;
    private String physicalCity;
    private String physicalState;
    private String physicalCountry;
    private String physicalPostCode;

    private static boolean SYNC_ADDRESSES = true;

    public static boolean isSYNC_ADDRESSES() {
        return SYNC_ADDRESSES;
    }

    @Override
    public void loadJSONData(JSONObject json) {
        super.loadJSONData(json);
        this.physicalAddress1 = LightspeedDataParser.getJSONString(json, "physical_address_1");
        this.physicalAddress2 = LightspeedDataParser.getJSONString(json, "physical_address_2");
        this.physicalSuburb = LightspeedDataParser.getJSONString(json, "physical_suburb");
        this.physicalCity = LightspeedDataParser.getJSONString(json, "physical_city");
        this.physicalState = LightspeedDataParser.getJSONString(json, "physical_state");
        this.physicalCountry = LightspeedDataParser.getJSONString(json, "physical_country_id");
        this.physicalPostCode = LightspeedDataParser.getJSONString(json, "physical_postcode");

    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        LightspeedDataParser.setJSONString(json, "physical_address_1", this.getPhysicalAddress1());
        LightspeedDataParser.setJSONString(json, "physical_address_2", this.getPhysicalAddress2());
        LightspeedDataParser.setJSONString(json, "physical_suburb", this.getPhysicalSuburb());
        LightspeedDataParser.setJSONString(json, "physcial_state", this.getPhysicalState());
        LightspeedDataParser.setJSONString(json, "physical_country_id", this.getPhysicalCountry());
        LightspeedDataParser.setJSONString(json, "physical_postcode", this.getPhysicalPostCode());

        return json;
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
}

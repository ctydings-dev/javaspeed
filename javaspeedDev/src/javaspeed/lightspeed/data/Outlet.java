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
public class Outlet extends AddressedData {

    private String name;
    private String defaultTaxId;

    @Override
    public String objectName() {
        return "OUTLET";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultTaxId() {
        return defaultTaxId;
    }

    public void setDefaultTaxId(String defaultTaxId) {
        this.defaultTaxId = defaultTaxId;
    }

    @Override
    protected void loadAdditionalJSONData(JSONObject json) {
        this.name = LightspeedDataParser.getJSONString(json, "name");
        this.defaultTaxId = LightspeedDataParser.getJSONString(json, "default_tax_id");
    }

    @Override
    protected void addAdditionalJSONData(JSONObject json) {
        LightspeedDataParser.setJSONString(json, "name", this.getName());
        LightspeedDataParser.setJSONString(json, "default_tax_price", this.getDefaultTaxId());
    }

}

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
public class Category extends LightspeedData {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String objectName() {
        return "CATEGORY";
    }

    @Override
    protected void loadAdditionalJSONData(JSONObject json) {
        this.name = LightspeedDataParser.getJSONString(json, "name");
    }

    @Override
    protected void addAdditionalJSONData(JSONObject json) {
        LightspeedDataParser.setJSONString(json, "name", this.getName());
    }

}

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
public class Brand extends DescriptiveData {

    public Brand() {

    }

    public Brand(String key, String name) {
        this.setId(key);
        this.setName(name);
    }

    @Override
    public String objectName() {
        return "BRAND";
    }

    @Override
    protected void loadAdditionalJSONData(JSONObject json) {

    }

    @Override
    protected void addAdditionalJSONData(JSONObject json) {
    }

}

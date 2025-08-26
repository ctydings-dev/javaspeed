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
public class Category extends NamedData {

    public Category() {

    }

    public Category(String key, String name) {
        super(key, name);
    }

    @Override
    public String objectName() {
        return "CATEGORY";
    }

    @Override
    protected void loadAdditionalJSONData(JSONObject json) {

    }

    @Override
    protected void addAdditionalJSONData(JSONObject json) {

    }
}

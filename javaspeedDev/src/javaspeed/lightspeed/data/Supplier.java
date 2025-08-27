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
public class Supplier extends SourcedData {

    private String code;
    
    public Supplier() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
 
    public Supplier(String key, String name) {
        this.setId(key);
        this.setName(name);
    }

    @Override
    public String objectName() {
        return "SUPPLIER";
    }

    @Override
    protected void loadAdditionalJSONData(JSONObject json) {

    }

    @Override
    protected void addAdditionalJSONData(JSONObject json) {
    }

}

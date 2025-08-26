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
public class ProductCode extends LightspeedData {

    private String type;

    private String code;

    @Override
    public String objectName() {
        return "PRODUCT_CODE";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    protected void loadAdditionalJSONData(JSONObject json) {
        this.type = LightspeedDataParser.getJSONString(json, "type");
        this.code = LightspeedDataParser.getJSONString(json, "code");
    }

    @Override
    protected void addAdditionalJSONData(JSONObject json) {
        LightspeedDataParser.setJSONString(json, "type", this.getType());
        LightspeedDataParser.setJSONString(json, "code", this.getCode());
    }

}

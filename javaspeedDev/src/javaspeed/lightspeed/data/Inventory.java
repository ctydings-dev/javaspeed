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
public class Inventory extends LightspeedData {

    private String productId;
    private String outletId;
    private int inventory;

    @Override
    public String objectName() {
        return "INVENTORY";
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOutletId() {
        return outletId;
    }

    public void setOutletId(String outletId) {
        this.outletId = outletId;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    protected void loadAdditionalJSONData(JSONObject json) {
        this.outletId = LightspeedDataParser.getJSONString(json, "outlet_id");
        this.productId = LightspeedDataParser.getJSONString(json, "product_id");
        this.inventory = json.getInt("current_amount");
    }

    @Override
    protected void addAdditionalJSONData(JSONObject json) {
        LightspeedDataParser.setJSONString(json, "outlet_id", this.getOutletId());
        //   LightspeedDataParser.setJSONString(json, "product_id", this.getProductId());
        // json.put("inventory_level", this.getInventory());
        json.put("current_amount", this.getInventory());
        json.remove("id");
    }

}

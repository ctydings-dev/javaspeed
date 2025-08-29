/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaspeed.lightspeed.data;

import org.json.JSONObject;

/**
 *
 * @author rfancher
 */
public class SerialNumber extends LightspeedData {

    public String number;
    public String owner;
    public Product product;
    public Outlet outlet;

    public SerialNumber(String number, String owner) {
        this.number = number;
        this.owner = owner;
    }

    public SerialNumber(String number) {
        this.number = number;

    }

    public String getNumber() {
        return number;
    }

    public String getOwner() {
        return owner;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Outlet getOutlet() {
        return outlet;
    }

    public void setOutlet(Outlet outlet) {
        this.outlet = outlet;
    }

    @Override
    public String objectName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void loadAdditionalJSONData(JSONObject json) {

    }

    @Override
    protected void addAdditionalJSONData(JSONObject json) {
        LightspeedDataParser.setJSONString(json, "product_id", this.getProduct().getId());
        LightspeedDataParser.setJSONString(json, "outlet_id", this.getOutlet().getId());
        LightspeedDataParser.setJSONString(json, "code", this.getNumber());
    }

}

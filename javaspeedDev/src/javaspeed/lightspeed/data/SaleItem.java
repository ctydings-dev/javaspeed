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
public class SaleItem extends LightspeedData {

    private Product product;

    private String regsterId;

    private int quantity;

    private double price;
    private double cost;

    public String getRegsterId() {
        return regsterId;
    }

    public void setRegsterId(String regsterId) {
        this.regsterId = regsterId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String objectName() {
        return "SALE_ITEM";
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    protected void loadAdditionalJSONData(JSONObject json) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void addAdditionalJSONData(JSONObject json) {
        json.put("product_id", this.getProduct().getId());
        json.put("price", this.getPrice());
        json.put("cost", this.getCost());
        json.put("quantity", this.getQuantity());
        json.put("tax", 0);
        json.put("tax_id", CommonKeys.NO_TAX_ID);

    }

}

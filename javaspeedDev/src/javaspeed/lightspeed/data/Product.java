/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaspeed.lightspeed.data;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author ctydi
 */
public class Product extends SourcedData {

    private Supplier supplier;
    private Brand brand;
    private Category category;

    private String sku;
    private String upc;
    private boolean active;
    private Double priceIncludingTax;
    private Double priceExcludingTax;
    private Double supplyPrice;
    private Inventory[] inventory;

    public Product() {
        this.inventory = new Inventory[0];
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void addInventory(Inventory toAdd) {
        Inventory[] toSet = new Inventory[this.inventory.length + 1];
        for (int index = 0; index < toSet.length - 1; index++) {
            toSet[index] = this.inventory[index];
        }
        toSet[toSet.length - 1] = toAdd;
        this.inventory = toSet;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Double getPriceIncludingTax() {
        return priceIncludingTax;
    }

    public void setPriceIncludingTax(Double priceIncludingTax) {
        this.priceIncludingTax = priceIncludingTax;
    }

    public Double getPriceExcludingTax() {
        return priceExcludingTax;
    }

    public void setPriceExcludingTax(Double priceExcludingTax) {
        this.priceExcludingTax = priceExcludingTax;
    }

    public Double getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(Double supplyPrice) {
        this.supplyPrice = supplyPrice;
    }

    public Inventory[] getInventory() {
        return inventory;
    }

    public void setInventory(Inventory[] inventory) {
        this.inventory = inventory;
    }

    @Override
    public String objectName() {
        return "PRODUCT";
    }

    @Override
    protected void loadAdditionalJSONData(JSONObject json) {
        this.setCategory(new Category());
        this.setBrand(new Brand());
        this.setSupplier(new Supplier());
        if (!json.isNull("product_category")) {
            this.getCategory().loadJSONData(json.getJSONObject("product_category"));
        }
        if (!json.isNull("supplier")) {
            this.getSupplier().loadJSONData(json.getJSONObject("supplier"));
        }
        if (!json.isNull("brand")) {
            this.getBrand().loadJSONData(json.getJSONObject("brand"));
        }
        this.sku = LightspeedDataParser.getJSONString(json, "sku");
    }

    @Override
    protected void addAdditionalJSONData(JSONObject json) {
        json.put("category", this.getCategory().toJSON());
        json.put("supplier", this.getSupplier().toJSON());
        json.put("brand", this.getBrand());
        LightspeedDataParser.setJSONString(json, "sku", this.getSku());
        JSONArray inven = new JSONArray(this.inventory.length);

        for (int index = 0; index < this.inventory.length; index++) {
            inven.put(index, this.inventory[index].toJSON());
        }
    }

}

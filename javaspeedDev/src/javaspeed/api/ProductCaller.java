/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaspeed.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javaspeed.lightspeed.data.Brand;
import javaspeed.lightspeed.data.Category;
import javaspeed.lightspeed.data.Customer;
import javaspeed.lightspeed.data.Inventory;
import javaspeed.lightspeed.data.Product;
import javaspeed.lightspeed.data.Supplier;
import javaspeed.lightspeed.data.Tag;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author ctydi
 */
public class ProductCaller extends LightspeedCaller {

    public ProductCaller(String url, String token) {
        super(url, token);
    }

    public List<Product> getProducts() throws IOException {

        String data = this.sendGetRequest(this.create20API("products"), 99999);
        JSONObject parsed = new JSONObject(data);
        JSONArray customers = parsed.getJSONArray("data");
        List<Product> ret = new ArrayList<>();

        for (int index = 0; index < customers.length(); index++) {
            Product toAdd = new Product();
            toAdd.loadJSONData(customers.getJSONObject(index));
            ret.add(toAdd);
        }
        return ret;
    }

    public String addProduct(Product toAdd) throws IOException {
        JSONObject value = toAdd.getCreateJSON();
        String response = this.sendPostRequest(this.create20API("products"), value.toString());
        JSONObject key = new JSONObject(response);
        String keyValue = key.getJSONArray("data").getString(0);
        return keyValue;
    }

    public String addProductCategory(Category toAdd) throws IOException {
        JSONObject value = toAdd.getCreateJSON();
        String response = this.sendPostRequest(this.create20API("product_categories"), value.toString());
        JSONObject key = new JSONObject(response);
        String keyValue = key.getJSONArray("data").getString(0);
        return keyValue;

    }

    public List<Category> getProductCategories() throws IOException {

        String data = this.sendGetRequest(this.create20API("product_categories"), 99999);
        JSONObject parsed = new JSONObject(data);
        parsed = parsed.getJSONObject("data");
        parsed = parsed.getJSONObject("data");
        JSONArray customers = parsed.getJSONArray("categories");
        List<Category> ret = new ArrayList<>();

        for (int index = 0; index < customers.length(); index++) {
            Category toAdd = new Category();
            toAdd.loadJSONData(customers.getJSONObject(index));
            ret.add(toAdd);
        }
        return ret;
    }

    public List<Tag> getProductTags() throws IOException {

        String data = this.sendGetRequest(this.create20API("tags"), 99999);
        JSONObject parsed = new JSONObject(data);

        JSONArray values = parsed.getJSONArray("data");
        List<Tag> ret = new ArrayList<>();

        for (int index = 0; index < values.length(); index++) {
            Tag toAdd = new Tag();
            toAdd.loadJSONData(values.getJSONObject(index));
            ret.add(toAdd);
        }
        return ret;
    }

    public List<Supplier> getSuppliers() throws IOException {

        String data = this.sendGetRequest(this.create20API("suppliers"), 99999);
        JSONObject parsed = new JSONObject(data);

        JSONArray values = parsed.getJSONArray("data");
        List<Supplier> ret = new ArrayList<>();

        for (int index = 0; index < values.length(); index++) {
            Supplier toAdd = new Supplier();
            toAdd.loadJSONData(values.getJSONObject(index));
            ret.add(toAdd);
        }
        return ret;
    }

    public List<Brand> getBrands() throws IOException {

        String data = this.sendGetRequest(this.create20API("brands"), 99999);
        JSONObject parsed = new JSONObject(data);

        JSONArray values = parsed.getJSONArray("data");
        List<Brand> ret = new ArrayList<>();

        for (int index = 0; index < values.length(); index++) {
            Brand toAdd = new Brand();
            toAdd.loadJSONData(values.getJSONObject(index));
            ret.add(toAdd);
        }
        return ret;
    }

    public List<Inventory> getInventories() throws IOException {

        String data = this.sendGetRequest(this.create20API("inventory"), 99999);
        JSONObject parsed = new JSONObject(data);

        JSONArray values = parsed.getJSONArray("data");
        List<Inventory> ret = new ArrayList<>();

        for (int index = 0; index < values.length(); index++) {
            Inventory toAdd = new Inventory();
            toAdd.loadJSONData(values.getJSONObject(index));
            ret.add(toAdd);
        }
        return ret;
    }

    public String addProductTag(Tag toAdd) throws IOException {
        JSONObject value = toAdd.getCreateJSON();
        String response = this.sendPostRequest(this.create20API("tags"), value.toString());
        JSONObject key = new JSONObject(response);
        String keyValue = key.getString("data");
        return keyValue;
    }

    public String addSupplier(Supplier toAdd) throws IOException {
        JSONObject value = toAdd.getCreateJSON();
        String response = this.sendPostRequest(this.create20API("suppliers"), value.toString());
        JSONObject key = new JSONObject(response);
        String keyValue = key.getString("data");
        return keyValue;
    }

    public String addBrand(Brand toAdd) throws IOException {
        JSONObject value = toAdd.getCreateJSON();
        String response = this.sendPostRequest(this.create20API("brands"), value.toString());
        JSONObject key = new JSONObject(response);
        String keyValue = key.getString("data");
        return keyValue;
    }

}

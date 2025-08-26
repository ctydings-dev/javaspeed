/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaspeed.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javaspeed.lightspeed.data.Customer;
import javaspeed.lightspeed.data.Product;
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
}

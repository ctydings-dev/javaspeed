/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaspeed.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javaspeed.lightspeed.data.Customer;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author ctydi
 */
public class CustomerCaller extends LightspeedCaller {

    public CustomerCaller(String url, String token) {
        super(url, token);
    }

    public List<Customer> getCustomers() throws IOException {

        String data = this.sendGetRequest(this.create20API("customers"), 99999);
        JSONObject parsed = new JSONObject(data);
        JSONArray customers = parsed.getJSONArray("data");
        List<Customer> ret = new ArrayList<>();

        for (int index = 0; index < customers.length(); index++) {
            Customer toAdd = new Customer();
            toAdd.loadJSONData(customers.getJSONObject(index));
            ret.add(toAdd);
        }
        return ret;
    }

    public void addCustomer(Customer toAdd) throws IOException {
        JSONObject value = toAdd.getCreateJSON();
        this.sendPostRequest(this.create20API("customers"), value.toString());

    }
}

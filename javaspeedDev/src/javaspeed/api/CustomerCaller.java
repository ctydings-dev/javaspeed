/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaspeed.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javaspeed.lightspeed.data.Customer;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author ctydi
 */
public class CustomerCaller extends LightspeedCaller {

    
    private Map<String,Customer> loadedCustomers;
    
    //private Map<String,User> loadedUsers;
    
    
    public CustomerCaller(String url, String token) {
        super(url, token);
        this.loadedCustomers = new HashMap();
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
        this.loadedCustomers.put(toAdd.getId(), toAdd);
        }
        return ret;
    }

    public String addCustomer(Customer toAdd) throws IOException {
        JSONObject value = toAdd.getCreateJSON();
        String response = this.sendPostRequest(this.create20API("customers"), value.toString());
        JSONObject key = new JSONObject(response);
        String keyValue = key.getJSONArray("data").getString(0);
        toAdd.setId(keyValue);
        this.loadedCustomers.put(keyValue, toAdd);
        return keyValue;
    }
    
    public Customer getCustomerByEveId(String eveId){
        
        for(String key : this.loadedCustomers.keySet()){
            Customer toCheck = this.loadedCustomers.get(key);
            if(toCheck.getCustom1() . equals(eveId)){
                return toCheck;
            }
        }
        
        
        
        return null;
    }
    
}

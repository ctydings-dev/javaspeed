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
import javaspeed.lightspeed.data.Outlet;
import javaspeed.lightspeed.data.Product;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author ctydi
 */
public class OutletCaller extends LightspeedCaller {

    
    private Map<String,Outlet> loadedOutlets;
    
    public OutletCaller(String url, String token) {
        super(url, token);
        this.loadedOutlets = new HashMap();
    }

    public List<Outlet> getOutlets() throws IOException {

        String raw = this.sendGetRequest(this.create20API("outlets"), 99999);
        JSONObject parsed = new JSONObject(raw);
        JSONArray data = parsed.getJSONArray("data");
        List<Outlet> ret = new ArrayList<>();

        for (int index = 0; index < data.length(); index++) {
            Outlet toAdd = new Outlet();
            toAdd.loadJSONData(data.getJSONObject(index));
            ret.add(toAdd);
            this.loadedOutlets.put(toAdd.getId(), toAdd);
        }
        return ret;
    }
    
    public Outlet getOutletByName(String name){
        
        for(String key : this.loadedOutlets.keySet()){
            Outlet toCheck = this.loadedOutlets.get(key);
        if(toCheck.getName().equals(name)){
            return toCheck;
        }
        
        }
        
        
        return null;
        
    }
    
    
}

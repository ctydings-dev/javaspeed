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
public abstract class LightspeedData {

    private String id;

    public LightspeedData(String key) {
        this.id = key;
    }

    public LightspeedData() {
        this.id = "";
    }

    public String getId() {
        return id;
    }

    protected void setId(String key) {
        this.id = key;
    }

    public abstract String objectName();

    public void loadJSONData(JSONObject data) {

        this.id = data.getString("id");
        loadAdditionalJSONData(data);
    }

    public JSONObject toJSON() {
        JSONObject ret = new JSONObject();
        ret.put("id", this.getId());
        this.addAdditionalJSONData(ret);
        return ret;
    }

    public JSONObject getCreateJSON() {
        JSONObject ret = this.toJSON();
        ret.remove("id");
        return ret;
    }

    protected abstract void loadAdditionalJSONData(JSONObject json);

    protected abstract void addAdditionalJSONData(JSONObject json);

}

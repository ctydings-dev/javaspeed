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
public class User extends LightspeedData {

    private String username;
    private String diaplay_name;
    private String email;

    @Override
    public String objectName() {
        return "USER";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDiaplay_name() {
        return diaplay_name;
    }

    public void setDiaplay_name(String diaplay_name) {
        this.diaplay_name = diaplay_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    protected void loadAdditionalJSONData(JSONObject json) {
        this.username = json.getString("username");
        this.diaplay_name = json.getString("display_name");
        if (!json.isNull("email")) {
            this.email = json.getString("email");
        }

    }

    @Override
    protected void addAdditionalJSONData(JSONObject json) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

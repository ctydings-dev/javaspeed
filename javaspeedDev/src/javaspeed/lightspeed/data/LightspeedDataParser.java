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
public class LightspeedDataParser {

    private static boolean autoTrim;

    private static boolean autoCap;

    private static boolean ignoreNull;

    public static String formatString(String value) {
        if (value == null) {
            return null;
        }
        if (autoTrim == true) {
            value = value.trim();
        }
        if (autoCap == true) {
            value = value.toUpperCase();
        }

        return value;
    }

    public static void setJSONString(JSONObject toSet, String field, String value) {
        if (value == null && ignoreNull == true) {
            return;
        }
        value = formatString(value);
        toSet.put(field, value);
    }

    public static String getJSONString(JSONObject value, String field) {
        if (value.isNull(field)) {
            return "";
        }
        String ret = value.getString(field);
        return formatString(ret);
    }

}

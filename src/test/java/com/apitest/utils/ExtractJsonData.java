package com.apitest.utils;

import org.json.JSONObject;

public class ExtractJsonData {
    public static String getNestedValue(String jsonString, String... keys) {
        JSONObject jsonObject = new JSONObject(jsonString);
        for (int i = 0; i < keys.length - 1; i++) {
            jsonObject = jsonObject.getJSONObject(keys[i]);
        }
        return jsonObject.getString(keys[keys.length - 1]);
    }

}

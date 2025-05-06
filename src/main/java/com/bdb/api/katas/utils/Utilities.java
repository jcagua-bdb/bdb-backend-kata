package com.bdb.api.katas.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Utilities {

    private static final List<String> KEYS_TO_OFUSCATE = Arrays.asList("apiKey", "channel");

    public static Map<String, Object> obfuscate(Object obj) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        Map<String, Object> obfuscatedData = gson.fromJson(json, new TypeToken<Map<String, Object>>() {}.getType());

        for (String key : KEYS_TO_OFUSCATE) {
            if (obfuscatedData.containsKey(key)) {
                obfuscatedData.put(key, obfuscateValue(obfuscatedData.get(key)));
            }
        }

        return obfuscatedData;
    }

    private static String obfuscateValue(Object value) {
        if (value == null) {
            return null;
        }

        String stringValue = value.toString();
        if (stringValue.length() <= 7) {
            return "**********";
        }
        return stringValue.substring(0, stringValue.length() - 10) + "****";
    }
}

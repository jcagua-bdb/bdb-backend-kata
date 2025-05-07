package com.bdb.api.katas.utils;

import com.bdb.api.katas.dto.app.ConfluenceRatingDTO;
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

    public static String buildHtmlTableRegisterConfluence(List<ConfluenceRatingDTO> participantDTOListList) {
        StringBuilder html = new StringBuilder();
        html.append("<table><thead><tr>")
                .append("<th>Nombre</th><th>Promedio</th><th>Aprobado</th><th>Posición</th>")
                .append("</tr></thead><tbody>");

        for ( ConfluenceRatingDTO p : participantDTOListList) {
            html.append("<tr>")
                    .append("<td>").append(p.getName()).append("</td>")
                    .append("<td>").append(p.getAverage()).append("</td>")
                    .append("<td>").append(p.isApproved() ? "✅" : "❌").append("</td>")
                    .append("<td>").append(p.getPosition()).append("</td>")
                    .append("</tr>");
        }
        html.append("</tbody></table>");
        return html.toString();
    }
}

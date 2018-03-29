/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rabbitmq.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 *
 * @author Vantu
 */
public class JsonUtil {
    
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    
    private JsonUtil() {};
    
    public static String toJson(Object object) {
        String resutl = "";
        
        try {
            resutl = OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        return resutl;
    }
    
    public static <T> T toObject(String json, Class<T> type) {
        T result = null;
        
        if (json != null && !json.trim().equals("")) {
            try {
                result = OBJECT_MAPPER.readValue(json, type);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }
    
    public static String getStringParam(String json, String paramKey) {
        String result = "";
        
        if (Validator.validateStrings(json, paramKey)) {
            try {
                JsonNode jsonNode = OBJECT_MAPPER.readValue(json, JsonNode.class);
                result = jsonNode.get(paramKey).asText();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }
    
    public static Long getLongParam(String json, String paramKey) {
        Long result = -1L;
        
        if (Validator.validateStrings(json, paramKey)) {
            try {
                JsonNode jsonNode = OBJECT_MAPPER.readValue(json, JsonNode.class);
                result = jsonNode.get(paramKey).asLong();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }
}

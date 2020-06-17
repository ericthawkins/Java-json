package com.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class Converter 
{
    public static void main( String[] args )
    {
    	Converter converter = new Converter();
    	String jsonInput = "{\"UpdateItems\":[{\"AclName\":\"ACL Name 1\",\"Pids\":[\"pid 1\",\"pid 2\",\"pid 3\"]},{\"AclName\":\"ACL Name 2\",\"Pids\":[\"pid 4\",\"pid 5\",\"pid 6\"]},{\"AclName\":\"ACL Name 3\",\"Pids\":[\"pid 7\",\"pid 8\",\"pid 9\"]}]}";
		System.out.println("Original JSON:  " + jsonInput);
    	String convertedJson = converter.toCamelCase(jsonInput);
		System.out.println("Converted JSON: " + convertedJson);
    }
    
    public String toCamelCase (String jsonInput) {
    	String convertedJson = null;
    	try {
			Map<String, Object> jsonMap = jsonToMap(jsonInput);
			convertedJson = new ObjectMapper().writeValueAsString(jsonMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return convertedJson;
    }
    
    private Map<String, Object> jsonToMap(String jsonString) throws IOException
    {
        ObjectMapper mapper=new ObjectMapper();
        Map<String, Object> map = mapper.readValue(jsonString,new TypeReference<Map<String, Object>>(){});
        return convertMap(map);
    }
    
    private String mapKey(String key) {
        return Character.toLowerCase(key.charAt(0)) + key.substring(1);
    }

    private Map<String, Object> convertMap(Map<String, Object> map) {
        Map<String, Object> result = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            result.put(mapKey(key), convertValue(value));
        }
        return result;
    }

    private List<Object> convertList(List<Object> list) {
        List<Object> result = new ArrayList<Object>();
        for (Object obj : list) {
            result.add(convertValue(obj));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
	private Object convertValue(Object obj) {
        if (obj instanceof Map<?, ?>) {
            return convertMap((Map<String, Object>) obj);
        } else if (obj instanceof List<?>) {
            return convertList((List<Object>) obj);
        } else {
            return obj;
        }
    }

}

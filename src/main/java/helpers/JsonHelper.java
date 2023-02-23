package helpers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;

public class JsonHelper {

    private static JsonReader getJsonReader(String jsonPath) {
        try {
            JsonReader reader;
            reader = new JsonReader(new FileReader(jsonPath));
            return reader;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static Map<String, String> convertJsonToMap(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> myMap = gson.fromJson(json, type);
        return myMap;
    }

    public static DesiredCapabilities convertJsonToDesiredCapabilities(String json) {
        DesiredCapabilities capability = new DesiredCapabilities();
        Map<String, String> map = convertJsonToMap(json);
        if (null != map) {
            for (String key : map.keySet()) {
                capability.setCapability(key, map.get(key));
            }
        }
        return capability;
    }

    public static JsonObject getJsonObject(String jsonPath) {
        try {
            JsonObject obj;
            Gson gson = new Gson();
            JsonReader reader = getJsonReader(jsonPath);
            obj = gson.fromJson(reader, JsonObject.class);
            return obj;
        } catch (Exception e) {
            throw e;
        }
    }
}

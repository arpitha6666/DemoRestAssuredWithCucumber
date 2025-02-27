package util;

import io.restassured.path.json.JsonPath;

public class JsonUtil {
    public static JsonPath rawToJsonPath(String response) {
        JsonPath js = new JsonPath(response);
        return js;
    }
}

package util;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class Utility {
    RequestSpecification reqSpec;
    ResponseSpecification responseSpec;
    Response response;

    public RequestSpecification getReqSpec() throws IOException {
        PrintStream log = new PrintStream(new FileOutputStream("logging.txt", true));

        reqSpec  = new RequestSpecBuilder().setBaseUri(getGlobalProerties("baseUri"))
                .addQueryParam("key", "qaclick123")
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .setContentType(ContentType.JSON).build();
        return reqSpec;
    }

    public ResponseSpecification getResSpec(){
        responseSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build().response();
        return responseSpec;
    }

    public static String getGlobalProerties(String key) throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream(new File("E:\\MyProjects\\DemoRestAssuredWithCucumber\\src\\test\\resources\\globalData.properties")));
        return prop.getProperty(key);
    }

    public JsonPath rawToJsonPath(String response){
        JsonPath js = new JsonPath(response);
        return js;
    }

    public String getJsonToString(String response,String key){
        JsonPath js = new JsonPath(response);
        return js.get(key).toString();
    }

}

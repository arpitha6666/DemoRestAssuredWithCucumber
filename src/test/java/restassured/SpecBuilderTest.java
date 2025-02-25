package restassured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import model.AddPlace;
import model.Location;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilderTest {

    @Test
    public void addPlaceSpecBuilderTest() {

        AddPlace place = new AddPlace();
        Location loc = new Location();
        List<String> arrTypes = Arrays.asList(new String[]{"Shoe park", "shoe"});
        loc.setLat(-38.383494);
        loc.setLng(37.383494);
        place.setAccuracy(50);
        place.setAddress("3410, side layout, cohen 044");
        place.setName("Dublin");
        place.setPhone_number("(+91) 983 893 3937");
        place.setWebsite("https://rahulshettyacademy.com/");
        place.setLanguage("English");
        place.setLocation(loc);
        place.setTypes(arrTypes);

        RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
                .addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON).build();

        ResponseSpecification responseSpec =new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build().response();
        Response respone = given().spec(reqSpec).body(place).when().post("maps/api/place/add/json").then().spec(responseSpec).extract().response();
        System.out.println(respone);


    }
}

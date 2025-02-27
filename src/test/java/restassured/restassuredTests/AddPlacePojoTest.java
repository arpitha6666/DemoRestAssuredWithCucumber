package restassured.restassuredTests;

import io.restassured.RestAssured;
import model.mapsapi.AddPlace;
import model.mapsapi.Location;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AddPlacePojoTest {
    @Test
    public void mapsAddPlaceTest() {

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

        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        given().log().all().queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body(place)
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200);

    }
}

package restassured.cucumberTests.stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import model.mapsapi.AddPlace;
import model.mapsapi.AddPlaceResponse;
import restassured.cucumberTests.TestData.AddPlaceTestDataBuilder;
import util.APIResources;
import util.JsonUtil;
import util.Utility;

import java.io.IOException;

import static org.testng.Assert.*;

import static io.restassured.RestAssured.given;

public class StepDefinitions extends Utility {
    RequestSpecification reqSpec;
    Response response;
    ResponseSpecification respSpec;
    static String placeId;
    JsonPath js;
    AddPlaceTestDataBuilder dataBuilder = new AddPlaceTestDataBuilder();

    @Given("Add place payload with {string}, {string} and {string}")
    public void add_place_payload(String name, String language, String address) throws IOException {
        reqSpec = given().spec(getReqSpec()).body(dataBuilder.getAddPlaceData(name, language, address));
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_post_http_request(String resource, String httpMethod) {
        //Constructor will be called with the value of resource which you pass as "resource"
        if (httpMethod.equalsIgnoreCase("POST")) {
            response = reqSpec.when().post(APIResources.valueOf(resource).getResource())
                    .then().spec(getResSpec()).extract().response();

            AddPlaceResponse responseObject = reqSpec.when().post(APIResources.valueOf(resource).getResource())
                    .then().spec(getResSpec()).extract().response().as(AddPlaceResponse.class);
            System.out.println(responseObject.toString());
        } else if (httpMethod.equalsIgnoreCase("GET")) {
            response = reqSpec.when().get(APIResources.valueOf(resource).getResource())
                    .then().spec(getResSpec()).extract().response();
        }else if (httpMethod.equalsIgnoreCase("DELETE")) {
            response = reqSpec.when().delete(APIResources.valueOf(resource).getResource())
                    .then().spec(getResSpec()).extract().response();
        }
    }

    @Then("verify the status code {int}")
    public void verify_the_status_code(Integer int1) {
        assertEquals(response.getStatusCode(), int1);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
        JsonPath js = rawToJsonPath(response.asString());
        assertEquals(js.get(key).toString(), value);
    }

    @And("verify placeid created maps to {string} using {string}")
    public void verifyPlaceidCreatedMapsToUsing(String name, String resource) throws IOException {
        placeId = getJsonToString(response.asString(),"place_id");
        System.out.println(placeId);
        reqSpec = given().spec(getReqSpec()).queryParam("place_id",placeId);
        user_calls_with_post_http_request(resource,"GET");
        assertEquals(getJsonToString(response.asString(),"name"), name) ;

    }

    @Given("Deleteplace Payload")
    public void deleteplacePayload() throws IOException {
        reqSpec = given().spec(getReqSpec()).body(dataBuilder.getDeleteData(placeId));
    }
}

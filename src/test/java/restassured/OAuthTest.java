package restassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class OAuthTest {

    @Test
    public void authTest(){
        RestAssured.baseURI = "https://rahulshettyacademy.com/";

        //get the accesstoken
        String postResponse =given().formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParams("grant_type","client_credentials")
                .formParams("scope","trust")
                .when().post("oauthapi/oauth2/resourceOwner/token").asString();
        System.out.println(postResponse);
        JsonPath js = new JsonPath(postResponse);
        String accessToken = js.getString("access_token");

        //use the accesstoken to make the GET call
        String getResponse =given().queryParam("access_token",accessToken)
                .when().get("oauthapi/getCourseDetails").asString();
        System.out.println("getResponse "+getResponse);


    }

}

package restassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import model.libraryapi.Api;
import model.libraryapi.GetCourse;
import model.libraryapi.WebAutomation;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetCoursePojoTest {

    @Test
    public void getCourse(){
        RestAssured.baseURI = "https://rahulshettyacademy.com/";

        String[] expectedCourseTitles={"Selenium Webdriver Java","Cypress","Protractor"};
        List<String> actualCourseTitles = new ArrayList<>();
        //get the accesstoken
        String postResponse =given().formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParams("grant_type","client_credentials")
                .formParams("scope","trust")
                .when().post("oauthapi/oauth2/resourceOwner/token").asString();
        JsonPath js = new JsonPath(postResponse);
        String accessToken = js.getString("access_token");

        GetCourse gc=	given()
                .queryParams("access_token", accessToken)
                .when().log().all()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);

        //get the price of 'SoapUI Webservices testing'
        List<Api> apiCourses = gc.getCourses().getApi();
        List<WebAutomation> webCourses = gc.getCourses().getWebAutomation();

        for(int i=0;i<apiCourses.size();i++){
            if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing") ){
                System.out.println(apiCourses.get(i).getPrice());
            }
        }

        for(int i=0;i<webCourses.size();i++){
            actualCourseTitles.add(webCourses.get(i).getCourseTitle());
        }

        Assert.assertEquals(actualCourseTitles, Arrays.asList(expectedCourseTitles));

    }
}

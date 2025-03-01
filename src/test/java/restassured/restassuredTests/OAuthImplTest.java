package restassured.restassuredTests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import util.Utility;

import static io.restassured.RestAssured.given;

public class OAuthImplTest extends Utility {
    @Test
    public void oauthTest(){

        //System.getProperty("webdriver.firefox.driver","driver.exe path")
        //WebDriver driver = new FirefoxDriver();
        //driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
        //driver.findElementBy(By.cssSelector("input[tye='email]")).sendKeys(email);
        //driver.findElementBy(By.cssSelector("input[tye='password]")).sendKeys(password);
        //String url = driver.getCurrentUrl();
       // String partialCode = url.split("code=")[1].split("&scope")[0];
        String url ="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2FvAHBQUZU6o4WJ719NrGBzSELBFVBI9XbxvOtYpmYpeV47bFVExkaxWaF_XR14PHtTZf7ILSEeamywJKwo_BYs9M&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&session_state=0c32992f0d47e93d273922018ade42d1072b9d1f..a35c&prompt=none#";



        String partialcode=url.split("code=")[1];

        String code=partialcode.split("&scope")[0];


        System.out.println(code);


        String accessTokenResponse = given().log().all()
                .urlEncodingEnabled(false) //do not encode anythign which has special characters
                .queryParams("code",code)
                .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type","authorization_code")
                .when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
        System.out.println(" accessTokenResponse \n"+ accessTokenResponse);
        String accessToken = getJsonToString(accessTokenResponse,"access_token");

        //Actual API to get the courses
        String response = given().
                queryParam("access_token",accessToken)
                .when().log().all().get("https://rahulshettyacademy.com/getCourse.php")
                .asString();


    }
}
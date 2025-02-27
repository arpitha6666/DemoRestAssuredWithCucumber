package restassured.cucumberTests.stepDefs;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void doBefore() throws IOException {
        StepDefinitions stepDef = new StepDefinitions();
        if (StepDefinitions.placeId ==null){
            stepDef.add_place_payload("Ars", "Us-en", "Infantary road");
            stepDef.user_calls_with_post_http_request("AddPlaceAPI","POST");
            stepDef.verifyPlaceidCreatedMapsToUsing("Ars","GetPlaceAPI");
        }
    }
}

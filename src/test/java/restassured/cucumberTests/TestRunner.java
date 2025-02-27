package restassured.cucumberTests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions( features = "src/test/java/restassured/cucumberTests/features",
        glue = "restassured.cucumberTests.stepDefs",tags = "@Regression",
        plugin ={"html:target/cucumber.html", "json:target/cucumber.json"})
public class TestRunner extends AbstractTestNGCucumberTests {

}

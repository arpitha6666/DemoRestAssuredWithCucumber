package restassured.cucumberTests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions( features = "src/test/java/restassured/cucumberTests/features",
        glue = "restassured.cucumberTests.stepDefs",tags = "@DeletePlace",
        plugin ={"html:target/cucumber.html"})
public class TestRunner extends AbstractTestNGCucumberTests {

}

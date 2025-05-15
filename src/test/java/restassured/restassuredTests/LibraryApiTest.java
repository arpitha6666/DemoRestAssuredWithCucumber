package restassured.restassuredTests;

import data.LibraryDataProvider;
import data.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;
import util.ExcelUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static util.JsonUtil.rawToJsonPath;

public class LibraryApiTest {
    @Test(dataProviderClass = LibraryDataProvider.class , dataProvider = "addBookDataProvider")
    public void addBook(String isbn,int aisle){
        RestAssured.baseURI = "http://216.10.245.166/";
        String response =given().header("Content-Type","application/json")
                .body(Payload.addBookData(isbn,aisle))
                .when()
                .post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath js = rawToJsonPath(response);
        String id =js.get("ID");
        System.out.println("ID : "+id);

    }

    @Test()
    public void addBookWithJsonPayload() throws IOException {
        RestAssured.baseURI = "http://216.10.245.166/";
        String response =given().header("Content-Type","application/json")
                .body(new String(Files.readAllBytes(Paths.get("E:\\MyProjects\\DemoRestAssuredWithCucumber\\src\\test\\resources\\addBook.json"))))
                .when()
                .post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath js = rawToJsonPath(response);
        String id =js.get("ID");
        System.out.println("ID : "+id);

    }

    @Test
    public void addBookMap() throws IOException, InvalidFormatException {
        ExcelUtil util = new ExcelUtil();
        List<String> data = util.getData("RestAddBook","RestAssured");
        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("name",data.get(1));
        jsonMap.put("isbn",data.get(2));
        jsonMap.put("aisle",data.get(3));
        jsonMap.put("author",data.get(4));

        RestAssured.baseURI = "http://216.10.245.166/";
        String response =given().header("Content-Type","application/json")
                .body(jsonMap)
                .when()
                .post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath js = rawToJsonPath(response);
        String id =js.get("ID");
        System.out.println("ID : "+id);

    }
}

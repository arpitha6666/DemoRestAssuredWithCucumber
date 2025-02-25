package restassured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.ecomapi.LoginCredentials;
import model.ecomapi.LoginResponse;
import model.ecomapi.OrderDetail;
import model.ecomapi.Orders;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class E2EEcommerceTest {
    RequestSpecification reqSpec = new RequestSpecBuilder()
            .setBaseUri("https://rahulshettyacademy.com/")
            .setContentType(ContentType.JSON).build();

    RequestSpecification addProdReqSpec = new RequestSpecBuilder()
            .setBaseUri("https://rahulshettyacademy.com/")
            .setContentType(ContentType.MULTIPART).build();
    String loginResourceUrl = "api/ecom/auth/login";
    String addProductUrl ="api/ecom/product/add-product";

    @Test
    public void loginTest() {
        LoginCredentials lc = new LoginCredentials();
        lc.setUserEmail("ars@gmail.com");
        lc.setUserPassword("Password@123");
        LoginResponse loginResponse = given().log().all().spec(reqSpec).body(lc)
                .when()
                .post(loginResourceUrl)
                .then().log().all().extract()
                .response().as(LoginResponse.class);
    }

    @Test
    public void createProductTest(){
        LoginCredentials lc = new LoginCredentials();
        lc.setUserEmail("ars@gmail.com");
        lc.setUserPassword("Password@123");
        LoginResponse loginResponse = given().log().all().spec(reqSpec).body(lc)
                .when()
                .post(loginResourceUrl)
                .then().log().all().extract()
                .response().as(LoginResponse.class);
        String token =loginResponse.getToken();
        String userId =loginResponse.getUserId();
        String response =given().log().all().spec(addProdReqSpec).header("Authorization",token)
                .param("productName","qwerty")
                .param("productAddedBy",userId)
                .param("productSubCategory","shirts")
                .param("productPrice","11500")
                .param("productDescription","Addias Originals")
                .param("productFor","Women")
                .param("productCategory","fashion")
                .multiPart("productImage",new File("E://SeleniumRegPage.png"))
                .when().post(addProductUrl)
                .then().log().all().extract().response().asString();
        JsonPath js = new JsonPath(response);
        String productOrderId =js.getString("productId");
        //"api/ecom/order/create-order/"

        OrderDetail od = new OrderDetail();
        od.setCountry("India");
        od.setProductOrderedId(productOrderId);
        Orders order = new Orders();
        List<OrderDetail> orderList = new ArrayList<>();
        orderList.add(od);
        order.setOrders(orderList);
        RequestSpecification createOrderSpec = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization",token)
                .setBody(order).build();
        String createOrderResponse =given().log().all().spec(createOrderSpec)
                .when().post("/api/ecom/order/create-order")
                .then().log().all().extract().response().asString();
        JsonPath jsCreateOrder = new JsonPath(createOrderResponse);
        jsCreateOrder.getString("message");
        jsCreateOrder.getList("orders");
        jsCreateOrder.getList("productOrderId");

        //delete product
        String deleteResp = given().log().all().spec(reqSpec).header("Authorization", token).pathParam("productId",productOrderId)
                .when().delete("api/ecom/product/delete-product/{productId}")
                .then().log().all().extract().response().asString();
        System.out.println(deleteResp);
    }

}

package com.apitest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class CreateObjectTest {

    File postBody = new File("src/test/resources/request/createObject.json");

    @Test
    public void addObject() {
        RequestSpecification request = given()
                .baseUri("https://api.restful-api.dev")
                .basePath("/objects")
                .header("Content-Type", "application/json")
                .body(postBody);

        Response response = request
                .when()
                .post();

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotEquals(response.statusCode(), 400);
        TestData.objectId = response.jsonPath().getString("id");
    }
}

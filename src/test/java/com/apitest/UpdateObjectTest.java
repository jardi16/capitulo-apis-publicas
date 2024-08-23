package com.apitest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static com.apitest.TestData.objectId;
import static io.restassured.RestAssured.given;

public class UpdateObjectTest {

    File putBody = new File("src/test/resources/request/updateObject.json");

    @Test
    public void updateObject() {
        RequestSpecification request = given()
                .baseUri("https://api.restful-api.dev")
                .basePath("/objects")
                .header("Content-Type", "application/json")
                .body(putBody);

        Response response = request
                .when()
                .put(objectId);

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(), 200);
    }
}

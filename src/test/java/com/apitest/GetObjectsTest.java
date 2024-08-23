package com.apitest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetObjectsTest {

    @Test
    public void getObjects() {
        RequestSpecification request = given()
                .baseUri("https://api.restful-api.dev")
                .basePath("/objects");

        Response response = request
                .when()
                .get();

        Assert.assertEquals(response.statusCode(), 200);
    }
}

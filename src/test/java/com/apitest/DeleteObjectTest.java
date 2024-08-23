package com.apitest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static com.apitest.TestData.objectId;
import static io.restassured.RestAssured.given;

public class DeleteObjectTest {
    @Test
    public void deleteObject() {
        RequestSpecification request = given()
                .baseUri("https://api.restful-api.dev")
                .basePath("/objects")
                .header("Content-Type", "application/json");

        Response response = request
                .when()
                .delete(objectId);

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(), 200);
    }
}

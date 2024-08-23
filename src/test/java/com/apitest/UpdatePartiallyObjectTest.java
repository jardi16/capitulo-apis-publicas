package com.apitest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static com.apitest.TestData.objectId;
import static io.restassured.RestAssured.given;

public class UpdatePartiallyObjectTest {

    File putBody = new File("src/test/resources/request/updatePartiallyObject.json");

    @Test
    public void updatePartiallyObject() {
        RequestSpecification request = given()
                .baseUri("https://api.restful-api.dev")
                .basePath("/objects")
                .header("Content-Type", "application/json")
                .body(putBody);

        Response response = request
                .when()
                .patch(objectId);

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(), 200);
    }
}

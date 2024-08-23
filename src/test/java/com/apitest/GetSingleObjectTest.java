package com.apitest;

import com.apitest.utils.ExtractJsonData;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetSingleObjectTest {


    @Test
    @Parameters({
            "id",
            "expectedStatusCode",
            "expectedName"}) //Sirve para buscar en el xml los parametros indicados para el test
    public void getSingleObject(String  id, int expectedStatusCode, String expectedName) {
        RequestSpecification request = given()
                .baseUri("https://api.restful-api.dev")
                .basePath("/objects");

        Response response = request
                .when()
                .get(id);

        response.prettyPrint();
        Assert.assertEquals(response.statusCode(), expectedStatusCode);

        if(response.statusCode() == 200) {
            String actualName = ExtractJsonData.getNestedValue(response.asString(), "name");
            Assert.assertEquals(actualName, expectedName);
            System.out.println(actualName);
        }



    }
}

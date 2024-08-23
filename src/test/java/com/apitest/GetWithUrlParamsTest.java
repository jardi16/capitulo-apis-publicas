package com.apitest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;

public class GetWithUrlParamsTest {

    String path = "./reporte/Apis.html"; //Ruta donde se generara el archivo del reporte
    ExtentReports extents = new ExtentReports(); //Instancia de libreria para reportes
    ExtentSparkReporter spark = new ExtentSparkReporter(path); // Tipo de reporte
    ExtentTest test;

    @BeforeMethod
    public void setUp() {
        extents.attachReporter(spark);
        test = extents.createTest("Prueba"); //Forma de asignar un test al reporte
    }

    @Test
    public void getWithUrlParams() {
        try {
            test.log(Status.INFO, "Paso la ruta informacion del request"); //Logs para generar informacion del test dentro del reporte
            RequestSpecification request = given()
                    .baseUri("https://api.restful-api.dev")
                    .basePath("/objects")
                    .formParam("id", 9, 10); //Forma de pasar query params

            Response response = request
                    .when()
                    .get();
            test.log(Status.INFO, "Se realizo consumo del API");

            response.prettyPrint();
            int statusCode = response.statusCode();
            test.log(Status.INFO, "El status code obtenido es: " + statusCode);

            Assert.assertEquals(statusCode, 500);
            test.log(Status.PASS, "El test fue exitoso");

        } catch (AssertionError e) {
            test.log(Status.FAIL, "El test falló. " + e.getMessage());
            Assert.fail("Código de estado esperado: 500, pero se obtuvo: " + e.getMessage());

        } catch (Exception e) {
            test.log(Status.FAIL, "Ocurrió un error inesperado: " + e.getMessage());
            Assert.fail("Ocurrió un error inesperado: " + e.getMessage());

        } finally {
            extents.flush();
        }
    }

    //Tambien se puede controlar el manejo de errores en el reporte por medio de un AfterMethod o Class
    @AfterMethod
    public void afterClass(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Fue fallido el test");
        } else {
            test.log(Status.PASS, "Fue exitoso el test");
        }
        extents.flush();
    }
}
package br.edu.utfpr.testrest.testrestassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.ProxySpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FifthTest {

    @BeforeClass
    public static void beforeClass() {
        //configuracao do proxy
        RestAssured.proxy = ProxySpecification
                .host("10.20.10.50")
                .withPort(3128)
                .withAuth("username", "password");
    }

    @Test
    public void testGetVooId54() {
        Response response = given().
                params("flight_number", 54).
                when().
                get("https://api.spacexdata.com/v2/launches");

        response.body().prettyPrint();
    }

    @Test
    public void testGetVooId45() {
        Response response = given().
                params("flight_number", 45).
                when().
                get("https://api.spacexdata.com/v2/launches");

        response.body().prettyPrint();
    }

    @Test
    public void testGetUltimoVoo() {
        Response response = given().
                when().
                get("https://api.spacexdata.com/v2/launches/latest");

        response.body().prettyPrint();
    }

}

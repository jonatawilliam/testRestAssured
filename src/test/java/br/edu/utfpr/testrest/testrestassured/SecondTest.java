package br.edu.utfpr.testrest.testrestassured;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.specification.ProxySpecification;
import org.junit.Test;
import org.junit.BeforeClass;


/**
 *
 * @author andreendo
 */
public class SecondTest {
    
    @BeforeClass
    public static void beforeClass() {
        //configuracao do proxy
        RestAssured.proxy = ProxySpecification
                .host("10.20.10.50")
                .withPort(3128)
                .withAuth("username", "password");
    }

    @Test
    public void test01() {
        Response res = given().
                params("access_key", "eccfbe058e83a6d28f3f81571b900c15").
                params("query", "prazer em conhece-lo").
        when().
                get("http://apilayer.net/api/detect");
        
        res.body().prettyPrint();
    }
}

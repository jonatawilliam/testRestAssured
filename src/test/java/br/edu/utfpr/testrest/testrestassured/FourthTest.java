package br.edu.utfpr.testrest.testrestassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.ProxySpecification;
import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class FourthTest {
    @BeforeClass
    public static void beforeClass() {
        //configuracao do proxy
        RestAssured.proxy = ProxySpecification
                .host("10.20.10.50")
                .withPort(3128)
                .withAuth("username", "password");

        RestAssured.registerParser("text/plain", Parser.JSON);
    }

    @Test
    public void testPostFullRegisterFake() {

        JSONObject location = new JSONObject();
        location.put("street", "addressStreetName");
        location.put("city", "addressCity");
        location.put("state", "addressState");
        location.put("country", "addressCountry");

        JSONObject contact = new JSONObject();
        contact.put("email", "internetEmail");
        contact.put("mobile", "phoneMobile");

        JSONObject data = new JSONObject();
        data.put("first_name", "personNickname");
        data.put("last_name", "nameFirst");
        data.put("location", location);
        data.put("contacts", contact);

        JSONObject info = new JSONObject();
        info.put("token", "-3VTZ0WQ7GZ25lNlTdpVJg");
        info.put("data", data);

        System.out.println("Json enviado: " + info + "\n\n\n");

        Response response = (Response)
                        given().
                                contentType(ContentType.JSON).
                                body(info).
                        when().
                                post("https://app.fakejson.com/q");
        System.out.println("Resposta do Post: \n");
            response.body().prettyPrint();

        response.then().body("email", is(not("")));

    }

    @Test
    public void testPostSimpleRegisterFake() {

        JSONObject data = new JSONObject();
        data.put("name", "name");
        data.put("email", "internetEmail");
        data.put("nickname", "personNickname");
        data.put("gender", "personGender");
        data.put("mobile", "phoneMobile");

        JSONObject info = new JSONObject();
        info.put("token", "-3VTZ0WQ7GZ25lNlTdpVJg");
        info.put("data", data);

        System.out.println("Json enviado: " + info + "\n\n\n");

        Response response = (Response)
                given().
                        contentType(ContentType.JSON).
                        body(info).
                        when().
                        post("https://app.fakejson.com/q");
        System.out.println("Resposta do Post: \n");
        response.body().prettyPrint();

        response.then().body("name", is(not("")));

    }

}



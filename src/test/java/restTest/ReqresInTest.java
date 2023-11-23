package restTest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ReqresInTest {

    @Test
    void authReqresPost() {
        // { "email": "eve.holt@reqres.in", "password": "cityslicka" }
        // { "token": "QpwL5tke4Pnpja7X4" }

        String data = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

        given()
                .contentType(ContentType.JSON) //text, picture
                .body(data)
                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void negativeAuthReqresPost415TypeNo() {

        given()
                .log().uri()
                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(415);
    }

    @Test
    void negativeAuthReqresPost400() {

        given()
                .log().uri()
                .body("Bad")
                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
    void authReqresPostMissingPassword() {
        // { "email": "eve.holt@reqres.in", "password": "cityslicka" }
        // { "token": "QpwL5tke4Pnpja7X4" }

        String data = "{ \"email\": \"eve.holt@reqres.in\"}";

        given()
                .contentType(ContentType.JSON) //text, picture
                .body(data)
                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}

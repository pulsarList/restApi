package rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReqresInDZTest {

    @Test
    void userNotFoundGet() {
        given()
                .log().all()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404)
                .log().all();
    }

    @Test
    void listUsersGet() {
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .body("data[0].id", is(1))
                .body("data[2]", hasKey("color"));
    }

    @Test
    void listUsersGet1() {
        String expectedColor = "#C74375";

        String actualColor = given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .body("data[0].id", is(1))
                .body("data[2]", hasKey("color"))
                .extract().path("data[1].color");

        assertEquals(expectedColor, actualColor);
    }

    @Test
    void createUsers() {
        String userCreate = "{ \"name\": \"Ferdinand Show\", \"job\": \"Action\" }";

        String userResponse = given()
                .contentType(ContentType.JSON)
                .body(userCreate)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .extract().path("name");

        assertEquals("Ferdinand Show", userResponse);
    }

    @Test
    void createUsers1() {
        String userCreate = "{ \"name\": \"Ferdinand Show\", \"job\": \"Action\" }";

        String userResponse = given()
                .contentType(ContentType.JSON)
                .body(userCreate)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .extract().path("job");

        assertNotNull(userResponse);
        assertEquals("Action", userResponse);
    }

    @Test
    void updateUsers() {
        String userUpdate = "{ \"name\": \"Ferdinand Show update\", \"job\": \"Action\" }";

        String userResponse = given()
                .contentType(ContentType.JSON)
                .body(userUpdate)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("", hasKey("updatedAt"))
                .body("job", is("Action"))
                .extract().path("name");

        assertEquals("Ferdinand Show update", userResponse);
    }

    @Test
    void getUser() {
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .body("data.email", is("janet.weaver@reqres.in"));
    }

    @Test
    void createUsersNotName() {
        String userCreate = "{ \"name\": , \"job\": \"Action\" }";

        given()
                .contentType(ContentType.JSON)
                .body(userCreate)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .statusCode(400);

    }

    @Test
    void registerErrorMessing() {

        String email = "{ \"email\": \"sydney@fife\" }";
        given()
                .contentType(ContentType.JSON)
                .body(email)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

}

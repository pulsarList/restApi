package restTest;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import models.ColorsData;
import models.LoginBodyModel;
import models.LoginResponseModel;
import models.UserData;
import org.junit.jupiter.api.Test;
import spec.Specification;

import java.util.List;
import java.util.stream.Collectors;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReqresInTest_part2 {

    @Test
    void loginWithBadPracticeTest() {
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
    void loginWithModelTest() {

        LoginBodyModel bodyModel = new LoginBodyModel();
        bodyModel.setEmail("eve.holt@reqres.in");
        bodyModel.setPassword("cityslicka");

        LoginResponseModel responseModel = given()
                .contentType(ContentType.JSON) //text, picture
                .body(bodyModel)
                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);

        assertThat(responseModel.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginWithAllureTest() {

        LoginBodyModel bodyModel = new LoginBodyModel();
        bodyModel.setEmail("eve.holt@reqres.in");
        bodyModel.setPassword("cityslicka");

        LoginResponseModel responseModel = given()
                .log().uri()
                .log().headers()
                .log().body()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(bodyModel)
                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);

        assertThat(responseModel.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginWithCustomAllureTest() {

        Specification.setSpec(Specification.reqSpec(), Specification.resSpec(200));
        LoginBodyModel bodyModel = new LoginBodyModel();
        bodyModel.setEmail("eve.holt@reqres.in");
        bodyModel.setPassword("cityslicka");

        LoginResponseModel responseModel = given()
                .log().uri()
                .log().headers()
                .log().body()
                .filter(withCustomTemplates())
                .contentType(ContentType.JSON)
                .body(bodyModel)
                .when()
                .post("/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);

        assertThat(responseModel.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void getUserList() {
        Specification.setSpec(Specification.reqSpec(), Specification.resSpec(200));
        List<UserData> users = given()
                .log().body()
                .when()
                .filter(withCustomTemplates())
                .get("/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        users.forEach(x -> assertTrue(x.getAvatar().contains(x.getId().toString())));
        assertTrue(users.stream().allMatch(x-> x.getEmail().endsWith("@reqres.in")));

    }

    @Test
    void colorDataSort() {
        Specification.setSpec(Specification.reqSpec(), Specification.resSpec(200));
        List<ColorsData> colors = given()
                .log().all()
                .when()
                .filter(withCustomTemplates())
                .get("/api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ColorsData.class);

        List<Integer> years = colors.stream().map(ColorsData::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
        assertEquals(sortedYears, years);

    }

}

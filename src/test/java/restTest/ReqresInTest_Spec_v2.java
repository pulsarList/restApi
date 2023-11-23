package restTest;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.ColorsData;
import models.LoginBodyModel;
import models.LoginResponseModel;
import models.UserData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import spec.Specification;

import java.util.List;
import java.util.stream.Collectors;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static spec.LoginSpec_v2.loginRequestSpec;
import static spec.LoginSpec_v2.loginResponseSpec;

public class ReqresInTest_Spec_v2 {

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
    void loginWithSpecTest() {

        LoginBodyModel bodyModel = new LoginBodyModel();
        bodyModel.setEmail("eve.holt@reqres.in");
        bodyModel.setPassword("cityslicka");

        LoginResponseModel responseModel = given(loginRequestSpec)
                .body(bodyModel)

                .when()
                .post("/login")

                .then()
                .spec(loginResponseSpec)
                .extract().as(LoginResponseModel.class);

        assertNotNull(responseModel);
        assertThat(responseModel.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

}

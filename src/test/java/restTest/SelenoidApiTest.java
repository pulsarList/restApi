package restTest;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelenoidApiTest {

//    @Test
//    void checkHubAbNoAuth() {
//        //clearBrowserCookies();
//        given()
//                .log().uri()
//                .when()
//                .get("https://selenoid.autotests.cloud/wd/hub/status")
//
//                .then()
//                .log().status()
//                .log().body()
//                .statusCode(401);
//    }

    @Test
    void selenoidRestRequest() {

        get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .body("total", is(20))
                //     ||
                .assertThat().body("total", equalTo(20));

    }

    @Test
    void selenoidRestRequestGiven() {

        given()
                .when()
                .get("https://selenoid.autotests.cloud/status")

                .then()
                .statusCode(200)
                .body("total", is(20));

    }

    @Test
    void selenoidRestRequestGivenLog() {

        given()
                .log().all()
                .when()
                .get("https://selenoid.autotests.cloud/status")

                .then()
                .log().all()
                .statusCode(200)
                .body("total", is(20));

    }

    @Test
    void selenoidRestRequestGivenLogs() {

        given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/status")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(20));

    }

    @Test
    void selenoidRestRequestVersionChrome() {

        given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/status")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("browsers", hasKey("firefox"))
                .body("browsers.chrome", hasKey("100.0"));

    }

    @Test
    void selenoidRestRequestGoodTotalPath() {
        Integer expectedTotal = 20;

        Integer actualTotal = given()
                .log().uri()
                .when()
                .get("https://selenoid.autotests.cloud/status")

                .then()
                .log().status()
                .statusCode(200)
                .extract().path("total");
        assertEquals(expectedTotal, actualTotal);

    }


    @Test
    void checkHubAuth200() {
        given()
                .log().uri()
                .when()
                .get("https://user1:1234@selenoid.autotests.cloud/wd/hub/status")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("value.ready", is(true));
    }

    @Test
    void checkHubAuth200Wd() {
        given()
                .log().uri()
                .auth().basic("user1", "1234")
                .when()
                .get("https://selenoid.autotests.cloud/wd/hub/status")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("value.ready", is(true));
    }

}

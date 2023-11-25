package groovy;

import groovy.spec.Spec;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.defaultParser;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAlfa {

    @Test
    void createUsersNotName() {
        String userCreate = "{\"metricName\":\"fid\",\"data\":2,\"device\":\"desktop\",\"isModernBrowser\":true,\"city\":\"Казань\",\"url\":\"/get-money/credit-cards/100-days/\"}";

        given()
                .contentType("text/plain;charset=UTF-8")
                .body(userCreate)
                .when()
                .post("https://alfabank.ru/api/v1/client-metrics/metrics")
                .then()
                .log().status()
                .log().all()
                .spec(Spec.responseS)
                .statusCode(200);

    }

}

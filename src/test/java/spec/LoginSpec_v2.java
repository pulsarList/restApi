package spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.notNullValue;

public class LoginSpec_v2 {

    public static RequestSpecification loginRequestSpec =
            with()
                    .log().uri()
                    .log().headers()
                    .filter(withCustomTemplates())
                    .contentType(JSON)
                    .baseUri("https://reqres.in")
                    .basePath("/api");

    public static ResponseSpecification loginResponseSpec =
            new ResponseSpecBuilder()
                    .log(STATUS)
                    .log(LogDetail.BODY)
                    .expectStatusCode(200)
                    .expectBody("token", notNullValue())
                    .build();

}

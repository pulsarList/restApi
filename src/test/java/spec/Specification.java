package spec;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.http.ContentType.JSON;

public class Specification {

    private final static String URL = "https://reqres.in";

    public static RequestSpecification reqSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(URL)
                .setContentType(JSON)
                .build();
    }

    public static ResponseSpecification resSpec(int status) {
        return new ResponseSpecBuilder()
                .expectStatusCode(status)
                .build();
    }

    public static void setSpec(RequestSpecification request, ResponseSpecification response) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }

}

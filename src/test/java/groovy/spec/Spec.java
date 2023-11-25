package groovy.spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.containsString;

public class Spec {
    public static RequestSpecification requestS = with()
            .baseUri("https://reqres.in")
            .basePath("/api")
            .log().all()
            .filter(withCustomTemplates())
            .contentType(ContentType.JSON);


    public static ResponseSpecification responseS = new ResponseSpecBuilder()
            .expectStatusCode(200)
            //.expectBody(containsString("true"))
            .build();
}

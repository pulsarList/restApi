package groovy;

import groovy.modelsP.UserData;
import groovy.spec.Spec;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testcl {
    @Test
    void singleUser() {
        Spec.requestS.when()
                .get("/users/2")
                .then()
                .spec(Spec.responseS)
                .log().body();
    }

//    @Test
//    void singleUserGroovy() {
//        // @formatter:off
//        Spec.requestS
//                .when()
//                    .get("/users")
//                .then()
//                    .spec(Spec.responseS)
//                    .log().body()
//                    .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()"
//                        , hasItem("george.bluth@reqres.in"));
//        // @formatter:on
//    }

    @Test
    void singleUserGroovy1() {
        // @formatter:off
        Spec.requestS
                .when()
                .get("/users")
                .then()
                .spec(Spec.responseS)
                .log().body()
                .body("data.findAll{it.id}.id.flatten() != 0"
                        ,is(true));
        // @formatter:on
    }

    @Test
    void singleUserGroovy2() {
        // @formatter:off
        List<Integer> g = Spec.requestS
                .when()
                .get("/users")
                .then()
                .spec(Spec.responseS)
                .log().body()
                .extract().path("data.findAll{it.id}.id.flatten()");
        for (Integer i : g) {
            System.out.println(i);
        }
        g.forEach(x->assertTrue(x > 0));
        // @formatter:on
    }

    @Test
    void singleUserGroovy3() {
        // @formatter:off
        List<String> g = Spec.requestS
                .when()
                .get("/users")
                .then()
                .spec(Spec.responseS)
                .log().body()
                .extract().path("data.findAll{it.email}.email.flatten()");
        for (String i : g) {
            System.out.println(i);
        }
        g.forEach(x->assertTrue(x.endsWith("reqres.in")));
        // @formatter:on
    }

    @Test
    void listUsers() {
        Spec.requestS.when()
                .get("/users?page=2")
                .then()
                .log().body()
                .body("", hasKey("data"))
                .body("data[0]", hasKey("id"));
    }

    @Test
    void singleUserWithModel() {
        UserData data = Spec.requestS.when()
                .get("/users/2")
                .then()
                .spec(Spec.responseS)
                .log().body()
                .extract().as(UserData.class);

        assertEquals(2, data.getUser().getId());
    }
}

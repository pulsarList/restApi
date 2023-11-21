package rest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

public class DemoWebShopTest {

    @BeforeAll
    public static void setUp() {
        baseURI = "https://demowebshop.tricentis.com";
    }

    @Test
    void addToCardTest() {
        String cookie = "8B8E5A030C44D3039AD7B95A98FCA5F178CF60BE416FA85DFEE8BCFFEB8C9D338A8EB2A8A38FB114" +
                "EC6F7D70F661E406D719F70C4A86D1DC2FC06D51410E9DB685B30C9E09E085F775CE8DF27A299812CC235780" +
                "68006A6DDC0C16736A35F4DC57771964A2DE585D7F28708BC8E943E85AB938FCF3522B37496F7446B15ED9DF" +
                "6B5DA1747245CC0D4E55E35F2CF5F79C";
        String body = "product_attribute_72_5_18=65&" +
                "product_attribute_72_6_19=54&" +
                "product_attribute_72_3_20=57&" +
                "product_attribute_72_8_30=94&" +
                "addtocart_72.EnteredQuantity=3";

        given()
//                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("NOPCOMMERCE.AUTH", cookie)
                .body(body)
                .when()
                .post(baseURI+"/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("updatetopcartsectionhtml", is("(21)"))
                .body("", hasKey("message"));
    }

    @Test
    void addToCardTestAnonym() {
        String body = "product_attribute_72_5_18=65&" +
                "product_attribute_72_6_19=54&" +
                "product_attribute_72_3_20=57&" +
                "product_attribute_72_8_30=94&" +
                "addtocart_72.EnteredQuantity=3";

        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body(body)
                .when()
                .post(baseURI+"/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("updatetopcartsectionhtml", is("(3)"))
                .body("", hasKey("message"));
    }


}

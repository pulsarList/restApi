package restTest;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

public class DemoWebShopTest {

    @BeforeAll
    public static void setUp() {
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        Configuration.browser = "chrome";
        baseURI = "https://demowebshop.tricentis.com";
        Configuration.baseUrl = baseURI;
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
                .post("/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                //.body("updatetopcartsectionhtml", is("(21)"))
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
                .post("/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("updatetopcartsectionhtml", is("(3)"))
                .body("", hasKey("message"));
        open("");
    }


    @Test
    void authDemoWebShop() {

        String authCookie =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .formParam("Email", "test@telerf.ru")
                        .formParam("Password", "password")
                        .formParam("RememberMe", false)
                        .when()
                        .post("/login")
                        .then()
                        .statusCode(302)
                        .extract()
                        .cookie("NOPCOMMERCE.AUTH");

        open("/Themes/DefaultClean/Content/images/logo.png");

        getWebDriver().manage().addCookie(
                new Cookie("NOPCOMMERCE.AUTH", authCookie)
        );

        open("");
        $("[href='/customer/info']").should(text("test@telerf.ru"), appear);

    }

    @Test
    void addFirstLastName() {

        given()
                .cookie("NOPCOMMERCE.AUTH", "000835FD9991690B5440EC49B37D28DFDEC28748E07A3F899F9D0C35FDDCB" +
                        "7347A728CA7094F5862B5A870641AE30E538695CE0AB99B37FBE542BC1C2A4D308A34DEDE44D3EC52F881B2E38D8F16484" +
                        "9FE6B39CDB92EB4BF00CF447C515816CEF7683ADDF3DAAC4EBDA122304F35CD32CE662FA1ACF35575EAB5764AF2478AE5F" +
                        "F59309D62AC0789C82118AEE02FF6ED")
                .cookie("__RequestVerificationToken", "_SUjdUNt5plTs9cA5B2GPfvx0f9ANKA5oquX6zCyjC8qMTrNeuz" +
                        "1BnFFOMLixsx0RpwlZGNYRq0qAD6osjPiuo8NBdq53GsGLiYa-7YX6_A1")
                .contentType("application/x-www-form-urlencoded")
                .formParam("__RequestVerificationToken", "UkI1Dtk7ravXF8jgdG4vwhGxmiJXYMZqmXup_zKrBxa0cESr1L4c41_BUGCdTv27HXW_qg1RwL6i4pC88mXcWMvIR3r_JRMV6bQ9yylBGEh_e_qaRuRi5ija6L-aCHlQ0")
                .formParam("FirstName", "New Name")
                .formParam("LastName", "New LastName")
                .formParam("Email", "test@telerf.ru")
                .when()
                .post("/customer/info")
                .then()
                .log().all()
                .statusCode(302);

        String authCookie = "000835FD9991690B5440EC49B37D28DFDEC28748E07A3F899F9D0C35FDDCB7347A728CA7094" +
                "F5862B5A870641AE30E538695CE0AB99B37FBE542BC1C2A4D308A34DEDE44D3EC52F881B2E38D8F164849FE" +
                "6B39CDB92EB4BF00CF447C515816CEF7683ADDF3DAAC4EBDA122304F35CD32CE662FA1ACF35575EAB5764AF" +
                "2478AE5FF59309D62AC0789C82118AEE02FF6ED";
        open("/Themes/DefaultClean/Content/images/logo.png");
        getWebDriver().manage().addCookie(
                new Cookie("NOPCOMMERCE.AUTH", authCookie)
        );

        open("");
        $("[href='/customer/info']").should(text("test@telerf.ru"), appear).click();

    }


}

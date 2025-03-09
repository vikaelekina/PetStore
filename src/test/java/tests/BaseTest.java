package tests;

import io.restassured.RestAssured;
import methods.Requests;
import methods.Responses;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    public final Requests request = new Requests();
    public final Responses response = new Responses();

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }
}

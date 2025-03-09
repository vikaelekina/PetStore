package methods;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.ApiResponse;
import model.Pet;

import javax.net.ssl.HttpsURLConnection;
import java.util.List;

public class Responses {

    @Step("Возвращен питомец, статус код 200")
    public Pet returnPet(Response response) {
        return response
                .then()
                .log().ifValidationFails()
                .statusCode(HttpsURLConnection.HTTP_OK)
                .extract()
                .body()
                .jsonPath()
                .getObject("", Pet.class);
    }

    @Step("Возвращен список питомцев, статус код 200")
    public List<Pet> returnListPet(Response response) {
        return response
                .then()
                .log().ifValidationFails()
                .statusCode(HttpsURLConnection.HTTP_OK)
                .extract()
                .body()
                .jsonPath()
                .getList("", Pet.class);
    }

    @Step("Возвращен ответ, статус код 200")
    public ApiResponse returnCustomResponse(Response response) {
        return response
                .then()
                .log().ifValidationFails()
                .statusCode(HttpsURLConnection.HTTP_OK)
                .extract()
                .body()
                .jsonPath()
                .getObject("", ApiResponse.class);
    }

    @Step("Возвращена ошибка, статус код 404")
    public ApiResponse returnErrorNotFound(Response response) {
        return response
                .then()
                .log().ifValidationFails()
                .statusCode(HttpsURLConnection.HTTP_NOT_FOUND)
                .extract()
                .body()
                .jsonPath()
                .getObject("", ApiResponse.class);
    }

    @Step("Возвращена ошибка, статус код 400")
    public ApiResponse returnErrorBadRequest(Response response) {
        return response
                .then()
                .log().ifValidationFails()
                .statusCode(HttpsURLConnection.HTTP_BAD_REQUEST)
                .extract()
                .body()
                .jsonPath()
                .getObject("", ApiResponse.class);
    }

    @Step("Возвращено значение статус кода")
    public Integer returnStatusCode(Response response) {
        return response
                .getStatusCode();
    }

}

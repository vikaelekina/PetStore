package methods;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Pet;

import static io.restassured.RestAssured.given;

public class Requests {

    @Step("Отправка запроса на создание нового питомца")
    public Response createNewPet(Object newPet) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .body(newPet)
                .log().ifValidationFails()
                .post(EndPoint.PET.getPath());
    }

    @Step("Отправка запроса для поиска питомца по ID")
    public Response findPetByID(Object id) {
        return given()
                .pathParam("id", id)
                .when()
                .log().ifValidationFails()
                .get(EndPoint.PETBYID.getPath());
    }


    @Step("Отправка запроса для удаления питомца по ID")
    public Response deletePetByID(Object id) {
        return given()
                .pathParam("id", id)
                .when()
                .log().ifValidationFails()
                .delete(EndPoint.PETBYID.getPath());
    }

    @Step("Отправка запроса для поиска питомца по статусу")
    public Response findPetByStatus(Object petStatus) {
        return given()
                .queryParam("status", petStatus)
                .log().ifValidationFails()
                .when()
                .get(EndPoint.PETBYSTATUS.getPath());
    }

    @Step("Отправка запроса для обновления информации о питомце")
    public Response updatePet(Object pet) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .body(pet)
                .log().all()
                .put(EndPoint.PET.getPath());
    }
}

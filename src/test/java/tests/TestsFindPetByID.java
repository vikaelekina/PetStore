package tests;

import io.qameta.allure.Description;
import model.ApiResponse;
import model.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.net.ssl.HttpsURLConnection;


public class TestsFindPetByID extends BaseTest {

    @Test
    @DisplayName("Поиск питомца c ID = 1")
    @Description("Тест проверяет, что ID питомца из тела ответа соответствует ID из запроса, статус код = 200")
    public void testFindPetByID() {
        int ID = 1;
        Pet petRequest = new Pet(ID);
        request.createNewPet(petRequest);
        Pet pet = response.returnPet(request.findPetByID(ID));
        Assertions.assertEquals(ID, pet.getId());
    }

    @ParameterizedTest(name = "Поиск несуществующему питомца с ID = {0}")
    @Description("Тест проверяет, что тело ответа соответствует ответу при ошибке 404, статус код = 404")
    @ValueSource(strings = {"2", "9223372036854775807"})
    public void testNotFoundPetByID(String ID) {
        request.deletePetByID(ID);
        ApiResponse customError = response.returnErrorNotFound(request.findPetByID(ID));
        Assertions.assertEquals(Errors.PETNOTFOUND.getResponseModel(), customError);
    }


    @ParameterizedTest(name = "Поиск питомца по невалидному значению ID = {0}")
    @Description("Тест проверяет, что статус код ответа = 400")
    @CsvFileSource(resources = "/InvalidID.csv")
    public void testFindPetByInvalidID(String ID) {
        int statusCode = response.returnStatusCode(request.findPetByID(ID));
        Assertions.assertEquals(HttpsURLConnection.HTTP_BAD_REQUEST, statusCode);
    }


}

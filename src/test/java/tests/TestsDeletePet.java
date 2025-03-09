package tests;

import io.qameta.allure.Description;
import model.ApiResponse;
import model.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import javax.net.ssl.HttpsURLConnection;

public class TestsDeletePet extends BaseTest{

    @Test
    @DisplayName("Удаление питомца по ID")
    @Description("Тест проверяет, что ID животного в ответе соответствует указанному в запросе, статус код 200, при попытке поиска удаленного животного - ошибка 404")
    public void deletePetByID(){
        Integer ID = 1;
        request.createNewPet(new Pet(ID));
        ApiResponse responseModel = response.returnCustomResponse(request.deletePetByID(ID));
        Assertions.assertEquals(ID.toString(), responseModel.getMessage());
        Assertions.assertEquals(Errors.PETNOTFOUND.getResponseModel(),response.returnErrorNotFound(request.findPetByID(ID)));
    }

    @Test
    @DisplayName("Удаление несуществующего питомца")
    @Description("Тест проверяет, что статус код ответа = 404")
    public void deletePetByIDNotFound(){
        Integer ID = 4;
        request.deletePetByID(ID);
        int statusCode = response.returnStatusCode(request.deletePetByID(ID));
        Assertions.assertEquals(HttpsURLConnection.HTTP_NOT_FOUND, statusCode);
    }

    @ParameterizedTest(name = "Удаление питомца по невалидному значению ID = {0}")
    @Description("Тест проверяет, что статус код ответа = 400")
    @CsvFileSource(resources = "/InvalidID.csv")
    public void deletePetByInvalidID(String ID){
        request.deletePetByID(ID);
        int statusCode = response.returnStatusCode(request.deletePetByID(ID));
        Assertions.assertEquals(HttpsURLConnection.HTTP_BAD_REQUEST, statusCode);
    }






}

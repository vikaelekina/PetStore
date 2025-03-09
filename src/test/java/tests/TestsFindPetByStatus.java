package tests;

import io.qameta.allure.Description;
import model.Pet;
import model.PetStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import javax.net.ssl.HttpsURLConnection;
import java.util.List;

public class TestsFindPetByStatus extends BaseTest {

    @Test
    @DisplayName("Поиск питомцев по статусу")
    @Description("Тест проверяет, что статус каждого животного в ответе соответствует указанному при поиске, статус код 200")
    public void testFindPetByStatus(){
       List<Pet> listPet = response.returnListPet(request.findPetByStatus(PetStatus.available));
       Assertions.assertTrue(listPet.stream().allMatch(p->p.getStatus().equals(PetStatus.available)));
    }

    @ParameterizedTest (name = "Поиск питомцев по невалидному статусу = {0}")
    @Description("Тест проверяет, что статус код ответа = 400")
    @CsvFileSource(resources = "/InvalidStatus.csv", nullValues = "N/A")
    public void testFindPetByInvalidStatus(String status){
        int statusCode = response.returnStatusCode(request.findPetByStatus(status));
        Assertions.assertEquals(HttpsURLConnection.HTTP_BAD_REQUEST,statusCode);
    }
}

package tests;

import io.qameta.allure.Description;
import model.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import javax.net.ssl.HttpsURLConnection;

public class TestsUpdatePet extends BaseTest {

    @Test
    @DisplayName("Обновление информации о питомце")
    @Description("Тест проверяет, что при обновлении информации о питомце body ответа соответствует body запроса, статус код 200, при поиске обновленного питомца возвращается body ответа то же, что и при его обновлении, статус код 200")
    public void testUpdatePet() {
        int ID = 6;
        Pet petRequest = new Pet(ID);
        request.createNewPet(petRequest);
        petRequest.setName(petRequest.getName() + "1");
        Assertions.assertEquals(petRequest, response.returnPet(request.updatePet(petRequest)));
        Assertions.assertEquals(petRequest, response.returnPet(request.findPetByID(ID)));
    }

    @Test
    @DisplayName("Обновление информации о несуществующем питомце")
    @Description("Тест проверяет, что статус код ответа = 404")
    public void testUpdateNotFoundPet() {
        int ID = 7;
        request.deletePetByID(ID);
        Assertions.assertEquals(HttpsURLConnection.HTTP_NOT_FOUND, response.returnStatusCode(request.updatePet(new Pet(ID))));
    }

    @Test
    @DisplayName("Обновление информации о питомце при невалидном ID")
    @Description("Тест проверяет, что статус код ответа = 400")
    public void testUpdatePetInvalidID() {
        int ID = 0;
        Assertions.assertEquals(HttpsURLConnection.HTTP_BAD_REQUEST, response.returnStatusCode(request.updatePet(new Pet(ID))));
    }

    @ParameterizedTest(name = "Обновление {0}")
    @Description("Тест проверяет, что статус код ответа = 405")
    @CsvFileSource(resources = "/InvalidPet.csv",delimiter = ';')
    public void testUpdatePetInvalidPet(String name, String pet) {
        Assertions.assertEquals(HttpsURLConnection.HTTP_BAD_METHOD, response.returnStatusCode(request.updatePet(pet)));
    }
}

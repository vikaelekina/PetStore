package tests;

import io.qameta.allure.Description;
import model.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import javax.net.ssl.HttpsURLConnection;

public class TestsCreateNewPet extends BaseTest {

    @Test
    @DisplayName("Создание нового питомца")
    @Description("Тест проверяет, что при создании питомца body ответа соответствует body запроса, статус код 200, при поиске созданного питомца возвращается body ответа то же, что и при его создании, статус код 200 ")
    public void testCreateNewPet() {
        int ID = 1;
        Pet petRequest = new Pet(ID);
        Assertions.assertEquals(petRequest, response.returnPet(request.createNewPet(petRequest)));
        Pet petResponse = response.returnPet(request.findPetByID(ID));
        Assertions.assertEquals(petRequest, petResponse);
    }

    @ParameterizedTest(name = "Создание {0}")
    @Description("Тест проверяет, что статус код ответа = 405")
    @CsvFileSource(resources = "/InvalidPet.csv",delimiter = ';')
    public void testCreateNewPetWithInvalidInput(String name, String pet){
        Assertions.assertEquals(HttpsURLConnection.HTTP_BAD_METHOD, response.returnStatusCode(request.createNewPet(pet)));
    }

}

package org.example.courier;

import io.qameta.allure.Issue;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CourierUnsuccessfulLoginWithoutRequiredFieldsTest {

    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    private final Credentials creds;
    private final String description;

    public CourierUnsuccessfulLoginWithoutRequiredFieldsTest(Credentials creds, String description) {
        this.creds = creds;
        this.description = description;
    }

    @Parameterized.Parameters(name = "{1}")
    public static Object[][] getResult() {
        return new Object[][] {
                { new Credentials(CourierGenerator.existingCourier().getLogin(), null), "Без пароля" },
                { new Credentials(null, CourierGenerator.existingCourier().getLogin()), "Без логина" },
                { new Credentials(null, null), "Без обоих полей" }
        };
    }

    @Test
    @DisplayName("Неуспешная авторизация курьера без обязательных полей")
    @Issue("При логине без поля login или без всех полей приходит 504, а не 400")
    public void unsuccessfulCourierLoginWithoutRequiredField() {
        ValidatableResponse loginResponse = client.login(creds);
        check.gotErrorWhenLoginWithoutRequiredFields(loginResponse);
    }

}

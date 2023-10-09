package org.example.courier;

import io.qameta.allure.Issue;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CourierUnsuccessfulLoginWithIncorrectDataTest {

    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    private final Credentials creds;
    private final String description;
    private static String randomData = RandomStringUtils.randomAlphanumeric(5, 10);

    public CourierUnsuccessfulLoginWithIncorrectDataTest(Credentials creds, String description) {
        this.creds = creds;
        this.description = description;
    }

    @Parameterized.Parameters(name = "{1}")
    public static Object[][] getResult() {
        return new Object[][] {
                { new Credentials(CourierGenerator.existingCourier().getLogin(), randomData), "С неверным паролем" },
                { new Credentials(randomData, randomData), "С несуществующем логином" }
        };
    }

    @Test
    @DisplayName("Неуспешная авторизация курьера с неверными данными")
    public void unsuccessfulCourierLoginWithoutRequiredField() {
        ValidatableResponse loginResponse = client.login(creds);
        check.gotErrorWhenLoginWithIncorrectData(loginResponse);
    }

}

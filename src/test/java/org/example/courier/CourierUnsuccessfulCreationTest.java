package org.example.courier;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CourierUnsuccessfulCreationTest {

    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();

    private final String field;
    private final String description;

    public CourierUnsuccessfulCreationTest(String field, String description) {
        this.field = field;
        this.description = description;
    }

    @Parameterized.Parameters(name = "{1}")
    public static Object[][] getResult() {
        return new Object[][] {
                { "login", "Без логина" },
                { "password", "Без пароля" },
                { "all", "Без всего" }
        };
    }

    @Test
    public void unsuccessfulCourierCreation() {
        Courier courier = CourierGenerator.withoutSomeField(field);

        ValidatableResponse response = client.create(courier);
        check.gotErrorWhenCreatingWithoutRequiredFields(response);
    }

}

package org.example.courier;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class CourierTest {

    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    private Courier courier;



    @Test
    public void unsuccessfulCourierCreation() {
        courier = CourierGenerator.withoutSomeField("login");

        ValidatableResponse response = client.create(courier);
        check.gotErrorWhenCreatingWithoutRequiredFields(response);
    }
}
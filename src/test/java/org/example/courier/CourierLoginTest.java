package org.example.courier;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class CourierLoginTest {

    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    private int courierId;

    @After
    public void deleteCourier() {
        ValidatableResponse delete = client.delete(courierId);
        check.deletedSuccessfully(delete);
    }

    @Test
    public void successfulCourierLogin() {
        Courier courier = CourierGenerator.randomLoginField();

        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);

        Credentials creds = Credentials.from(courier);

        ValidatableResponse loginResponse = client.login(creds);
        courierId = check.loggedInSuccessfully(loginResponse);

        assert courierId != 0;
    }
}
package org.example.courier;

import io.qameta.allure.Issue;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class CourierCreationTest {

    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    private Courier courier;

    @After
    public void loginAndDeleteCourier() {
        Credentials creds = Credentials.from(courier);
        ValidatableResponse loginResponse = client.login(creds);
        int courierId = check.loggedInSuccessfully(loginResponse);

        ValidatableResponse delete = client.delete(courierId);
        check.deletedSuccessfully(delete);
    }

    @Test
    public void successfulCourierCreation() {
        courier = CourierGenerator.randomLoginField();

        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);
    }

    @Test
    public void successfulCourierCreationWithOnlyRequiredFields() {
        courier = CourierGenerator.withoutSomeField("firstName");

        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);
    }

    @Test
    @Issue("ФР != ОР из описания API")
    public void unsuccessfulCourierCreationWithExistingLogin() {
        courier = CourierGenerator.randomLoginField();

        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);

        response = client.create(courier);
        check.gotErrorWhenCreatingWithExistingLogin(response);
    }
}
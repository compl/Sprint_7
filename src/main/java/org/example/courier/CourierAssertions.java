package org.example.courier;

import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;
import java.util.Map;

import static org.hamcrest.Matchers.*;

public class CourierAssertions {

    public void createdSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .body("ok", is(true))
        ;
    }

    public int loggedInSuccessfully(ValidatableResponse loginResponse) {
        int id = loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("id")
                ;
        return id;
    }

    public void deletedSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK);
    }

    public void gotErrorWhenCreatingWithExistingLogin(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CONFLICT)
                .body("message", equalTo("Этот логин уже используется"));

    }

    public void gotErrorWhenCreatingWithoutRequiredFields(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));

    }

    public void gotErrorWhenLoginWithoutRequiredFields(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));

    }

    public void gotErrorWhenLoginWithIncorrectData(ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));

    }
}
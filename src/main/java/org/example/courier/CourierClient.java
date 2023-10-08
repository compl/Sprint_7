package org.example.courier;

import io.restassured.response.ValidatableResponse;
import org.example.Client;

import java.util.Map;

public class CourierClient extends Client {
    private static final String COURIER_PATH = "/courier";
    private static final String LOGIN_PATH = "/login";

    public ValidatableResponse create(Courier courier) {
        return spec()
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    public ValidatableResponse login(Credentials creds) {
        return spec()
                .body(creds)
                .when()
                .post(COURIER_PATH + LOGIN_PATH)
                .then().log().all();
    }

    public ValidatableResponse delete(int courierId) {
        return spec()
                .body(Map.of("id", String.valueOf(courierId)))
                .when()
                .delete(COURIER_PATH + "/" + courierId)
                .then().log().all();
    }
}
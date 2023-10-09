package org.example.order;

import io.restassured.response.ValidatableResponse;
import org.example.Client;
import org.example.courier.Courier;

import java.util.Map;

public class OrderClient extends Client {

    private static final String ORDERS_PATH = "/orders";
    private static final String CANCEL_PATH = "/cancel";

    public ValidatableResponse create(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ORDERS_PATH)
                .then().log().all();
    }

    public ValidatableResponse cancel(int orderId) {
        return spec()
                .body(Map.of("track", orderId))
                .when()
                .put(ORDERS_PATH + CANCEL_PATH + "?track=" + orderId)
                .then().log().all();
    }

    public ValidatableResponse getOrderList() {
        return spec()
                .get(ORDERS_PATH)
                .then().log().all();
    }

}
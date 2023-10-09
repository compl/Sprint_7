package org.example.order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class OrderListTest {

    private final OrderClient client = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();

    @Test
    @DisplayName("Получение списка заказов")
    public void successfullyGotOrderList() {

        ValidatableResponse response = client.getOrderList();
        check.gotOrderListSuccessfully(response);
    }

}

package org.example.order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.Client;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class OrderCreationTest {

    private final OrderClient client = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();
    private int track;
    private final String color;
    private final String description;

    public OrderCreationTest(String color, String description) {
        this.color = color;
        this.description = description;
    }

    @After
    public void cancelOrder() {
        ValidatableResponse delete = client.cancel(track);
        check.cancelSuccessfully(delete);
    }

    @Parameterized.Parameters(name = "{1}")
    public static Object[][] getResult() {
        return new Object[][] {
                { "black", "Черный цвет" },
                { "grey", "Серый цвет" },
                { "both", "Оба цвета" },
                { "none", "Без цветов" }
        };
    }

    @Test
    @DisplayName("Создание заказа")
    public void successfulOrderCreation() {
        Order order = OrderGenerator.newOrder(color);

        ValidatableResponse response = client.create(order);
        track = check.createdSuccessfully(response);
    }

}

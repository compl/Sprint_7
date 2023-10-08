package org.example.courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {

    public static Courier existingCourier() {
        return new Courier("Test_123", "P@ssw0rd123", "Sparrow");
    }

    public static Courier randomLoginField() {
        return new Courier(RandomStringUtils.randomAlphanumeric(5, 10), "P@ssw0rd123", "Sparrow");
    }

    public static Courier withoutSomeField(String field) {
        switch (field) {
            case "login":
                return new Courier(null, "P@ssw0rd123", "Sparrow");
            case "password":
                return new Courier(RandomStringUtils.randomAlphanumeric(5, 10), null, "Sparrow");
            case "firstName":
                return new Courier(RandomStringUtils.randomAlphanumeric(5, 10), "P@ssw0rd123", null);
            case "all":
                return new Courier(null, null, null);
            default:
                throw new IllegalArgumentException("Unexpected field: " + field);
        }
    }
}
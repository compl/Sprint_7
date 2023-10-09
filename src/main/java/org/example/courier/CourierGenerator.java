package org.example.courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {

    private final static String EXISTING_LOGIN = "Test_123";
    private static String randomLogin;
    private final static String DEFAULT_PASSWORD = "P@ssw0rd123";
    private final static String DEFAULT_FIRSTNAME = "Sparrow";


    public static Courier existingCourier() {
        return new Courier(EXISTING_LOGIN, DEFAULT_PASSWORD, DEFAULT_FIRSTNAME);
    }

    public static Courier randomLoginField() {
        randomLogin = RandomStringUtils.randomAlphanumeric(5, 10);
        return new Courier(randomLogin, DEFAULT_PASSWORD, DEFAULT_FIRSTNAME);
    }

    public static Courier withoutSomeField(String field) {
        randomLogin = RandomStringUtils.randomAlphanumeric(5, 10);
        switch (field) {
            case "login":
                return new Courier(null, DEFAULT_PASSWORD, DEFAULT_FIRSTNAME);
            case "password":
                return new Courier(randomLogin, null, DEFAULT_FIRSTNAME);
            case "firstName":
                return new Courier(randomLogin, DEFAULT_PASSWORD, null);
            case "all":
                return new Courier(null, null, null);
            default:
                throw new IllegalArgumentException("Unexpected field: " + field);
        }
    }
}
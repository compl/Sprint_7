package org.example.order;

public class OrderGenerator {

    private final static String FIRST_NAME = "Alex";
    private final static String LAST_NAME = "Way";
    private final static String ADDRESS = "Moscow";
    private final static String METRO_STATION = "CSKA";
    private final static String PHONE = "88005553535";
    private final static int RENT_TIME = 7;
    private final static String DELIVERY_DATE = "2023-07-07";
    private final static String COMMENT = "Test";
    private final static String[] COLOR_BLACK = {"BLACK"};
    private final static String[] COLOR_GREY = {"GREY"};
    private final static String[] BOTH_COLORS = {"BLACK", "GREY"};
    private final static String[] NO_COLORS = {};

    public static Order newOrder(String color) {
        return new Order(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT,
                getColor(color));

    }

    public static String[] getColor(String color) {
        switch (color) {
            case "black":
                return COLOR_BLACK;
            case "grey":
                return COLOR_GREY;
            case "both":
                return BOTH_COLORS;
            case "none":
                return NO_COLORS;
            default:
                throw new IllegalArgumentException("Unexpected color: " + color);

        }
    }
}

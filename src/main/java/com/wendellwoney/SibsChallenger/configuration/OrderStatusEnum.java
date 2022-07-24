package com.wendellwoney.SibsChallenger.configuration;

public enum OrderStatusEnum {

    INPROGESS("IN PROGRESS"),
    COMPLETED("COMPLETED"),
    CANCELED("CANCELED"),
    REMOVED("REMOVED");

    private final String name;
    private OrderStatusEnum(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

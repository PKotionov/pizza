package ru.mail.pashok.shop.repository.model;

public enum StatusEnum {
    PROCESSING,
    DELIVERY,
    CLOSED,
    CANCELED;

    public static StatusEnum getStatusFromStatusID(int statusID) {
        switch (statusID) {
            case 1:
                return PROCESSING;
            case 2:
                return DELIVERY;
            case 3:
                return CLOSED;
            case 4:
                return CANCELED;
            default:
                return null;
        }
    }
}

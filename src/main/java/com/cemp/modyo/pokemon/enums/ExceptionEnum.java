package com.cemp.modyo.pokemon.enums;

public enum ExceptionEnum {

    INVALID_ID(1000, "Invalid Id."),
    NOT_FOUND(1001, "Resource not found."),
    COMMUNICATION_EXCEPTION(1002, "Communication Exception."),
    INVALID_DATA(1003, "Invalid Data."),
    INVALID_PARAMS(1004, "Invalid Parameters. You must provide none of or both the offset and the limit."),
    ;

    private final int id;
    private final String detail;

    private ExceptionEnum(int id, String detail) {
        this.id = id;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public String getDetail() {
        return detail;
    }
}

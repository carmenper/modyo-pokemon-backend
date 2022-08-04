package com.cemp.modyo.pokemon.enums;

public enum ExceptionEnum {

    INVALID_ID(1000, "Id inválido."),
    NOT_FOUND(1001, "no fue encontrado."),
    COMMUNICATION_EXCEPTION(1002, "Excepción en comunicación."),
    INVALID_DATA(1003, "Data invalida."),
    INVALID_PARAMS(1004, "Parametros inválidos. " +
            "Debe proveer ningun parámetro o ambos valores del offset y limit."),
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

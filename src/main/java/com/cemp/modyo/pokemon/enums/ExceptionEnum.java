package com.cemp.modyo.pokemon.enums;

public enum ExceptionEnum {

    INVALID_ID(1000, "Id inválido."),
    NOT_FOUND(1001, "no fue encontrado."),
    COMMUNICATION_EXCEPTION(1002, "Excepción en comunicación."),
    INVALID_DATA(1003, "Data invalida."),
    INVALID_PARAMS(1004, ExceptionEnum.PARAMETROS_INVALIDOS +
            "Debe proveer ningun parámetro o ambos valores de offset y limit."),
    INVALID_PARAM_OFFSET(1005, ExceptionEnum.PARAMETROS_INVALIDOS +
            "Parametro 'offset' debe tener valor numérico entero."),
    INVALID_PARAM_LIMIT(1006, ExceptionEnum.PARAMETROS_INVALIDOS +
            "Parametro 'limit' debe tener valor numérico entero."),

    ;

    private static final String PARAMETROS_INVALIDOS = "Parametros inválidos. ";
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

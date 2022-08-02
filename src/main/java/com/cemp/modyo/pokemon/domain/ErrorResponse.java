package com.cemp.modyo.pokemon.domain;

import lombok.Getter;


public class ErrorResponse {

    @Getter
    private int id;
    @Getter private final String timestamp;
    @Getter private String detail;

    private ErrorResponse(String timestamp) {
        this.timestamp = timestamp;
    }

    public ErrorResponse(int id, String timestamp) {
        this(timestamp);
        this.id = id;
        this.detail = "Error sin detalle";
    }

    public ErrorResponse(int id, String detail, String timestamp) {
        this(timestamp);
        this.id = id;
        this.detail = detail;
    }

}

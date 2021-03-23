package com.churrascoprime.api.dtos.error;

public class ErrorDto {

    private String error;
    private String property;

    public ErrorDto(String error) {
        this.error = error;
    }

    public ErrorDto(String error, String property) {
        this.error = error;
        this.property = property;
    }

    public String getError() {
        return error;
    }

    public String getProperty() {
        return property;
    }
}

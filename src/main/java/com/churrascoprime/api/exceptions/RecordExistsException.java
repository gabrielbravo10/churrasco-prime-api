package com.churrascoprime.api.exceptions;

public class RecordExistsException extends RuntimeException {

    private static final String RECORD_EXISTS = "error.exists";

    public RecordExistsException() {
        super(RECORD_EXISTS);
    }

    public RecordExistsException(String message) {
        super(message);
    }
}

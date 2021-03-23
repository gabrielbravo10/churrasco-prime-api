package com.churrascoprime.api.exceptions;

public class RecordNotFoundException extends RuntimeException {

    private static final String RECORD_NOT_FOUND = "error.notFound";

    public RecordNotFoundException() {
        super(RECORD_NOT_FOUND);
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}

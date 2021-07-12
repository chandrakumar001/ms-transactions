package com.chandraedu.api.transactions.exception;

public class NoRecordFoundException extends RuntimeException {

    public static final String ERROR_NO_RECORD_FOUND = "No Record Found";

    public NoRecordFoundException() {
        super(ERROR_NO_RECORD_FOUND);
    }

    public NoRecordFoundException(final String msg) {
        super(msg);
    }
}

package com.creditcard.account.exception;

public class NoRecordFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoRecordFoundException(final String message) {
        super(message);
    }
}
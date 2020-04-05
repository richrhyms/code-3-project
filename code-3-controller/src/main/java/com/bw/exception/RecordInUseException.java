package com.bw.exception;

public class RecordInUseException extends RuntimeException {

    public RecordInUseException() {
        super();
    }

    public RecordInUseException(String message) {
        super(message);
    }
}

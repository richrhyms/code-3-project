package com.bw.exception;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }
}

package com.cici.exception;

public class OrderDispachedException extends RuntimeException {
    private static final long serialVersionUID= 2l;

    public OrderDispachedException(String message) {
        super(message);
    }

    public OrderDispachedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

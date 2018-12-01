package com.es.phoneshop.model.exception;

public class IllegalStockArgumentException extends PhoneshopAppException {

    public IllegalStockArgumentException() {
    }

    public IllegalStockArgumentException(String message) {
        super(message);
    }

    public IllegalStockArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalStockArgumentException(Throwable cause) {
        super(cause);
    }
}

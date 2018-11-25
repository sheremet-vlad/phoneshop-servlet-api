package com.es.phoneshop.model.exception;

public class IllegalSortArgumentException extends PhoneshopAppException {
    public IllegalSortArgumentException() {
        super();
    }

    public IllegalSortArgumentException(String message) {
        super(message);
    }

    public IllegalSortArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalSortArgumentException(Throwable cause) {
        super(cause);
    }
}

package com.es.phoneshop.model.exception;

public class PhoneshopAppException extends Exception{
    public PhoneshopAppException() {
        super();
    }

    public PhoneshopAppException(String message) {
        super(message);
    }

    public PhoneshopAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneshopAppException(Throwable cause) {
        super(cause);
    }
}

package io.hashimati.exceptions;

public class WebServiceGeneralException extends RuntimeException {
    private String message;
    public WebServiceGeneralException(String message)
    {
        super(message);
    }
}


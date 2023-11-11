package io.hashimati.exceptions;

public class EndpointGeneralException extends RuntimeException {
    private String message;
    public EndpointGeneralException(String message)
    {
        super(message);
    }
}


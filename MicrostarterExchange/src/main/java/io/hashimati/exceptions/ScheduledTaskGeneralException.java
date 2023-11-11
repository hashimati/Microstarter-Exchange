package io.hashimati.exceptions;

public class ScheduledTaskGeneralException extends RuntimeException {
    private String message;
    public ScheduledTaskGeneralException(String message)
    {
        super(message);
    }
}


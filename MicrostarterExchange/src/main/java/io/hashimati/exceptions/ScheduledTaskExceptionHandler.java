package io.hashimati.exceptions;

import io.hashimati.exceptions.ScheduledTaskGeneralException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;


@Produces
@Requires(classes = {ScheduledTaskGeneralException.class, ExceptionHandler.class})
@Singleton
public class ScheduledTaskExceptionHandler implements ExceptionHandler<ScheduledTaskGeneralException, HttpResponse> {
    @Override
    public HttpResponse handle(HttpRequest request, ScheduledTaskGeneralException exception) {
        return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


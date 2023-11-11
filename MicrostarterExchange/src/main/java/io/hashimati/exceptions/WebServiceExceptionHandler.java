package io.hashimati.exceptions;

import io.hashimati.exceptions.WebServiceGeneralException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;


@Produces
@Requires(classes = {WebServiceGeneralException.class, ExceptionHandler.class})
@Singleton
public class WebServiceExceptionHandler implements ExceptionHandler<WebServiceGeneralException, HttpResponse> {
    @Override
    public HttpResponse handle(HttpRequest request, WebServiceGeneralException exception) {
        return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

